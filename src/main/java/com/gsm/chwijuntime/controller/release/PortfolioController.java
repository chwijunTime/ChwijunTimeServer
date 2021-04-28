package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.portfolio.PortfolioSaveDto;
import com.gsm.chwijuntime.dto.portfolio.PortfolioUpdateDto;
import com.gsm.chwijuntime.model.MemberPortfolio;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.portfolio.PortfolioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"9. 포트폴리오 저장"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class PortfolioController {

    private final ResponseService responseService;
    private final PortfolioService portfolioService;

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
    public CommonResult deleteByIdx(@PathVariable Long portfolioIdx){
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
}
