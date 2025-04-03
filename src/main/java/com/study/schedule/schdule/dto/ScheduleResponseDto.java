package com.study.schedule.schdule.dto;

import com.study.schedule.schdule.entity.ScheduleEntity;
import com.study.schedule.user.entity.UserEntity;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final UserEntity userid;

    public ScheduleResponseDto(Long id, String title, String contents, UserEntity userid) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userid = userid;
    }

    public static ScheduleResponseDto toDto(ScheduleEntity scheduleEntity) {
        return new ScheduleResponseDto(scheduleEntity.getId(), scheduleEntity.getTitle(), scheduleEntity.getContents(), scheduleEntity.getUserEntityId());
    }
}
