package com.gsm.chwijuntime.dto.consultingadmin;


import com.gsm.chwijuntime.model.ConsultingStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultingAdminResDto {

    private Long consultingIdx;
    private LocalDate applicationDate;
    private ConsultingStatus consultingStatus;

}
