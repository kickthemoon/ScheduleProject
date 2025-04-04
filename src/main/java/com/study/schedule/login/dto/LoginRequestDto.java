package com.study.schedule.login.dto;

import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Getter
public class LoginRequestDto {

    @NotBlank
    private final String username;

    @NotNull
    @Range(min = 4, max = 13)
    private final String password;

    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
