package com.study.schedule.others.dto.deleteDto;

import lombok.Getter;

@Getter
public class DeleteCheckPassword {

    private final String checkPassword;

    public DeleteCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}

