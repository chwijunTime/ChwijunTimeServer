package com.gsm.chwijuntime.controller.exception;

import com.gsm.chwijuntime.advice.exception.*;
import com.gsm.chwijuntime.model.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/entrypoint")
    public CommonResult entrypointException() {
        throw new CAuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied")
    public CommonResult accessdeniedException() {
        throw new AccessDeniedException("");
    }

    @GetMapping(value = "/UserDuplicationException")
    public CommonResult UserDuplicationException() {
        throw new UserDuplicationException();
    }

    @GetMapping(value = "/EmailNotFoundException")
    public CommonResult EmailNotFoundException() {
        throw new EmailNotFoundException();
    }

    @GetMapping(value = "/NotFoundTagException")
    public CommonResult NotFoundTagException() {
        throw new NotFoundTagException();
    }

    @GetMapping(value = "/NotFoundContractingCompanyException")
    public CommonResult NotFoundContractingCompanyException() {
        throw new NotFoundContractingCompanyException();
    }

}
