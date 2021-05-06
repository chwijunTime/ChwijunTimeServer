package com.gsm.chwijuntime.service.consultinguser;

import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserResDto;
import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserSaveDto;
import com.gsm.chwijuntime.model.ConsultingAdmin;
import com.gsm.chwijuntime.model.ConsultingUser;
import com.gsm.chwijuntime.repository.ConsultingAdminRepository;
import com.gsm.chwijuntime.repository.ConsultingUserRepository;
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

    @Transactional
    @Override
    public void saveConsultingUser(Long idx, ConsultingUserSaveDto consultingUserSaveDto) {
        ConsultingAdmin consultingAdmin = consultingAdminRepository.findById(idx).orElseThrow(null);
        consultingAdmin.changeConsultingStatus();
        consultingUserRepository.save(consultingUserSaveDto.toEntityByConsultingUser(consultingAdmin));
    }

    @Override
    public List<ConsultingUserResDto> findAll() {
        List<ConsultingUserResDto> consultingUserResDtos = new ArrayList<>();
        ConsultingUserResDto consultingUserResDto;
        List<ConsultingUser> all = consultingUserRepository.findAll();
        for (ConsultingUser consultingUser : all) {
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