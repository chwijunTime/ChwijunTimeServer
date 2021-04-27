package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.resume.ResumeSaveDto;
import com.gsm.chwijuntime.dto.resume.ResumeUpdateDto;
import com.gsm.chwijuntime.model.MemberResume;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.resume.ResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"8. 이력서 저장"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class ResumeController {

    private final ResponseService responseService;
    private final ResumeService resumeService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "이력서 저장", notes = "사용자가 이력서를 저장한다.")
    @PostMapping("/resume")
    public CommonResult ResumeSave(@RequestBody ResumeSaveDto resumeSaveDto){
        resumeService.saveResume(resumeSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "이력서 전체 조회", notes = "관리자가 이력서를 전체 조히한다.")
    @GetMapping("/resume")
    public CommonResult findAllResume(){
        List<MemberResume> memberResumes = resumeService.findAll();
        return responseService.getListResult(memberResumes);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "이력서 단일 조회", notes = "관리자와 사용자가 이력서를 단일 조회한다.")
    @GetMapping("/resume/{resumeIdx}")
    public SingleResult<MemberResume> findByIdx(@PathVariable Long resumeIdx){
        MemberResume memberResume = resumeService.findByIdx(resumeIdx);
        return responseService.getSingleResult(memberResume);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "이력서 수정", notes = "사용자가 이력서를 업데이트한다.")
    @PutMapping("/resume/{resumeIdx}")
    public CommonResult updateByIdx(@PathVariable Long resumeIdx, @RequestBody ResumeUpdateDto resumeUpdateDto){
        resumeService.updateResume(resumeIdx, resumeUpdateDto);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "이력서 삭제", notes = "사용자가 이력서를 삭제한다.")
    @DeleteMapping("/resume/{resumeIdx}")
    public CommonResult deleteByIdx(@PathVariable Long resumeIdx){
        resumeService.deleteResume(resumeIdx);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "나의 이력서 전체 보기", notes = "사용자가 자신이 등록한 이력서를 전체 조회한다.")
    @GetMapping("/my-resume")
    public ListResult<MemberResume> myResume(){
        List<MemberResume> memberResumes = resumeService.findByMember();
        return responseService.getListResult(memberResumes);
    }
}
