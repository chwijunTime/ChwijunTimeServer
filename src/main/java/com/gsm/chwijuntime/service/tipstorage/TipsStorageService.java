package com.gsm.chwijuntime.service.tipstorage;

import com.gsm.chwijuntime.dto.tipstorage.TipsStorageResDto;
import com.gsm.chwijuntime.dto.tipstorage.TipsStorageSaveDto;
import com.gsm.chwijuntime.dto.tipstorage.TipsStorageUpdateDto;
import com.gsm.chwijuntime.model.TipsStorage;

import java.util.List;

public interface TipsStorageService {

    // 저장
    void saveTipsStorage(TipsStorageSaveDto tipsStorageSaveDto);

    // 전체 조회
    List<TipsStorageResDto> findAll();

    // 단일 조회
    TipsStorageResDto findByIdx(Long idx);

    // 수정
    void updateTipsStorage(Long idx, TipsStorageUpdateDto tipsStorageUpdateDto);

    // 삭제
    void deleteTipsStorage(Long idx);

}
