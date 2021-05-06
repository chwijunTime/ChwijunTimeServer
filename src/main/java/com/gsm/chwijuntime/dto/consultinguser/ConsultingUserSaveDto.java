package com.gsm.chwijuntime.dto.consultinguser;

import com.gsm.chwijuntime.model.ConsultingAdmin;
import com.gsm.chwijuntime.model.ConsultingUser;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultingUserSaveDto {

    private String classNumber;
    private String name;

    public ConsultingUser toEntityByConsultingUser(ConsultingAdmin consultingAdmin) {
        return ConsultingUser.builder()
                .consultingUserName(name)
                .consultingUserClassNumber(classNumber)
                .consultingAdmin(consultingAdmin)
                .build();
    }
}
