package com.gsm.chwijuntime.dto.applicationemployment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.ApplicationEmployment;
import com.gsm.chwijuntime.model.ApplicationEmploymentStatus;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationEmploymentSaveDto {

    @NotBlank(message = "깃허브 URL을 입력해주세요.")
    private String gitHubURL;

    @NotBlank(message = "이력서 URL을 입력해주세요.")
    private String applicationEmploymentResumeURL;

    @NotBlank(message = "포트폴리오 URL을 입력해주세요.")
    private String applicationEmploymentPortfolioURL;

    @JsonIgnore
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
