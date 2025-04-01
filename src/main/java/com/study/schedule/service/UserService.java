package com.study.schedule.service;

import com.study.schedule.dto.userDto.UserResponseDto;
import com.study.schedule.entity.User;
import com.study.schedule.repository.UserRepository;
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
        User user = new User(username,password,email);

        User saveUser = userRepository.save(user);

        return new UserResponseDto(saveUser.getId(),saveUser.getUsername(),saveUser.getEmail());
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }

    public UserResponseDto findById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(findUser.getId(), findUser.getUsername(),findUser.getEmail());
    }

    @Transactional
    public void updateUsernameAndEmail(Long id, String newUsername, String newEmail, String checkPassword) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        // 입력 받는 비밀번호가 저장된 비밀번호와 같은지 판별
        if(!findUser.getPassword().equals(checkPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 입력이 틀렸습니다.");
        }

        findUser.updateUsernameAndEmail(newUsername,newEmail);
    }

    public void delete(Long id, String checkPassword) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        if(!findUser.getPassword().equals(checkPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 입력이 틀렸습니다.");
        }

        userRepository.delete(findUser);
    }
}
