package com.douzone.mehago.utils;

public class InvalidJwtException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidJwtException(String msg) {
        super(msg);
    }
    
}
