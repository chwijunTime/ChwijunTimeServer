package com.gsm.chwijuntime.dto.consultinguser;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultingUserResDto {

    private Long consultingUserIdx;
    private String consultingUserName;
    private String consultingUserClassNumber;
    private String applicationDate;
}
