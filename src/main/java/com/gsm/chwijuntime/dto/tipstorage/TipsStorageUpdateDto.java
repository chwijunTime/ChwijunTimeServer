package com.gsm.chwijuntime.dto.tipstorage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.TipsStorage;
import com.gsm.chwijuntime.model.tagmapping.TipsStorageTag;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipsStorageUpdateDto {

    private String workCompanyName;
    private String workCompanyAddress;
    private String tipsInfo;
    private List<String> tagName = new ArrayList<>();


    @JsonIgnore
    private Tag tag;
    @JsonIgnore
    private TipsStorage tipsStorage;

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
