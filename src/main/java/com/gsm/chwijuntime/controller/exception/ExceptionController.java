package com.gsm.chwijuntime.controller.exception;

import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"에러 - Exception"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class ExceptionController {

    private final ResponseService responseService;

    @GetMapping("/accessdenied")
    public CommonResult AccessDenied() {
        return responseService.getFailResult(403, "관리자의 권한이 필요합니다.");
    }
    
    @GetMapping("/entrypoint")
    public CommonResult EntryPoint() {
        return responseService.getFailResult(401, "로그인이 필요합니다.");
    }
}
