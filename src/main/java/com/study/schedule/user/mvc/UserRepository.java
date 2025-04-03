package com.study.schedule.user.mvc;

import com.study.schedule.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    default UserEntity findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저는 없습니다."));
    }

    // Optional<User> findUserById(Long userId);
    Optional<UserEntity> findById(Long id);

    default UserEntity findByUserIdOrElseThrow(Long userId) {
        return findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 유저를 찾을 수 없습니다."));
    }

    Optional<UserEntity> findByUsername(String username);

    default UserEntity findByUsernameOrElseThrow(String username) {
        return findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"로그인 정보를 찾을 수 없습니다."));
    }
}
