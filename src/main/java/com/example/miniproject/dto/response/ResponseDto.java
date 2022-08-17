package com.example.miniproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ResponseDto<T>{
    HttpStatus httpStatus;
    T data;


}
