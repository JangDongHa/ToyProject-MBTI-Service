package com.example.miniproject.exception;


public class ExceptionNamingHandler {
    public static final String USERNAME_ERROR = "닉네임은 최소 4자 이상, 12자 이하 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성되어야 합니다.";
    public static final String PASSWORD_ERROR = "비밀번호는 최소 4자 이상이며, 32자 이하 알파벳 소문자(a~z), 숫자(0~9) 로 구성되어야 합니다.";
    public static final String NICKNAME_OVERLAP_ERROR = "중복된 닉네임입니다.";
}