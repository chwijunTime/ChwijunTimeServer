package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.correction.CorrectionApplyResDto;
import com.gsm.chwijuntime.dto.correction.CorrectionApprovalSaveDto;
import com.gsm.chwijuntime.dto.correction.CorrectionRejectionSaveDto;
import com.gsm.chwijuntime.model.CorrectionApply;
import com.gsm.chwijuntime.model.CorrectionType;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.correction.CorrectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public CommonResult saveCorrection(@RequestParam Long idx, @RequestParam CorrectionType correctionType) throws Exception {
        correctionService.saveCorrectionApply(idx, correctionType);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "관리자 요청 거절", notes = "관리자가 요청을 거절한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/admin/correction-rejection")
    public CommonResult correctionRejection(@RequestParam Long idx, @RequestBody CorrectionRejectionSaveDto correctionRejectionSaveDto) throws Exception {
        correctionService.requestRejection(idx, correctionRejectionSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "관리자 요청 승인", notes = "관리자가 요청을 승인한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/admin/correction-approval")
    public CommonResult correctionApproval(@RequestParam Long idx, @RequestBody CorrectionApprovalSaveDto correctionApprovalSaveDto) throws Exception {
        correctionService.requestApproval(idx, correctionApprovalSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "요청 단일 조회", notes = "관리자가 요청을 단일 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/admin/correction/{idx}")
    public SingleResult<CorrectionApply> findByIdx(@PathVariable Long idx) {
        CorrectionApply byIdx = correctionService.findByIdx(idx);
        return responseService.getSingleResult(byIdx);
    }

    @ApiOperation(value = "관리자 요청 전체 조회", notes = "관리자가 요청을 전체 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/admin/correction")
    public ListResult<CorrectionApply> findByAll() {
        List<CorrectionApply> all = correctionService.findAll();
        return responseService.getListResult(all);
    }

    @ApiOperation(value = "사용자 요청 전체 조회", notes = "내가 신청한 요청을 전체 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/my-correction")
    public ListResult<CorrectionApplyResDto> findByMember() {
        List<CorrectionApplyResDto> all = correctionService.findByMyApply();
        return responseService.getListResult(all);
    }
}
