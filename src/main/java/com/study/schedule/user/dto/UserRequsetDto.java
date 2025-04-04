package com.study.schedule.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class UserRequsetDto {

    @NotBlank
    private final String username;

    @NotNull
    @Range(min = 4, max = 13)
    private final String password;

    @Email
    private final String email;

    public UserRequsetDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
