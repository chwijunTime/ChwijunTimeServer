package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.portfolio.PortfolioSaveDto;
import com.gsm.chwijuntime.dto.portfolio.PortfolioUpdateDto;
import com.gsm.chwijuntime.dto.resume.ResumeSaveDto;
import com.gsm.chwijuntime.dto.resume.ResumeUpdateDto;
import com.gsm.chwijuntime.model.MemberPortfolio;
import com.gsm.chwijuntime.model.MemberResume;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.portfolio.PortfolioService;
import com.gsm.chwijuntime.service.resume.ResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"8. 이력서 및 포트폴리오"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class Portfolio_ResumeController {

    private final ResponseService responseService;
    private final PortfolioService portfolioService;
    private final ResumeService resumeService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "포트폴리오 저장", notes = "사용자가 포트폴리오를 저장한다.")
    @PostMapping("/portfolio")
    public CommonResult portfolioSave(@Valid @RequestBody PortfolioSaveDto portfolioSaveDto){
        portfolioService.savePortfolio(portfolioSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "포트폴리오 전체 조회", notes = "관리자가 포트폴리오를 전체 조회한다.")
    @GetMapping("/portfolio")
    public ListResult<MemberPortfolio> findAll(){
        List<MemberPortfolio> all = portfolioService.findAll();
        return responseService.getListResult(all);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "포트폴리오 단일 조회", notes = "관리자가 포트폴리오를 전체 조회한다.")
    @GetMapping("/portfolio/{portfolioIdx}")
    public SingleResult<MemberPortfolio> findByIdx(@PathVariable Long portfolioIdx){
        MemberPortfolio memberPortfolio = portfolioService.findByIdx(portfolioIdx);
        return responseService.getSingleResult(memberPortfolio);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "포트폴리오 수정", notes = "사용자가 포트폴리오를 수정한다.")
    @PutMapping("/portfolio/{portfolioIdx}")
    public CommonResult updateByIdx(@PathVariable Long portfolioIdx, @Valid @RequestBody PortfolioUpdateDto portfolioUpdateDto){
        portfolioService.updatePortfolio(portfolioIdx, portfolioUpdateDto);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "포트폴리오 삭제", notes = "사용자가 포트폴리오를 삭제한다.")
    @DeleteMapping("/portfolio/{portfolioIdx}")
    public CommonResult portfolioDeleteByIdx(@PathVariable Long portfolioIdx){
        portfolioService.deletePortfolio(portfolioIdx);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "나의 포트폴리오 전체 보기", notes = "사용자가 자신이 등록한 포트폴리오를 전체 조회한다.")
    @GetMapping("/my-portfolio")
    public ListResult<MemberPortfolio> findByMember(){
        List<MemberPortfolio> memberPortfolios = portfolioService.myPortfolio();
        return responseService.getListResult(memberPortfolios);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "이력서 저장", notes = "사용자가 이력서를 저장한다.")
    @PostMapping("/resume")
    public CommonResult ResumeSave(@Valid @RequestBody ResumeSaveDto resumeSaveDto){
        resumeService.saveResume(resumeSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "이력서 전체 조회", notes = "관리자가 이력서를 전체 조히한다.")
    @GetMapping("/resume")
    public CommonResult findAllResume(){
        List<MemberResume> memberResumes = resumeService.findAll();
        return responseService.getListResult(memberResumes);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "이력서 단일 조회", notes = "관리자와 사용자가 이력서를 단일 조회한다.")
    @GetMapping("/resume/{resumeIdx}")
    public SingleResult<MemberResume> ResumefindByIdx(@PathVariable Long resumeIdx){
        MemberResume memberResume = resumeService.findByIdx(resumeIdx);
        return responseService.getSingleResult(memberResume);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "이력서 수정", notes = "사용자가 이력서를 업데이트한다.")
    @PutMapping("/resume/{resumeIdx}")
    public CommonResult updateByIdx(@PathVariable Long resumeIdx, @Valid @RequestBody ResumeUpdateDto resumeUpdateDto){
        resumeService.updateResume(resumeIdx, resumeUpdateDto);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "이력서 삭제", notes = "사용자가 이력서를 삭제한다.")
    @DeleteMapping("/resume/{resumeIdx}")
    public CommonResult deleteByIdx(@PathVariable Long resumeIdx){
        resumeService.deleteResume(resumeIdx);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "나의 이력서 전체 보기", notes = "사용자가 자신이 등록한 이력서를 전체 조회한다.")
    @GetMapping("/my-resume")
    public ListResult<MemberResume> myResume(){
        List<MemberResume> memberResumes = resumeService.findByMember();
        return responseService.getListResult(memberResumes);
    }
}
