package com.gsm.chwijuntime.dto.consultingadmin;


import com.gsm.chwijuntime.model.ConsultingStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultingAdminResDto {

    private Long consultingIdx;
    private String applicationDate;
    private ConsultingStatus consultingStatus;

}
