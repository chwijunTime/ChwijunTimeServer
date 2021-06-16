package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanyResDto;
import com.gsm.chwijuntime.dto.contractingcompany.ContractingCompanySaveDto;
import com.gsm.chwijuntime.dto.contractingcompany.ContractionCompanyUpdateDto;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.contractingcompany.ContractingCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"3. 협약 업체"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class ContractingCompanyController {

    private final ContractingCompanyService contractingCompanyService;
    private final ResponseService responseService;


    @ApiOperation(value = "관리자 협약 업체 등록", notes = "관리자가 협약 업체를 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/admin/contracting-company")
    public CommonResult join(@Valid @RequestBody ContractingCompanySaveDto contractingCompanySaveDto) {
        contractingCompanyService.insertContractingCompany(contractingCompanySaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "사용자 협약 업체 전체 조회", notes = "관리자와 사용자가 협약 업체를 모두 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/contracting-company")
    public ListResult<ContractingCompanyResDto> findAll() {
        List<ContractingCompanyResDto> contractingCompanies = contractingCompanyService.findAllContractingCompany();
        return responseService.getListResult(contractingCompanies);
    }

    @ApiOperation(value = "사용자 협약 업체 단일 조회", notes = "관리자와 사용자가 협약 업체를 단일 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/contracting-company/{companyidx}")
    public SingleResult<ContractingCompanyResDto> findIdx(@PathVariable Long companyidx) {
        ContractingCompanyResDto contractingCompanyResDto = contractingCompanyService.findByContractingCompanyIdx(companyidx);
        return responseService.getSingleResult(contractingCompanyResDto);
    }

    @ApiOperation(value = "관리자 협약 업체 삭제", notes = "관리자가 협약 업체를 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @DeleteMapping("/admin/contracting-company/{companyidx}")
    public CommonResult deleteById(@PathVariable Long companyidx) {
        contractingCompanyService.deleteContractingCompanyIdx(companyidx);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "관리자 협약 업체 수정", notes = "관리자가 협약 업체를 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PutMapping("/admin/contracting-company/{companyidx}")
    public CommonResult updateByIdx(@PathVariable Long companyidx, @Valid @RequestBody ContractionCompanyUpdateDto contractionCompanyUpdateDto) {
        contractingCompanyService.updateContractingCompany(companyidx, contractionCompanyUpdateDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "관리자 협약 업체 키워드 검색 (이름 및 지역)", notes = "사용자가 키워드 검색을 한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/contracting-company-keyword")
    public ListResult<ContractingCompanyResDto> updateByIdx(@RequestParam String keyword) {
        List<ContractingCompanyResDto> byContractingBusinessAreasORContractingCompanyName = contractingCompanyService.findByContractingBusinessAreasORContractingCompanyName(keyword);
        return responseService.getListResult(byContractingBusinessAreasORContractingCompanyName);
    }
}
