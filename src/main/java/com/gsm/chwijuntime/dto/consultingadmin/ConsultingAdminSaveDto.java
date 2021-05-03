package com.gsm.chwijuntime.dto.consultingadmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.ConsultingAdmin;
import com.gsm.chwijuntime.model.ConsultingStatus;
import com.gsm.chwijuntime.model.Member;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultingAdminSaveDto {

    private String applicationDate;

    public ConsultingAdmin toEntityByConsultingAdmin(Member member) {
        return ConsultingAdmin.builder()
                .applicationDate(applicationDate)
                .consultingStatus(ConsultingStatus.No_Application)
                .member(member)
                .build();
    }
}
