package com.study.schedule.others.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class Filter implements jakarta.servlet.Filter {
    private static final String[] WHITE_LIST = {"/","/users/signup","/auth/login","/auth/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String servletRequestURI = httpServletRequest.getRequestURI();
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if(!isWhiteList(servletRequestURI)) {
            HttpSession session = httpServletRequest.getSession(false);

            if(session == null || session.getAttribute("sessionKey") == null) {
                throw new RuntimeException("로그인 해주세요.");
            }

            // 로그인 성공 로직

        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST,requestURI);
    }

}
