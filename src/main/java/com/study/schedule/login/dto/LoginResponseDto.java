package com.study.schedule.login.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private final String username;
    private final String password;

    public LoginResponseDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
