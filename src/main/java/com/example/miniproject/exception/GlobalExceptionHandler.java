package com.example.miniproject.exception;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.miniproject.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseDto<Boolean> proceedAllException(Exception e){
        return new ResponseDto<>(HttpStatus.EXPECTATION_FAILED, false);
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseDto<Boolean> tokenExpiredException(TokenExpiredException e){
        return new ResponseDto<>(HttpStatus.UNAUTHORIZED, false);
    }

    @ExceptionHandler(value = SignatureVerificationException.class)
    public ResponseDto<Boolean> signatureVerificationException(SignatureVerificationException e){
        return new ResponseDto<>(HttpStatus.BAD_REQUEST, false);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseDto<Boolean> nullPointerException(NullPointerException e){
        final String TOKEN_NULL_EXCEPTION_MESSAGE = "http.HttpServletRequest.getHeader(String)";
        if (e.getMessage().contains(TOKEN_NULL_EXCEPTION_MESSAGE)) // 로그인이 필요한 서비스에 토큰을 입력하지 않은 경우
            return new ResponseDto<>(HttpStatus.UNAUTHORIZED, false);

        return new ResponseDto<>(HttpStatus.EXPECTATION_FAILED, false);
    }
}