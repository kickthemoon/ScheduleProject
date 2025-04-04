package com.study.schedule.user.dto.updateDto;

import lombok.Getter;

@Getter
public class UpdateUsernameAndEmailDto {

    private final String newUsername;
    private final String newEmail;
    private final String checkPassword;

    public UpdateUsernameAndEmailDto(String newUsername, String newEmail, String checkPassword) {
        this.newUsername = newUsername;
        this.newEmail = newEmail;
        this.checkPassword = checkPassword;
    }
}
