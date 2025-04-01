package com.study.schedule.dto.scheduleDto;

import com.study.schedule.entity.Schedule;
import com.study.schedule.entity.User;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final User userid;

    public ScheduleResponseDto(Long id, String title, String contents, User userid) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userid = userid;
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(),schedule.getContents(),schedule.getUserId());
    }
}
