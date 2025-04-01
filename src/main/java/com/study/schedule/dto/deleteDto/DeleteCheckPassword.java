package com.study.schedule.dto.deleteDto;

import lombok.Getter;

@Getter
public class DeleteCheckPassword {

    private final String checkPassword;

    public DeleteCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}

