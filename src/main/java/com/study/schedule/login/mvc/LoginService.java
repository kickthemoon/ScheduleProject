package com.study.schedule.login.mvc;

import com.study.schedule.config.encoder.PasswordEncoder;
import com.study.schedule.config.cookie.CookieService;
import com.study.schedule.user.entity.UserEntity;
import com.study.schedule.user.mvc.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final CookieService cookieService;
    private final PasswordEncoder passwordEncoder;
    
    public void login(@Valid String username, String password, HttpSession session, HttpServletResponse response) {

        UserEntity findUsername = userRepository.findByUsernameOrElseThrow(username);

        passwordEncoder.matches(password, findUsername.getPassword());

        cookieService.loginCookie(username, session, response);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        cookieService.logoutCookie(request, response);
    }
}
