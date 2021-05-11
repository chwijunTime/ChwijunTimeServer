package com.gsm.chwijuntime.dto.applicationemployment;

import com.gsm.chwijuntime.model.ApplicationEmployment;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllApplicationDetailResDto {

    private ApplicationEmployment applicationEmployment;

}
