package com.douzone.mehago.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class Account {

    private Long no;

    @NotNull
    @Length(max = 30)
    @Email
    private String email;

    @NotNull
    @Length(min = 8, max = 10)
    private String password;

    @NotNull
    @Length(min = 2, max = 20)
    private String nickname;

    @NotNull
    @Length(min = 2, max = 10)
    private String name;

    @NotNull
    @Length(max = 11)
    private String phoneNumber;
    private String thumbnailUrl;
    // created_at
}
