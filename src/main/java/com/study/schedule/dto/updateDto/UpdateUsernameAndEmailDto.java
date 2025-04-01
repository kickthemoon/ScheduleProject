package com.study.schedule.dto.updateDto;

import lombok.Getter;

@Getter
public class UpdateUsernameAndEmailDto {
    // 유저 이름과 e메일을 수정함
    // 비밀번호를 입력받고 일치하면 실행되야함
    private final String newUsername;
    private final String newEmail;
    private final String checkPassword;

    public UpdateUsernameAndEmailDto(String newUsername, String newEmail, String checkPassword) {
        this.newUsername = newUsername;
        this.newEmail = newEmail;
        this.checkPassword = checkPassword;
    }
}
