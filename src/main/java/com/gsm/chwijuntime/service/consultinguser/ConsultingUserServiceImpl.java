package com.gsm.chwijuntime.service.consultinguser;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserResDto;
import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserSaveDto;
import com.gsm.chwijuntime.model.ConsultingAdmin;
import com.gsm.chwijuntime.model.ConsultingUser;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.ConsultingAdminRepository;
import com.gsm.chwijuntime.repository.ConsultingUserRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConsultingUserServiceImpl implements ConsultingUserService {

    private final ModelMapper mapper;
    private final ConsultingAdminRepository consultingAdminRepository;
    private final ConsultingUserRepository consultingUserRepository;
    private final MemberRepository memberRepository;
    private final GetUserEmailUtil getUserEmailUtil;

    @Transactional
    @Override
    public void saveConsultingUser(Long idx, ConsultingUserSaveDto consultingUserSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        ConsultingAdmin consultingAdmin = consultingAdminRepository.findById(idx).orElseThrow(null);
        consultingAdmin.changeConsultingStatus();
        consultingUserRepository.save(consultingUserSaveDto.toEntityByConsultingUser(consultingAdmin, member));
    }

    @Override
    public List<ConsultingUserResDto> findAll() {
        List<ConsultingUserResDto> consultingUserResDtos = new ArrayList<>();
        List<ConsultingUser> all = consultingUserRepository.findAll();
        return getConsultingUserResDtos(consultingUserResDtos, all);
    }

    @Override
    public List<ConsultingUserResDto> findByMember() {
        List<ConsultingUserResDto> consultingUserResDtos = new ArrayList<>();
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        List<ConsultingUser> byMember = consultingUserRepository.findByMember(member);
        return getConsultingUserResDtos(consultingUserResDtos, byMember);
    }

    private List<ConsultingUserResDto> getConsultingUserResDtos(List<ConsultingUserResDto> consultingUserResDtos, List<ConsultingUser> byMember) {
        ConsultingUserResDto consultingUserResDto;
        for (ConsultingUser consultingUser : byMember) {
            ConsultingAdmin consultingAdmin = consultingUser.getConsultingAdmin();
            consultingUserResDto = ConsultingUserResDto.builder()
                    .consultingUserIdx(consultingUser.getConsultingUserIdx())
                    .consultingUserName(consultingUser.getConsultingUserName())
                    .consultingUserClassNumber(consultingUser.getConsultingUserClassNumber())
                    .applicationDate(consultingAdmin.getApplicationDate())
                    .build();
            consultingUserResDtos.add(consultingUserResDto);
        }
        return consultingUserResDtos;
    }
}