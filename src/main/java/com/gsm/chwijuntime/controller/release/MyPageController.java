package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.applicationemployment.FindAllApplicationResDto;
import com.gsm.chwijuntime.dto.companyreview.CompanyReviewResDto;
import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserResDto;
import com.gsm.chwijuntime.dto.tipstorage.TipsStorageResDto;
import com.gsm.chwijuntime.model.Correction;
import com.gsm.chwijuntime.model.CorrectionApply;
import com.gsm.chwijuntime.model.MemberPortfolio;
import com.gsm.chwijuntime.model.MemberResume;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.service.applicationemployment.ApplicationEmploymentService;
import com.gsm.chwijuntime.service.companyreview.CompanyReviewService;
import com.gsm.chwijuntime.service.consultingadmin.ConsultingAdminService;
import com.gsm.chwijuntime.service.consultinguser.ConsultingUserService;
import com.gsm.chwijuntime.service.correction.CorrectionService;
import com.gsm.chwijuntime.service.portfolio.PortfolioService;
import com.gsm.chwijuntime.service.resume.ResumeService;
import com.gsm.chwijuntime.service.tipstorage.TipsStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"11. 마이페이지"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class MyPageController {

    private final ResumeService resumeService;
    private final PortfolioService portfolioService;
    private final ConsultingUserService consultingUserService;
    private final CorrectionService correctionService;
    private final CompanyReviewService companyReviewService;
    private final ApplicationEmploymentService applicationEmploymentService;
    private final TipsStorageService tipsStorageService;
    private final ResponseService responseService;

    // 내가 등록한 면접 후기 보기
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "면접 후기 보기(마이페이지)", notes = "사용자의 면접 후기를 전체 조회한다.")
    @GetMapping("/mypage-company-review")
    public ListResult<CompanyReviewResDto> myPageCompanyReview() {
        List<CompanyReviewResDto> byMember = companyReviewService.findByMember();
        return responseService.getListResult(byMember);
    }

    // 내가 신청한 취업 공고 보기
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "취업 공고 신청 보기(마이페이지)", notes = "사용자의 공고 신청을 전체 조회한다.")
    @GetMapping("/mypage-application-employment")
    public ListResult<FindAllApplicationResDto> myPageApplicationEmployment() {
        List<FindAllApplicationResDto> byMember = applicationEmploymentService.findByMember();
        return responseService.getListResult(byMember);
    }

    // 내가 등록한 이력서 보기
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "이력서 보기(마이페이지)", notes = "사용자의 이력서를 전체 조회한다.")
    @GetMapping("/mypage-resume")
    public ListResult<MemberResume> myPageResume() {
        List<MemberResume> byMember = resumeService.findByMember();
        return responseService.getListResult(byMember);
    }

    // 내가 등록한 포트폴리오 보기
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "포트폴리오 보기(마이페이지)", notes = "사용자의 포트폴리오를 전체 조회한다.")
    @GetMapping("/mypage-portfolio")
    public ListResult<MemberPortfolio> myPagePortfolio() {
        List<MemberPortfolio> memberPortfolios = portfolioService.myPortfolio();
        return responseService.getListResult(memberPortfolios);
    }

    // 내가 신청한 첨삭 보기
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "첨삭 신청 보기(마이페이지)", notes = "사용자의 첨삭 신청을 전체 조회한다.")
    @GetMapping("/mypage-correction-apply")
    public ListResult<CorrectionApply> myPageCorrectionApply() {
        List<CorrectionApply> byMyApply = correctionService.findByMyApply();
        return responseService.getListResult(byMyApply);
    }

    // 내가 받은 첨삭 보기
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "첨삭 보기(마이페이지)", notes = "사용자의 첨삭을 전체 조회한다.")
    @GetMapping("/mypage-correction")
    public ListResult<Correction> myPageCorrection() {
        List<Correction> myCorrection = correctionService.findMyCorrection();
        return responseService.getListResult(myCorrection);
    }

    // 내가 신청한 상담 보기
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "상담 신청 보기(마이페이지)", notes = "사용자의 상담 신청을 전체 조회한다.")
    @GetMapping("/mypage-consulting-user")
    public ListResult<ConsultingUserResDto> myPageConsultingUser() {
        List<ConsultingUserResDto> byMember = consultingUserService.findByMember();
        return responseService.getListResult(byMember);
    }

    // 내가 등록한 꿀팁 보기
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "꿀팁 보기(마이페이지)", notes = "사용자의 꿀팁 등록을 조회한다.")
    @GetMapping("/mypage-tip-user")
    public ListResult<TipsStorageResDto> myPageTip() {
        List<TipsStorageResDto> byMember = tipsStorageService.findByMember();
        return responseService.getListResult(byMember);
    }

}
