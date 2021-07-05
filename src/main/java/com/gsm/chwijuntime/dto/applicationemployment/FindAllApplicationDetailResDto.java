package com.gsm.chwijuntime.dto.applicationemployment;

import com.gsm.chwijuntime.model.ApplicationEmployment;
import com.gsm.chwijuntime.model.ApplicationEmploymentStatus;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.Member;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllApplicationDetailResDto {

    private Long applicationEmploymentIdx;
    private String gitHubURL;  // 깃허브 URL
    private String applicationEmploymentResumeURL; // 이력서 URL (Notion)
    private String applicationEmploymentPortfolioURL;  // 포트폴리오 URL (Notion)
    private ApplicationEmploymentStatus applicationEmploymentStatus;  //신청 상태
    private LocalDate applicationDate;  //지원 날짜

    private String memberEmail;
    private String memberClassNumber;

    private String employmentAnnouncementName;
    private String recruitmentField;
    private String employmentAnnouncementAddress;
    private LocalDate deadLine;

    public void mapping(ApplicationEmployment applicationEmployment, Member member, EmploymentAnnouncement employmentAnnouncement) {
        this.applicationEmploymentIdx = applicationEmployment.getApplicationEmploymentIdx();
        this.gitHubURL = applicationEmployment.getGitHubURL();
        this.applicationEmploymentResumeURL = applicationEmployment.getApplicationEmploymentResumeURL();
        this.applicationEmploymentPortfolioURL = applicationEmployment.getApplicationEmploymentPortfolioURL();
        this.applicationEmploymentStatus = applicationEmployment.getApplicationEmploymentStatus();
        this.applicationDate = applicationEmployment.getApplicationDate();
        this.memberEmail = member.getMemberEmail();
        this.memberClassNumber = member.getMemberClassNumber();
        this.employmentAnnouncementName = employmentAnnouncement.getEmploymentAnnouncementName();
        this.recruitmentField = employmentAnnouncement.getRecruitmentField();
        this.employmentAnnouncementAddress = employmentAnnouncement.getEmploymentAnnouncementAddress();
        this.deadLine = employmentAnnouncement.getDeadLine();
    }
}
