package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanySaveDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementResponseDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementSaveDto;
import com.gsm.chwijuntime.dto.employmentAnnouncement.EmploymentAnnouncementUpdateDto;
import com.gsm.chwijuntime.model.EmploymentAnnouncement;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.employmentAnnouncement.EmploymentAnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"6. 취업 공고"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class EmploymentAnnouncementController {

    private final EmploymentAnnouncementService employmentAnnouncementService;
    private final ResponseService responseService;

    @ApiOperation(value = "취업 공고 등록", notes = "관리자가 취업 공고를 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("/employment-announcement")
    public CommonResult save(@RequestBody EmploymentAnnouncementSaveDto employmentAnnouncementSaveDto) {
        employmentAnnouncementService.EmploymentAnnouncementSave(employmentAnnouncementSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "취업 공고 단일 조회", notes = "관리자와 사용자가 취업 공고를 단일 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/employment-announcement/{employmentAnnouncementIdx}")
    public SingleResult<EmploymentAnnouncementResponseDto> findByIdx(@PathVariable Long employmentAnnouncementIdx) {
        EmploymentAnnouncementResponseDto employmentAnnouncementResponseDto = employmentAnnouncementService.findByOne(employmentAnnouncementIdx);
        return responseService.getSingleResult(employmentAnnouncementResponseDto);
    }

    @ApiOperation(value = "취업 공고 전체 조회", notes = "관리자와 사용자가 취업 공고를 전체 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/employment-announcement")
    public ListResult<EmploymentAnnouncementResponseDto> findAll() {
        List<EmploymentAnnouncementResponseDto> employmentAnnouncements = employmentAnnouncementService.findByAll();
        return responseService.getListResult(employmentAnnouncements);
    }

    @ApiOperation(value = "취업 공고 수정", notes = "관리자가 취업 공고를 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PutMapping("/employment-announcement/{employmentAnnouncementIdx}")
    public CommonResult update(@PathVariable Long employmentAnnouncementIdx, @RequestBody EmploymentAnnouncementUpdateDto employmentAnnouncementUpdateDto) {
        employmentAnnouncementService.updateEmploymentAnnouncement(employmentAnnouncementIdx, employmentAnnouncementUpdateDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "취업 공고 삭제", notes = "관리자가 취업 공고를 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @DeleteMapping("/employment-announcement/{employmentAnnouncementIdx}")
    public CommonResult delete(@PathVariable Long employmentAnnouncementIdx) {
        employmentAnnouncementService.deleteEmploymentAnnouncement(employmentAnnouncementIdx);
        return responseService.getSuccessResult();
    }
}

