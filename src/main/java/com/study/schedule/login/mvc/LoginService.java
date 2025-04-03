package com.study.schedule.login.mvc;

import com.study.schedule.others.cookieAndSession.CookieAndSession;
import com.study.schedule.user.entity.UserEntity;
import com.study.schedule.user.mvc.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final CookieAndSession cookieAndSession;
    
    public void login(String username, String password, HttpSession session, HttpServletResponse response) {

        UserEntity findUsername = userRepository.findByUsernameOrElseThrow(username);

        if(!findUsername.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 입력이 틀렸습니다.");
        }

        // 쿠키&세션 쿼리
        cookieAndSession.loginCookie(username, session, response);

    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        cookieAndSession.logoutCookie(request, response);
    }
}
