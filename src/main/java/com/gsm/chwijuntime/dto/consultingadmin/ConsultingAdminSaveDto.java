package com.gsm.chwijuntime.dto.consultingadmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.ConsultingAdmin;
import com.gsm.chwijuntime.model.ConsultingStatus;
import com.gsm.chwijuntime.model.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultingAdminSaveDto {

    @NotBlank(message = "가능한 상담 일자를 입력해주세요.")
    private String applicationDate;

    public ConsultingAdmin toEntityByConsultingAdmin(Member member) {
        return ConsultingAdmin.builder()
                .applicationDate(applicationDate)
                .consultingStatus(ConsultingStatus.No_Application)
                .member(member)
                .build();
    }
}
