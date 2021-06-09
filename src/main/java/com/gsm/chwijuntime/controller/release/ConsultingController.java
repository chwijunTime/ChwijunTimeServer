package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.consultingadmin.ConsultingAdminResDto;
import com.gsm.chwijuntime.dto.consultingadmin.ConsultingAdminSaveDto;
import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserResDto;
import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserSaveDto;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.consultingadmin.ConsultingAdminService;
import com.gsm.chwijuntime.service.consultinguser.ConsultingUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"10. 상담"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class ConsultingController {

    private final ConsultingAdminService consultingAdminService;
    private final ResponseService responseService;
    private final ConsultingUserService consultingUserService;

    @ApiOperation(value = "관리자 상담 등록", notes = "관리자가 상담을 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/admin/consulting-admin")
    public CommonResult saveConsulting(@RequestBody ConsultingAdminSaveDto consultingAdminSaveDto) {
        consultingAdminService.saveConsultingAdmin(consultingAdminSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "사용자 상담 전체 조회", notes = "사용자가 신청할 수 있는 상담을 전체 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/consulting-admin")
    public ListResult<ConsultingAdminResDto> userFindAll() {
        List<ConsultingAdminResDto> all = consultingAdminService.findAll();
        return responseService.getListResult(all);
    }

    @ApiOperation(value = "사용자 상담 단일 조회", notes = "사용자가 상담을 단일 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/consulting-admin/{consultingIdx}")
    public SingleResult<ConsultingAdminResDto> findByIdx(@PathVariable Long consultingIdx) {
        ConsultingAdminResDto byIdx = consultingAdminService.findByIdx(consultingIdx);
        return responseService.getSingleResult(byIdx);
    }

    @ApiOperation(value = "관리자 상담 삭제", notes = "관리자가 상담을 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @DeleteMapping("/admin/consulting-admin/{consultingIdx}")
    public CommonResult deleteByIdx(@PathVariable Long consultingIdx) {
        consultingAdminService.deleteConsultingAdmin(consultingIdx);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "사용자 상담 신청", notes = "사용자가 상담을 신청한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/consulting-user")
    public CommonResult saveConsulting(@RequestParam Long idx, @RequestBody ConsultingUserSaveDto consultingUserSaveDto) {
        consultingUserService.saveConsultingUser(idx, consultingUserSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "관리자 상담 신청 전체 조회", notes = "관리자가 상담 신청을 전체 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/admin/consulting-user")
    public ListResult<ConsultingUserResDto> findAll() {
        List<ConsultingUserResDto> all = consultingUserService.findAll();
        return responseService.getListResult(all);
    }
}
