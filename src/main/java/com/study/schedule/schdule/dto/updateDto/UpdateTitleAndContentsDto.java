package com.study.schedule.schdule.dto.updateDto;


import lombok.Getter;

@Getter
public class UpdateTitleAndContentsDto {
    private final String newTitle;
    private final String newContents;
    private final String checkPassword;

    public UpdateTitleAndContentsDto(String newTitle, String newContents, String checkPassword) {
        this.newTitle = newTitle;
        this.newContents = newContents;
        this.checkPassword = checkPassword;
    }
}
