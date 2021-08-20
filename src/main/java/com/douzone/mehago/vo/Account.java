package com.douzone.mehago.vo;

import lombok.Data;

@Data
public class Account {

    private Long no;

    private String email;
    private String password;
    private String nickName;
    private int phoneNumber;
    // created_at
}
