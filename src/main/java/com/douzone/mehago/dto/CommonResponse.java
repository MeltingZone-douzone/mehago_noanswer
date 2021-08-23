package com.douzone.mehago.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CommonResponse<T> {

    private String result;
    private T data;
    private String message;

    private CommonResponse() {}

    private CommonResponse(T data) {
        this.result = "success";
        this.data = data;
    }

    private CommonResponse(String message) {
        this.result = "fail";
        this.message = message;
    }

    public static CommonResponse success(Object data) {
        return new CommonResponse<Object>(data);
    }

    public static CommonResponse fail(String message) {
        return new CommonResponse(message);
    }
}