package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.applicationemployment.ApplicationEmploymentSaveDto;
import com.gsm.chwijuntime.dto.applicationemployment.FindAllApplicationDetailResDto;
import com.gsm.chwijuntime.dto.applicationemployment.FindAllApplicationResDto;
import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanySaveDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementResponseDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementSaveDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementUpdateDto;
import com.gsm.chwijuntime.model.ApplicationEmploymentStatus;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.applicationemployment.ApplicationEmploymentService;
import com.gsm.chwijuntime.service.employmentAnnouncement.EmploymentAnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"4. 취업 공고"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class EmploymentAnnouncementController {

    private final EmploymentAnnouncementService employmentAnnouncementService;
    private final ApplicationEmploymentService applicationEmploymentService;
    private final ResponseService responseService;

    @ApiOperation(value = "관리자 취업 공고 등록", notes = "관리자가 취업 공고를 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/employment-announcement")
    public CommonResult save(@Valid @RequestBody EmploymentAnnouncementSaveDto employmentAnnouncementSaveDto) {
        employmentAnnouncementService.EmploymentAnnouncementSave(employmentAnnouncementSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "사용자 취업 공고 단일 조회", notes = "관리자와 사용자가 취업 공고를 단일 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/employment-announcement/{employmentAnnouncementIdx}")
    public SingleResult<EmploymentAnnouncementResponseDto> findByIdx(@PathVariable Long employmentAnnouncementIdx) {
        EmploymentAnnouncementResponseDto employmentAnnouncementResponseDto = employmentAnnouncementService.findByOne(employmentAnnouncementIdx);
        return responseService.getSingleResult(employmentAnnouncementResponseDto);
    }

    @ApiOperation(value = "사용자 취업 공고 전체 조회", notes = "관리자와 사용자가 취업 공고를 전체 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/employment-announcement")
    public ListResult<EmploymentAnnouncementResponseDto> findAll() {
        List<EmploymentAnnouncementResponseDto> employmentAnnouncements = employmentAnnouncementService.findByAll();
        return responseService.getListResult(employmentAnnouncements);
    }

    @ApiOperation(value = "관리자 취업 공고 수정", notes = "관리자가 취업 공고를 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PutMapping("/employment-announcement/{employmentAnnouncementIdx}")
    public CommonResult update(@PathVariable Long employmentAnnouncementIdx, @Valid @RequestBody EmploymentAnnouncementUpdateDto employmentAnnouncementUpdateDto) {
        employmentAnnouncementService.updateEmploymentAnnouncement(employmentAnnouncementIdx, employmentAnnouncementUpdateDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "관리자 취업 공고 삭제", notes = "관리자가 취업 공고를 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @DeleteMapping("/employment-announcement/{employmentAnnouncementIdx}")
    public CommonResult delete(@PathVariable Long employmentAnnouncementIdx) {
        employmentAnnouncementService.deleteEmploymentAnnouncement(employmentAnnouncementIdx);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "사용자 공고 신청", notes = "사용자가 취업 공고를 신청한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("/application/{employmentAnnouncementIdx}")
    @ResponseBody
    public CommonResult application(@PathVariable Long employmentAnnouncementIdx, @Valid @RequestBody ApplicationEmploymentSaveDto applicationEmploymentSaveDto) {
        applicationEmploymentService.application(employmentAnnouncementIdx, applicationEmploymentSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "관리자 신청 단일 조회", notes = "관리자가 신청을 단일(디테일) 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/application/{applicationIdx}")
    public SingleResult<FindAllApplicationDetailResDto> FindAllApplicationByidx(@PathVariable Long applicationIdx) {
        FindAllApplicationDetailResDto findAllApplicationDetailResDto = applicationEmploymentService.applicationDetail(applicationIdx);
        return responseService.getSingleResult(findAllApplicationDetailResDto);
    }

    @ApiOperation(value = "관리자 신청 승인", notes = "관리자가 취업 공고 신청을 승인한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/application-accept/{applicationIdx}")
    public CommonResult AcceptApplication(@PathVariable Long applicationIdx) throws Exception {
        applicationEmploymentService.acceptApplication(applicationIdx);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "관리자 신청 거절", notes = "관리자가 취업 공고 신청을 승인한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/application-reject/{applicationIdx}")
    public CommonResult Rejectpplication(@PathVariable Long applicationIdx) throws Exception {
        applicationEmploymentService.rejectApplication(applicationIdx);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "관리자 신청 상태 조회 (공고 전체 조회)", notes = "관리자가 취업 공고 상태를 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/application-status")
    public ListResult<FindAllApplicationResDto> findByStatus(@RequestParam ApplicationEmploymentStatus status) {
        List<FindAllApplicationResDto> byStatus = applicationEmploymentService.findByStatus(status);
        return responseService.getListResult(byStatus);
    }

    @ApiOperation(value = "사용자 키워드 검색 (이름, 지역, 직군)", notes = "사용자가 키워드 검색을 한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/application-keyword")
    public ListResult<EmploymentAnnouncementResponseDto> findByKeyword(@RequestParam String keyword) {
        List<EmploymentAnnouncementResponseDto> byEmploymentAnnouncementNameORRecruitmentFieldOREmploymentAnnouncementAddress = employmentAnnouncementService.findByEmploymentAnnouncementNameORRecruitmentFieldOREmploymentAnnouncementAddress(keyword);
        return responseService.getListResult(byEmploymentAnnouncementNameORRecruitmentFieldOREmploymentAnnouncementAddress);
    }
}

