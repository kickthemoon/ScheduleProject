package com.study.schedule.others.cookieAndSession;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CookieAndSession {

    public void loginCookie(String username, HttpSession session, HttpServletResponse response) {

        session.setAttribute("user", username);

        Cookie cookie = new Cookie("SESSIONID", session.getId());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    public void logoutCookie(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("SESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
