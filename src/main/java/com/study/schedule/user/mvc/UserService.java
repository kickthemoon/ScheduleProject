package com.study.schedule.user.mvc;

import com.study.schedule.config.encoder.PasswordEncoder;
import com.study.schedule.config.cookie.CookieService;
import com.study.schedule.user.entity.UserEntity;
import com.study.schedule.user.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CookieService cookieService;

    public UserResponseDto signUp(String username, String password, String email) {

        String encodePassword = passwordEncoder.encode(password);

        UserEntity userEntity = new UserEntity(username,encodePassword,email);

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

        passwordEncoder.matches(checkPassword, findUserEntity.getPassword());

        findUserEntity.updateUsernameAndEmail(newUsername,newEmail);
    }

    public void delete(Long id, String checkPassword, HttpServletRequest request, HttpServletResponse response) {
        UserEntity findUserEntity = userRepository.findByIdOrElseThrow(id);

        passwordEncoder.matches(checkPassword, findUserEntity.getPassword());

        // 유저 정보 삭제와 동시에 로그아웃
        cookieService.logoutCookie(request,response);

        userRepository.delete(findUserEntity);
    }
}
