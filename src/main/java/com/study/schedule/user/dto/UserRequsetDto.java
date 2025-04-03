package com.study.schedule.user.dto;

import lombok.Getter;

@Getter
public class UserRequsetDto {

    private final String username;

    private final String password;

    private final String email;

    public UserRequsetDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
