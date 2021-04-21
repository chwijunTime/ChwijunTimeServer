package com.gsm.chwijuntime.dto.applicationemployment;

import com.gsm.chwijuntime.model.ApplicationEmploymentStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllApplicationResDto {

    //학번, 깃허브, 회사 이름, 채용 분야, 지원 날짜, 마감 일자 등
    private Long applicationEmploymentIdx;
    private String memberClassNumber;
    private String gitHubURL;
    private String employmentAnnouncementName;
    private String recruitmentField;
    private ApplicationEmploymentStatus applicationEmploymentStatus;

}
