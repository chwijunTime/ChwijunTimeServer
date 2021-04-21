package com.gsm.chwijuntime.dto.applicationemployment;

import com.gsm.chwijuntime.model.ApplicationEmployment;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllApplicationDetailResDto {

    private Member member;
    private ApplicationEmployment applicationEmployment;
    private EmploymentAnnouncement employmentAnnouncement;

}
