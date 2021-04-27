package com.gsm.chwijuntime.dto.applicationemployment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.ApplicationEmployment;
import com.gsm.chwijuntime.model.ApplicationEmploymentStatus;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.Member;
import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationEmploymentSaveDto {

    private String gitHubURL;
    private String applicationEmploymentResumeURL;
    private String applicationEmploymentPortfolioURL;
    private LocalDate localDate = LocalDate.now();

    public ApplicationEmployment toEntityByApplicationEmployment(Member member, EmploymentAnnouncement employmentAnnouncement) {
        return ApplicationEmployment.builder()
                .applicationDate(localDate)
                .gitHubURL(this.gitHubURL)
                .applicationEmploymentResumeURL(this.applicationEmploymentResumeURL)
                .applicationEmploymentPortfolioURL(this.applicationEmploymentPortfolioURL)
                .applicationEmploymentStatus(ApplicationEmploymentStatus.Wait)
                .member(member)
                .employmentAnnouncement(employmentAnnouncement)
                .build();
    }
}
