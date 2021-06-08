package com.gsm.chwijuntime.controller.exception;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
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

    @GetMapping("/accessdenied")
    public CommonResult AccessDenied() {
        throw new AccessDeniedException("");
    }
    
    @GetMapping("/entrypoint")
    public void EntryPoint() {
        throw new CAuthenticationEntryPointException();
    }
}
