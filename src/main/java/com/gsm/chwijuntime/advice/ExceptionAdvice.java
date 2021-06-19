package com.gsm.chwijuntime.advice;

import com.gsm.chwijuntime.advice.exception.*;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    // Code정보에 해당하는 메시지를 조회합니다
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    // Code정보, 추가 Argument로 현재 Locale에 맞는 메시지를 조회합니다
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(Exception.class)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("unKnown.code")), e.getMessage());
    }

    // 로그인이 안됬을 경우
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(CAuthenticationEntryPointException.class)
    public CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("entryPointException.code")), getMessage("entryPointException.msg"));
    }

    // 리소스 접근 권한이 없을 경우
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult AccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("accessDenied.code")), getMessage("accessDenied.msg"));
    }

    //유저가 중복될 경우
    @ExceptionHandler(UserDuplicationException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CommonResult UserDuplicationException(HttpServletRequest request, UserDuplicationException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("UserDuplicationException.code")), getMessage("UserDuplicationException.msg"));
    }

    //유저 이메일을 찾지 못할 경우
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(EmailNotFoundException.class)
    public CommonResult EmailNotFoundException(HttpServletRequest request, EmailNotFoundException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("EmailNotFoundException.code")), getMessage("EmailNotFoundException.msg"));
    }

    // 태그를 찾지 못할 경우
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundTagException.class)
    public CommonResult NotFoundTagException(HttpServletRequest request, NotFoundTagException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundTagException.code")), getMessage("NotFoundTagException.msg"));
    }

    // 협약 업체을 찾지 못한 경우
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundContractingCompanyException.class)
    public CommonResult NotFoundTagException(HttpServletRequest request, NotFoundContractingCompanyException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundContractingCompanyException.code")), getMessage("NotFoundContractingCompanyException.msg"));
    }

    // 작성자 권한이 아닌 경우
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorNotCertifiedException.class)
    public CommonResult AuthorNotCertifiedException(HttpServletRequest request, AuthorNotCertifiedException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("AuthorNotCertifiedException.code")), getMessage("AuthorNotCertifiedException.msg"));
    }

    // 리뷰를 찾지 못한 경우
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundCompanyReviewException.class)
    public CommonResult NotFoundCompanyReview(HttpServletRequest request, NotFoundCompanyReviewException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundCompanyReviewException.code")), getMessage("NotFoundCompanyReviewException.msg"));
    }

    // 협약 업체가 중복된 경우
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(DuplicateContractingCompanyException.class)
    public CommonResult NotFoundCompanyReview(HttpServletRequest request, DuplicateContractingCompanyException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("DuplicateContractingCompanyException.code")), getMessage("DuplicateContractingCompanyException.msg"));
    }

    // 공지사항을 못 찾을 경우
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundNoticeException.class)
    public CommonResult NotFoundNoticeException(HttpServletRequest request, NotFoundNoticeException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundNoticeException.code")), getMessage("NotFoundNoticeException.msg"));
    }

    // 이미 요청이 승인된 경우
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(RequestAlreadyApprovedException.class)
    public CommonResult RequestAlreadyApprovedException(HttpServletRequest request, RequestAlreadyApprovedException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("RequestAlreadyApprovedException.code")), getMessage("RequestAlreadyApprovedException.msg"));
    }

    // 이미 요청이 거절된 경우
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(RequestAlreadyRejectedException.class)
    public CommonResult RequestAlreadyRejectedException(HttpServletRequest request, RequestAlreadyRejectedException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("RequestAlreadyRejectedException.code")), getMessage("RequestAlreadyRejectedException.msg"));
    }

    // 취업 공고를 못 찾을 경우
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundEmploymentAnnouncementException.class)
    public CommonResult NotFoundEmploymentAnnouncementException(HttpServletRequest request, NotFoundEmploymentAnnouncementException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundEmploymentAnnouncementException.code")), getMessage("NotFoundEmploymentAnnouncementException.msg"));
    }

    // 취업 공고 요청을 못 찾을 경우
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundApplicationEmploymentException.class)
    public CommonResult NotFoundApplicationEmploymentException(HttpServletRequest request, NotFoundApplicationEmploymentException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundApplicationEmploymentException.code")), getMessage("NotFoundApplicationEmploymentException.msg"));
    }

    // DTO Valid를 할 경우
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult processValidationError(MethodArgumentNotValidException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("MethodArgumentNotValidException.code")), e.getAllErrors().get(0).getDefaultMessage());
    }

    // 패스워드를 틀릴 경우
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(IncorrectPasswordException.class)
    public CommonResult IncorrectPasswordException(HttpServletRequest request, IncorrectPasswordException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("IncorrectPasswordException.code")), getMessage("IncorrectPasswordException.msg"));
    }

    // 채용 확정 정보를 찾을 수 없을 경우
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundEmploymentConfirmationException.class)
    public CommonResult NotFoundEmploymentConfirmationException(HttpServletRequest request, NotFoundEmploymentConfirmationException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundEmploymentConfirmationException.code")), getMessage("NotFoundEmploymentConfirmationException.msg"));
    }

    // 포트폴리오 정보를 찾을 수 없을 경우
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundPortfolioException.class)
    public CommonResult NotFoundPortfolioException(HttpServletRequest request, NotFoundPortfolioException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundPortfolioException.code")), getMessage("NotFoundPortfolioException.msg"));
    }

    // 이력서 정보를 찾을 수 없는 경우
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundResumeException.class)
    public CommonResult NotFoundResumeException(HttpServletRequest request, NotFoundResumeException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundResumeException.code")), getMessage("NotFoundResumeException.msg"));
    }

    // 태그 삭제 에러
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult IntegrationException(HttpServletRequest request, ConstraintViolationException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("ConstraintViolationException.code")), getMessage("ConstraintViolationException.msg"));
    }

    // 태그 삭제 에러
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public CommonResult DataIntegrityViolationException(HttpServletRequest request, DataIntegrityViolationException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("ConstraintViolationException.code")), getMessage("ConstraintViolationException.msg"));
    }

    // 신청 날짜 만료
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(ApplicationDateExpirationException.class)
    public CommonResult ApplicationDateExpirationException(HttpServletRequest request, ApplicationDateExpirationException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("ApplicationDateExpirationException.code")), getMessage("ApplicationDateExpirationException.msg"));
    }

    // URL 유효성 검사 에러
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(URLValidationException.class)
    public CommonResult URLValidationException(HttpServletRequest request, URLValidationException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("URLValidationException.code")), getMessage("URLValidationException.msg"));
    }

    // 시간 타입 에러
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResult HttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("HttpMessageNotReadableException.code")), getMessage("HttpMessageNotReadableException.msg"));
    }

    // 토큰 만료 에러
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(CExpiredJwtException.class)
    public CommonResult CExpiredJwtException(HttpServletRequest request, CExpiredJwtException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("CExpiredJwtException.code")), getMessage("CExpiredJwtException.msg"));
    }

    // Bearer 토큰을 찾지 못함
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(NotFoundBearer.class)
    public CommonResult NotFoundBearer(HttpServletRequest request, NotFoundBearer e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundBearer.code")), getMessage("NotFoundBearer.msg"));
    }

    // 중복 신청
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(RedundantApplicationException.class)
    public CommonResult RedundantApplicationException(HttpServletRequest request, RedundantApplicationException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("RedundantApplicationException.code")), getMessage("RedundantApplicationException.msg"));
    }

    // 어드민 신청을 찾지 못함
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundConsultingAdminException.class)
    public CommonResult NotFoundConsultingAdminException(HttpServletRequest request, NotFoundConsultingAdminException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundConsultingAdminException.code")), getMessage("NotFoundConsultingAdminException.msg"));
    }

    // 첨삭 신청을 찾지 못함
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundCorrectionApply.class)
    public CommonResult NotFoundCorrectionApply(HttpServletRequest request, NotFoundCorrectionApply e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundCorrectionApply.code")), getMessage("NotFoundCorrectionApply.msg"));
    }

    // 요청 태그를 찾지 못함
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundRequestTagException.class)
    public CommonResult NotFoundRequestTagException(HttpServletRequest request, NotFoundRequestTagException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundRequestTagException.code")), getMessage("NotFoundRequestTagException.msg"));
    }

    // 태그 중복
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DuplicateTagNameException.class)
    public CommonResult DuplicateTagNameException(HttpServletRequest request, DuplicateTagNameException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("DuplicateTagNameException.code")), getMessage("DuplicateTagNameException.msg"));
    }

    // 글자 수 에러
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(DataException.class)
    public CommonResult DataException(HttpServletRequest request, DataException dataException) {
        return responseService.getFailResult(Integer.parseInt(getMessage("DataException.code")), getMessage("DataException.msg"));
    }

    //토큰 에러
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(NoTokenANDTokenWrongException.class)
    public CommonResult NoTokenANDTokenWrongException(HttpServletRequest request, NoTokenANDTokenWrongException e){
        return responseService.getFailResult(Integer.parseInt(getMessage("NoTokenANDTokenWrongException.code")), getMessage("NoTokenANDTokenWrongException.msg"));
    }
}
