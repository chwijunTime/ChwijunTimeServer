package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationResDto;
import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationSaveDto;
import com.gsm.chwijuntime.dto.employmentconfirmation.EmploymentConfirmationUpdateDto;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.employmentconfirmation.EmploymentConfirmationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"5. 취업 확정 현황"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class EmploymentConfirmationController {

    private final EmploymentConfirmationService employmentConfirmationService;
    private final ResponseService responseService;

    @ApiOperation(value = "취업 확정 현황 저장", notes = "관리자가 취업 확정 현황을 저장한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/employment-confirmation")
    public CommonResult save(@Valid @RequestBody EmploymentConfirmationSaveDto employmentConfirmationSaveDto) {
        employmentConfirmationService.EmploymentConfirmationServiceSave(employmentConfirmationSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "취업 확정 현황 전체 조회", notes = "관리자와 사용자가 취업 확정 현황을 전체 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/employment-confirmation")
    public ListResult<EmploymentConfirmationResDto> findAll() {
        List<EmploymentConfirmationResDto> all = employmentConfirmationService.findAll();
        return responseService.getListResult(all);
    }

    @ApiOperation(value = "취업 확정 현황 단일 조회", notes = "관리자와 사용자가 취업 확정 현황을 단일 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/employment-confirmation/{employmentConfirmationIdx}")
    public SingleResult<EmploymentConfirmationResDto> fidnByIdx(@PathVariable Long employmentConfirmationIdx) {
        EmploymentConfirmationResDto byIdx = employmentConfirmationService.findByIdx(employmentConfirmationIdx);
        return responseService.getSingleResult(byIdx);
    }

    @ApiOperation(value = "취업 확정 현황 수정", notes = "관리자가 취업 확정 현황을 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PutMapping("/employment-confirmation/{employmentConfirmationIdx}")
    public CommonResult updateByIdx(@PathVariable Long employmentConfirmationIdx, @Valid @RequestBody EmploymentConfirmationUpdateDto employmentConfirmationUpdateDto) {
        employmentConfirmationService.updateEmploymentConfirmation(employmentConfirmationIdx, employmentConfirmationUpdateDto);
        return responseService.getSuccessResult();
    }
}
