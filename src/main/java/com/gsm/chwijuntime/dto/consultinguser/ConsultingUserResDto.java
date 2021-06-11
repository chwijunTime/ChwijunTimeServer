package com.gsm.chwijuntime.dto.consultinguser;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultingUserResDto {

    private Long consultingUserIdx;
    private String consultingUserName;
    private String consultingUserClassNumber;
    private LocalDateTime applicationDate;
}
