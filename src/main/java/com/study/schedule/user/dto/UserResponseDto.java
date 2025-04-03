package com.study.schedule.user.dto;

import com.study.schedule.user.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    public UserResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // user 등록
    public static UserResponseDto toDto(UserEntity userEntity) {
        return new UserResponseDto(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail());
    }

}
