package com.gsm.chwijuntime.dto.tipstorage;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipsStorageResDto {

    private Long tipsStorageIdx;
    private String workCompanyName;
    private String workCompanyAddress;
    private String tipsInfo;
    private List<String> tagName = new ArrayList<>();

}
