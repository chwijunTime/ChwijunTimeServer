package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.consultingadmin.ConsultingAdminResDto;
import com.gsm.chwijuntime.dto.consultingadmin.ConsultingAdminSaveDto;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.consultingadmin.ConsultingAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"11. 상담 등록"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class ConsultingAdminController {

    private final ConsultingAdminService consultingAdminService;
    private final ResponseService responseService;

    @ApiOperation(value = "상담 등록", notes = "관리자가 상담을 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/consulting-admin")
    public CommonResult saveConsulting(@RequestBody ConsultingAdminSaveDto consultingAdminSaveDto) {
        consultingAdminService.saveConsultingAdmin(consultingAdminSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "상담 사용자 전체 조회", notes = "사용자가 신청할 수 있는 상담을 전체 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/consulting-admin")
    public ListResult<ConsultingAdminResDto> userFindAll() {
        List<ConsultingAdminResDto> all = consultingAdminService.findAll();
        return responseService.getListResult(all);
    }

    @ApiOperation(value = "상담 단일 조회", notes = "사용자가 상담을 단일 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/consulting-admin/{consultingIdx}")
    public SingleResult<ConsultingAdminResDto> findByIdx(@PathVariable Long consultingIdx) {
        ConsultingAdminResDto byIdx = consultingAdminService.findByIdx(consultingIdx);
        return responseService.getSingleResult(byIdx);
    }

    @ApiOperation(value = "상담 삭제", notes = "관리자가 상담을 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @DeleteMapping("/consulting-admin/{consultingIdx}")
    public CommonResult deleteByIdx(@PathVariable Long consultingIdx) {
        consultingAdminService.deleteConsultingAdmin(consultingIdx);
        return responseService.getSuccessResult();
    }
}
