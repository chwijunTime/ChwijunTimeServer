package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.service.dev.DevService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = {"Dev"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class DevController {

    private final ResponseService responseService;
    private final DevService devService;

    @GetMapping("/abdodn/check/permissions")
    @ResponseBody
    public Map<String, String> CheckPermissions(@RequestParam String email){
        Map<String, String> stringStringMap = devService.PermissionCheck(email);
        return stringStringMap;
    }

    @PostMapping("/duene/change/permissions")
    @ResponseBody
    public CommonResult ChangePermissions(@RequestParam String email){
        devService.ChangePermissions(email);
        return responseService.getSuccessResult();
    }

}
