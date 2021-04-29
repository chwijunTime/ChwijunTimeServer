package com.gsm.chwijuntime.advice;

import com.gsm.chwijuntime.advice.exception.*;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(CAuthenticationEntryPointException.class)
    public CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("entryPointException.code")), getMessage("entryPointException.msg"));
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult AccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("accessDenied.code")), getMessage("accessDenied.msg"));
    }

    //유저 중복 Exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserDuplicationException.class)
    public CommonResult UserDuplicationException(HttpServletRequest request, UserDuplicationException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("UserDuplicationException.code")), getMessage("UserDuplicationException.msg"));
    }

    //유저 이메일 없음 Exception
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmailNotFoundException.class)
    public CommonResult EmailNotFoundException(HttpServletRequest request, EmailNotFoundException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("EmailNotFoundException.code")), getMessage("EmailNotFoundException.msg"));
    }

    // 태그 없음
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundTagException.class)
    public CommonResult NotFoundTagException(HttpServletRequest request, NotFoundTagException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundTagException.code")), getMessage("NotFoundTagException.msg"));
    }

    // 협약 업체 찾지 못함
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundContractingCompanyException.class)
    public CommonResult NotFoundTagException(HttpServletRequest request, NotFoundContractingCompanyException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundContractingCompanyException.code")), getMessage("NotFoundContractingCompanyException.msg"));
    }

    // 작성자 권한이 아님
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorNotCertifiedException.class)
    public CommonResult AuthorNotCertifiedException(HttpServletRequest request, AuthorNotCertifiedException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("AuthorNotCertifiedException.code")), getMessage("AuthorNotCertifiedException.msg"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundCompanyReviewException.class)
    public CommonResult NotFoundCompanyReview(HttpServletRequest request, NotFoundCompanyReviewException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundCompanyReviewException.code")), getMessage("NotFoundCompanyReviewException.msg"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateContractingCompanyException.class)
    public CommonResult NotFoundCompanyReview(HttpServletRequest request, DuplicateContractingCompanyException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("DuplicateContractingCompanyException.code")), getMessage("DuplicateContractingCompanyException.msg"));
    }

    // 공지사항 못 찾음
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundNoticeException.class)
    public CommonResult NotFoundNoticeException(HttpServletRequest request, NotFoundNoticeException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundNoticeException.code")), getMessage("NotFoundNoticeException.msg"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequestAlreadyApprovedException.class)
    public CommonResult RequestAlreadyApprovedException(HttpServletRequest request, RequestAlreadyApprovedException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("RequestAlreadyApprovedException.code")), getMessage("RequestAlreadyApprovedException.msg"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequestAlreadyRejectedException.class)
    public CommonResult RequestAlreadyRejectedException(HttpServletRequest request, RequestAlreadyRejectedException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("RequestAlreadyRejectedException.code")), getMessage("RequestAlreadyRejectedException.msg"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundEmploymentAnnouncementException.class)
    public CommonResult NotFoundEmploymentAnnouncementException(HttpServletRequest request, NotFoundEmploymentAnnouncementException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundEmploymentAnnouncementException.code")), getMessage("NotFoundEmploymentAnnouncementException.msg"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundApplicationEmploymentException.class)
    public CommonResult NotFoundApplicationEmploymentException(HttpServletRequest request, NotFoundApplicationEmploymentException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundApplicationEmploymentException.code")), getMessage("NotFoundApplicationEmploymentException.msg"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult processValidationError(MethodArgumentNotValidException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("MethodArgumentNotValidException.code")), e.getAllErrors().get(0).getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectPasswordException.class)
    public CommonResult IncorrectPasswordException(HttpServletRequest request, IncorrectPasswordException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("IncorrectPasswordException.code")), getMessage("IncorrectPasswordException.msg"));
    }
}
