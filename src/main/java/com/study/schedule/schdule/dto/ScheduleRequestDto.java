package com.study.schedule.schdule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private final String title;
    private final String contents;
    private final String passowrd;
    private final Long userId;

    public ScheduleRequestDto(String title, String contents, String password, Long userId) {
        this.title = title;
        this.contents = contents;
        this.passowrd = password;
        this.userId = userId;
    }
}
