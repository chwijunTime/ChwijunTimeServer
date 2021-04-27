package com.gsm.chwijuntime.service.resume;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.dto.resume.ResumeSaveDto;
import com.gsm.chwijuntime.dto.resume.ResumeUpdateDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.MemberResume;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.MemberResumeRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final GetUserEmailUtil getUserEmailUtil;
    private final MemberRepository memberRepository;
    private final MemberResumeRepository memberResumeRepository;

    @Transactional
    @Override
    public void saveResume(ResumeSaveDto resumeSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.GetUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        memberResumeRepository.save(resumeSaveDto.toEntityByMember(member));
    }

    @Override
    public List<MemberResume> findAll() {
        return memberResumeRepository.findAll();
    }

    @Override
    public MemberResume findByIdx(Long idx) {
        MemberResume memberResume = memberResumeRepository.findByMemberResumeIdx(idx);
        return memberResume;
    }

    @Transactional
    @Override
    public void updateResume(Long idx, ResumeUpdateDto resumeUpdateDto) {
        MemberResume memberResume = memberResumeRepository.findById(idx).orElseThrow(null);
        memberResume.changeURL(resumeUpdateDto.getResumeFileURL());
    }

    @Override
    public void deleteResume(Long idx) {
        memberResumeRepository.deleteById(idx);
    }

    @Override
    public List<MemberResume> findByMember() {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.GetUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        List<MemberResume> resumes = memberResumeRepository.findAllByMember(member);
        return resumes;
    }

}
