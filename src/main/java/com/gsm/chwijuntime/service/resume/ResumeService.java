package com.gsm.chwijuntime.service.resume;

import com.gsm.chwijuntime.dto.resume.ResumeSaveDto;
import com.gsm.chwijuntime.dto.resume.ResumeUpdateDto;
import com.gsm.chwijuntime.model.MemberResume;

import java.util.List;

public interface ResumeService {
    void saveResume(ResumeSaveDto resumeSaveDto);
    List<MemberResume> findAll();
    MemberResume findByIdx(Long idx);
    void updateResume(Long idx, ResumeUpdateDto resumeUpdateDto);
    void deleteResume(Long idx);
    List<MemberResume> findByMember();
}
