package com.gsm.chwijuntime.service.tipstorage;

import com.gsm.chwijuntime.dto.tipstorage.TipsStorageResDto;
import com.gsm.chwijuntime.dto.tipstorage.TipsStorageSaveDto;
import com.gsm.chwijuntime.dto.tipstorage.TipsStorageUpdateDto;

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

    //회사 이름 검색
    List<TipsStorageResDto> findByWorkCompanyName(String keyword);

    //내가 등록한 게시물 보기
    List<TipsStorageResDto> findByMember();
}
