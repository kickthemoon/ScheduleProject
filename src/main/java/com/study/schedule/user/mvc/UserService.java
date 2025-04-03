package com.study.schedule.user.mvc;

import com.study.schedule.user.entity.UserEntity;
import com.study.schedule.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto signUp(String username, String password, String email) {
        UserEntity userEntity = new UserEntity(username,password,email);

        UserEntity saveUserEntity = userRepository.save(userEntity);

        return new UserResponseDto(saveUserEntity.getId(), saveUserEntity.getUsername(), saveUserEntity.getEmail());
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }

    public UserResponseDto findById(Long id) {
        UserEntity findUserEntity = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(findUserEntity.getId(), findUserEntity.getUsername(), findUserEntity.getEmail());
    }

    @Transactional
    public void updateUsernameAndEmail(Long id, String newUsername, String newEmail, String checkPassword) {
        UserEntity findUserEntity = userRepository.findByIdOrElseThrow(id);

        // 입력 받는 비밀번호가 저장된 비밀번호와 같은지 판별
        if(!findUserEntity.getPassword().equals(checkPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 입력이 틀렸습니다.");
        }

        findUserEntity.updateUsernameAndEmail(newUsername,newEmail);
    }

    public void delete(Long id, String checkPassword) {
        UserEntity findUserEntity = userRepository.findByIdOrElseThrow(id);

        if(!findUserEntity.getPassword().equals(checkPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 입력이 틀렸습니다.");
        }

        userRepository.delete(findUserEntity);
    }
}
