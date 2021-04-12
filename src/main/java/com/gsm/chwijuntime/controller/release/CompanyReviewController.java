package com.gsm.chwijuntime.controller.release;


import com.gsm.chwijuntime.dto.companyreview.CompanyReviewResDto;
import com.gsm.chwijuntime.dto.companyreview.CompanyReviewSaveDto;
import com.gsm.chwijuntime.model.CompanyReview;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.companyreview.CompanyReviewService;
import com.gsm.chwijuntime.service.companyreview.CompanyReviewServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"4. 면접 후기 및 회사 후기"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class CompanyReviewController {

    private final CompanyReviewService companyReviewService;
    private final ResponseService responseService;

    @ApiOperation(value = "면버 후기 등록", notes = "사용자가 면접 후기를 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("/companyreview")
    public CommonResult join(@RequestBody CompanyReviewSaveDto companyReviewSaveDto) {
        companyReviewService.insertCompanyReview(companyReviewSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "면접 후기 단일 조회", notes = "사용자가 면접 후기를 단일 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/companyreview/{companyreviewIdx}")
    public SingleResult<CompanyReviewResDto> findByIdx(@PathVariable Long companyreviewIdx) {
        CompanyReviewResDto companyReviewResDto = companyReviewService.findByIdx(companyreviewIdx);
        return responseService.getSingleResult(companyReviewResDto);
    }

    @ApiOperation(value = "면접 후기 전체 조회", notes = "사용자가 면접 후기를 단일 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/companyreview")
    public ListResult<CompanyReviewResDto> findAll() {
        List<CompanyReviewResDto> companyReviewResDtos = companyReviewService.findAll();
        return responseService.getListResult(companyReviewResDtos);
    }

    @ApiOperation(value = "면접 후기 전체 삭제", notes = "사용자가 면접 후기를 단일 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @DeleteMapping("/companyreview/{companyreviewIdx}")
    public CommonResult deletebByIdx(@PathVariable Long companyreviewIdx) {
        companyReviewService.deleteByIdx(companyreviewIdx);
        return responseService.getSuccessResult();
    }
}
