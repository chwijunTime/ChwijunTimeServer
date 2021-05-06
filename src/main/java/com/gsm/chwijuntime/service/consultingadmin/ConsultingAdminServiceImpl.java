package com.gsm.chwijuntime.service.consultingadmin;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.dto.consultingadmin.ConsultingAdminResDto;
import com.gsm.chwijuntime.dto.consultingadmin.ConsultingAdminSaveDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.ConsultingAdminRepository;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConsultingAdminServiceImpl implements ConsultingAdminService {

    private final ModelMapper mapper;
    private final GetUserEmailUtil getUserEmailUtil;
    private final MemberRepository memberRepository;
    private final ConsultingAdminRepository consultingAdminRepository;

    @Transactional
    @Override
    public void saveConsultingAdmin(ConsultingAdminSaveDto consultingAdminSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        consultingAdminRepository.save(consultingAdminSaveDto.toEntityByConsultingAdmin(member));
    }

    @Override
    public ConsultingAdminResDto findByIdx(Long idx) {
        ConsultingAdminResDto consultingAdminResDto = consultingAdminRepository.findById(idx)
                .map(m -> mapper.map(m, ConsultingAdminResDto.class)).orElseThrow(null);
        return consultingAdminResDto;
    }

    @Override
    public List<ConsultingAdminResDto> findAll() {
        List<ConsultingAdminResDto> collect = consultingAdminRepository.findAll().stream()
                .map(m -> mapper.map(m, ConsultingAdminResDto.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Transactional
    @Override
    public void deleteConsultingAdmin(Long idx) {
        consultingAdminRepository.deleteById(idx);
    }
}
