package com.gsm.chwijuntime.advice;

import com.gsm.chwijuntime.advice.exception.*;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    // code정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    // code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회합니다.
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    public CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("entryPointException.code")), getMessage("entryPointException.msg"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult AccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("accessDenied.code")), getMessage("accessDenied.msg"));
    }

    //유저 중복 Exception
    @ExceptionHandler(UserDuplicationException.class)
    public CommonResult UserDuplicationException(HttpServletRequest request, UserDuplicationException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("UserDuplicationException.code")), getMessage("UserDuplicationException.msg"));
    }

    //유저 이메일 없음 Exception
    @ExceptionHandler(EmailNotFoundException.class)
    public CommonResult EmailNotFoundException(HttpServletRequest request, EmailNotFoundException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("EmailNotFoundException.code")), getMessage("EmailNotFoundException.msg"));
    }

    // 태그 없음
    @ExceptionHandler(NotFoundTagException.class)
    public CommonResult NotFoundTagException(HttpServletRequest request, NotFoundTagException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("NotFoundTagException.code")), getMessage("NotFoundTagException.msg"));
    }

    // 협약 업체 찾지 못함
    @ExceptionHandler(NotFoundContractingCompanyException.class)
    public CommonResult NotFoundTagException(HttpServletRequest request, NotFoundContractingCompanyException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("NotFoundContractingCompanyException.code")), getMessage("NotFoundContractingCompanyException.msg"));
    }

    // 작성자 권한이 아님
    @ExceptionHandler(AuthorNotCertifiedException.class)
    public CommonResult AuthorNotCertifiedException(HttpServletRequest request, AuthorNotCertifiedException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("AuthorNotCertifiedException.code")), getMessage("AuthorNotCertifiedException.msg"));
    }

    @ExceptionHandler(NotFoundCompanyReviewException.class)
    public CommonResult NotFoundCompanyReview(HttpServletRequest request, NotFoundCompanyReviewException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("NotFoundCompanyReviewException.code")), getMessage("NotFoundCompanyReviewException.msg"));
    }

    @ExceptionHandler(DuplicateContractingCompanyException.class)
    public CommonResult NotFoundCompanyReview(HttpServletRequest request, DuplicateContractingCompanyException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("DuplicateContractingCompanyException.code")), getMessage("DuplicateContractingCompanyException.msg"));
    }

    // 공지사항 못 찾음
    @ExceptionHandler(NotFoundNoticeException.class)
    public CommonResult NotFoundNoticeException(HttpServletRequest request, NotFoundNoticeException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("NotFoundNoticeException.code")), getMessage("NotFoundNoticeException.msg"));
    }
}
