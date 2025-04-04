package com.study.schedule.schdule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    @NotBlank
    private final String title;

    private final String contents;

    @NotNull
    @Size(min = 4, max = 13)
    private final String passowrd;

    private final Long userId;

    public ScheduleRequestDto(String title, String contents, String password, Long userId) {
        this.title = title;
        this.contents = contents;
        this.passowrd = password;
        this.userId = userId;
    }
}
