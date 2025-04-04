package com.study.schedule.config.deleteCheck;

import lombok.Getter;

@Getter
public class DeleteCheckPasswordDto {

    private final String checkPassword;

    public DeleteCheckPasswordDto(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}

