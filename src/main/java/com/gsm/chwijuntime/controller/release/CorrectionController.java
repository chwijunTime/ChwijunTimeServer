package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.correction.CorrectionApplySaveDto;
import com.gsm.chwijuntime.dto.correction.CorrectionApprovalSaveDto;
import com.gsm.chwijuntime.dto.correction.CorrectionRejectionSaveDto;
import com.gsm.chwijuntime.model.CorrectionType;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.service.correction.CorrectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"9. 이력서 및 포트폴리오 첨삭"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class CorrectionController {

    private final ResponseService responseService;
    private final CorrectionService correctionService;

    @ApiOperation(value = "사용자 첨삭 신청", notes = "사용자가 첨삭을 신청한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/correction-request")
    public CommonResult saveCorrection(@RequestParam Long idx, @RequestBody CorrectionApplySaveDto correctionApplySaveDto, @RequestParam CorrectionType correctionType) throws Exception {
        correctionService.saveCorrectionApply(idx, correctionApplySaveDto, correctionType);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "관리자 요청 거절", notes = "관리자가 요청을 거절한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/correction-rejection")
    public CommonResult correctionRejection(@RequestParam Long idx, @RequestBody CorrectionRejectionSaveDto correctionRejectionSaveDto) throws Exception {
        correctionService.requestRejection(idx, correctionRejectionSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "관리자 요청 승인", notes = "관리자가 요청을 승인한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/correction-approval")
    public CommonResult correctionApproval(@RequestParam Long idx, @RequestBody CorrectionApprovalSaveDto correctionApprovalSaveDto) throws Exception {
        correctionService.requestApproval(idx, correctionApprovalSaveDto);
        return responseService.getSuccessResult();
    }
}
