package com.gsm.chwijuntime.dto.consultingadmin;


import com.gsm.chwijuntime.model.ConsultingStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultingAdminResDto {

    private Long consultingIdx;
    private LocalDateTime applicationDate;
    private ConsultingStatus consultingStatus;

}
