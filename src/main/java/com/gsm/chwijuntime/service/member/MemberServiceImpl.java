package com.gsm.chwijuntime.service.member;

import com.gsm.chwijuntime.advice.exception.*;
import com.gsm.chwijuntime.security.CustomUserDetailService;
import com.gsm.chwijuntime.dto.member.*;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.MemberTag;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.TagRepository;
import com.gsm.chwijuntime.repository.tag.MemberTagRepository;
import com.gsm.chwijuntime.security.JwtTokenProvider;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import com.gsm.chwijuntime.util.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final CustomUserDetailService customUserDetailService;
    private final RedisUtil redisUtil;
    private final MemberRepository memberRepository;
    private final MemberTagRepository memberTagRepository;
    private final TagRepository tagRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final GetUserEmailUtil getUserEmailUtil;

    @Transactional
    @Override
    public void InsertMember(MemberJoinDto memberJoinDto) {
        Optional<Member> member = memberRepository.findByMemberEmail(memberJoinDto.getMemberEmail());
        if(member.isEmpty()) {
            String password = memberJoinDto.getMemberPassword();
            memberJoinDto.setMemberPassword(passwordEncoder.encode(password));
            memberRepository.save(memberJoinDto.ToEntityByMember()).getMemberIdx();
        } else {
            throw new UserDuplicationException();
        }
    }

    @Override
    public MemberLoginResDto findMember(MemberLoginDto memberLoginDto) {
        String accessToken = null;
        String refreshToken = null;
        String roles = null;
        MemberLoginResDto memberLoginResDto = null;
        Member member = memberRepository.findByMemberEmail(memberLoginDto.getMemberEmail()).orElseThrow(EmailNotFoundException::new);
        if (member != null) {
            boolean check = passwordEncoder.matches(memberLoginDto.getMemberPassword(), member.getMemberPassword());
            if(!check) {
                throw new IncorrectPasswordException();
            }
            accessToken = jwtTokenProvider.generateToken(member);
            refreshToken = jwtTokenProvider.generateRefreshToken(member);
            roles = member.String_Role(member);
            redisUtil.setDataExpire(refreshToken, member.getUsername(), jwtTokenProvider.REFRESH_TOKEN_VALIDATION_SECOND);
            memberLoginResDto = MemberLoginResDto.mapping(member.getMemberEmail(), member.getMemberClassNumber(), roles, accessToken, refreshToken);
        }
        return memberLoginResDto;
    }

    @Override
    public void logoutMember() {
        String userEmail = getUserEmailUtil.getUserEmail();
        redisUtil.deleteData(userEmail);
    }

    @Override
    public Member UserInfo() {
        String UserEmail = getUserEmailUtil.getUserEmail();
        return memberRepository.findByMemberEmail(UserEmail).orElseThrow(EmailNotFoundException::new);
    }

    @Transactional
    @Override
    public void memberProfileSave(MemberProfileSaveDto memberProfileSaveDto) {
        for (String i : memberProfileSaveDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i);
            if(tag == null) {
                throw new NotFoundTagException();
            }
            String userEmail = getUserEmailUtil.getUserEmail();
            Member member = memberRepository.findByMemberEmail(userEmail).orElseThrow(CAuthenticationEntryPointException::new);
            memberProfileSaveDto.MappingTag_Member(tag, member);
            //프로필 업데이트
            member.change_profile(memberProfileSaveDto.getMemberPhoneNumber(), memberProfileSaveDto.getMemberETC());
            memberTagRepository.save(memberProfileSaveDto.ToEntityByMemberTag());
        }
    }

    @Transactional
    @Override
    public void updateMemberProfile(UpdateMemberProfileDto updateMemberProfileDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        member.change_profile(updateMemberProfileDto.getMemberPhoneNumber(), updateMemberProfileDto.getMemberETC());
        List<MemberTag> findMemberTag = memberTagRepository.findByMember(member);
        // 관련된 태그들 모두 삭제
        for (MemberTag memberTag : findMemberTag) {
            memberTagRepository.delete(memberTag);
        }
        //태그 저장
        for (String i : updateMemberProfileDto.getTagName()){
            Tag tag = tagRepository.findByTagName(i);
            if(tag == null) {
                throw new NotFoundTagException();
            }
            updateMemberProfileDto.MappingTag_Member(tag, member);
            memberTagRepository.save(updateMemberProfileDto.ToEntityByMemberTag());
        }
    }

    @Override
    public void userEmailCheck(String email) {
        Optional<Member> member = memberRepository.findByMemberEmail(email);
        if(!member.isEmpty()){
            throw new UserDuplicationException();
        }
    }

    @Transactional
    @Override
    public void change_password(MemberPasswordChangeDto memberPasswordChangeDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        String pw = passwordEncoder.encode(memberPasswordChangeDto.getMemberPassword());
        member.change_password(pw);
    }

    @Override
    public String authRefresh(AuthRefreshDto authRefreshDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String newAccessToken = null;
        String RefreshTokenUserEmail = jwtTokenProvider.getUserEmail(authRefreshDto.getRefreshToken());
        String RedisRefreshJwt = redisUtil.getData(RefreshTokenUserEmail);  //현재 Redis에 저장되어 있는 리프레쉬 토큰

        if(RedisRefreshJwt.equals(authRefreshDto.getRefreshToken())){
            try{
                if(authRefreshDto.getRefreshToken() != null && jwtTokenProvider.validateToken(authRefreshDto.getRefreshToken())){
                    Authentication authentication = jwtTokenProvider.getAuthentication(authRefreshDto.getRefreshToken());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e){   // 만약 유효기간을 넘겼다면??
                httpServletResponse.setHeader("message", e.getMessage());
            }
            Member member = customUserDetailService.findMember(RefreshTokenUserEmail);
            newAccessToken = jwtTokenProvider.generateToken(member);
        }
        return newAccessToken;
    }


    @Transactional
    @Override
    public MemberTagResDto viewMember() {
        List<String> tags = new ArrayList<>();
        String userEmail = getUserEmailUtil.getUserEmail();
        Member findMember = memberRepository.findByMemberEmail(userEmail).orElseThrow(CAuthenticationEntryPointException::new);
        List<MemberTag> findMemberTag = memberTagRepository.findByMember(findMember);
        for (MemberTag memberTag : findMemberTag) {
            String Name = memberTag.getTag().getTagName();
            Tag tag = tagRepository.findByTagName(Name);
            tags.add(tag.getTagName());
        }
        MemberTagResDto memberTagResDto = MemberTagResDto.mapping(findMember, tags);
        return memberTagResDto;
    }
}