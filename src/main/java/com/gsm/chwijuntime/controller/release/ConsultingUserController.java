package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.consultingadmin.ConsultingAdminSaveDto;
import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserResDto;
import com.gsm.chwijuntime.dto.consultinguser.ConsultingUserSaveDto;
import com.gsm.chwijuntime.model.ConsultingUser;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.service.consultinguser.ConsultingUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"12. 상담 신청"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class ConsultingUserController {

    private final ConsultingUserService consultingUserService;
    private final ResponseService responseService;

    @ApiOperation(value = "상담 신청", notes = "사용자가 상담을 신청한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @PostMapping("/consulting-user")
    public CommonResult saveConsulting(@RequestParam Long idx, @RequestBody ConsultingUserSaveDto consultingUserSaveDto) {
        consultingUserService.saveConsultingUser(idx, consultingUserSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "상담 신청 전체 조회", notes = "관리자가 상담 신청을 전체 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @GetMapping("/consulting-user")
    public ListResult<ConsultingUserResDto> findAll() {
        List<ConsultingUserResDto> all = consultingUserService.findAll();
        return responseService.getListResult(all);
    }
}
