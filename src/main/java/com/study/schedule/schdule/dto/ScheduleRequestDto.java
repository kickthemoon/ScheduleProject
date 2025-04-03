package com.study.schedule.schdule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private final String title;
    private final String contents;
    private final Long userId;

    public ScheduleRequestDto(String title, String contents, Long userId) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;
    }
}
