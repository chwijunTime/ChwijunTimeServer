package com.gsm.chwijuntime.dto.employmentAnnouncement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.tagmapping.EmploymentAnnouncementTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentAnnouncementUpdateDto {

    @NotBlank(message = "공고 회사 이름을 입력해주세요.")
    private String employmentAnnouncementName;

    @NotBlank(message = "채용 분야를 입력해주세요.")
    private String recruitmentField;

    private String employmentAnnouncementExplanation;
    private String preferentialConditions;

    @NotBlank(message = "회사 위치를 입력해주세요.")
    private String employmentAnnouncementAddress;

    private String employmentAnnouncementEtc;

    private List<String> tagName;

    @JsonIgnore
    private Tag tag;
    @JsonIgnore
    private EmploymentAnnouncement employmentAnnouncement;

    public void MappingTagByEmploymentAnnouncement(Tag tag, EmploymentAnnouncement employmentAnnouncement){
        this.tag = tag;
        this.employmentAnnouncement = employmentAnnouncement;
    }

    public EmploymentAnnouncementTag ToEntityByEmploymentAnnouncementTag(){
        return EmploymentAnnouncementTag.builder()
                .employmentAnnouncement(this.employmentAnnouncement)
                .tag(this.tag)
                .build();
    }
}
