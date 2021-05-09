package com.gsm.chwijuntime.dto.consultinguser;

import com.gsm.chwijuntime.model.ConsultingAdmin;
import com.gsm.chwijuntime.model.ConsultingUser;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultingUserSaveDto {

    @NotBlank(message = "학번을 입력해주세요.")
    private String classNumber;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    public ConsultingUser toEntityByConsultingUser(ConsultingAdmin consultingAdmin) {
        return ConsultingUser.builder()
                .consultingUserName(name)
                .consultingUserClassNumber(classNumber)
                .consultingAdmin(consultingAdmin)
                .build();
    }
}
