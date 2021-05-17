package com.gsm.chwijuntime.dto.tipstorage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.TipsStorage;
import com.gsm.chwijuntime.model.tagmapping.CompanyReviewTag;
import com.gsm.chwijuntime.model.tagmapping.TipsStorageTag;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipsStorageSaveDto {

    @NotBlank(message = "회사 이름을 입력해주세요.")
    private String workCompanyName;

    @NotBlank(message = "회사 주소을 입력해주세요.")
    private String workCompanyAddress;

    @NotBlank(message = "꿀팁을 적어주세요.")
    private String tipsInfo;

    @NotEmpty(message = "태그를 1개 이상 등록해주세요.")
    private List<String> tagName = new ArrayList<>();

    @JsonIgnore
    private Tag tag;
    @JsonIgnore
    private TipsStorage tipsStorage;

    public TipsStorage toEntityByTipsStorage(Member member) {
        return TipsStorage.builder()
                .workCompanyName(this.workCompanyName)
                .workCompanyAddress(this.workCompanyAddress)
                .tipsInfo(this.tipsInfo)
                .member(member)
                .build();
    }

    public void mappingTag_ContractingCompany(Tag tag, TipsStorage tipsStorage){
        this.tag = tag;
        this.tipsStorage = tipsStorage;
    }

    public TipsStorageTag toEntityByTipsStorageTag() {
        return TipsStorageTag.builder()
                .tag(this.tag)
                .tipsStorage(this.tipsStorage)
                .build();
    }

}
