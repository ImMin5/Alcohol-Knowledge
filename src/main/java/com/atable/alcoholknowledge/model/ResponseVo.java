package com.atable.alcoholknowledge.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ResponseVo<T> implements Serializable {
    private int code;
    private String message;
    private T result;

    public ResponseVo(int statusCode, String message, T result ) {
        this.code = statusCode;
        this.message = message;
        this.result = result;
    }
}
