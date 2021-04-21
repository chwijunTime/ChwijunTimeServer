package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.applicationemployment.ApplicationEmploymentSaveDto;
import com.gsm.chwijuntime.dto.applicationemployment.FindAllApplicationResDto;
import com.gsm.chwijuntime.dto.companyreview.CompanyReviewSaveDto;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.service.applicationemployment.ApplicationEmploymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"7. 취업 공고 신청"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class ApplicationEmploymentController {

    private final ApplicationEmploymentService applicationEmploymentService;
    private final ResponseService responseService;

    @ApiOperation(value = "공고 신청", notes = "사용자가 취업 공고를 신청한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("/application/{employmentAnnouncementIdx}")
    public CommonResult application(@PathVariable Long employmentAnnouncementIdx, @RequestBody ApplicationEmploymentSaveDto applicationEmploymentSaveDto) {
        applicationEmploymentService.application(employmentAnnouncementIdx, applicationEmploymentSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "공고 전체 조회", notes = "관리자가 취업 공고를 전체 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/application")
    public ListResult<FindAllApplicationResDto> FindAllApplication() {
        List<FindAllApplicationResDto> allApplication = applicationEmploymentService.findAllApplication();
        return responseService.getListResult(allApplication);
    }
}
