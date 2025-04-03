package com.study.schedule.login.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {

    private final String username;
    private final String password;

    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
