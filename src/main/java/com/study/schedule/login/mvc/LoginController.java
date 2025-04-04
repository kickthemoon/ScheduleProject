package com.study.schedule.login.mvc;

import com.study.schedule.login.dto.LoginRequestDto;
import com.study.schedule.login.dto.LoginResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody @Valid LoginRequestDto requsetDto,
            HttpSession session,
            HttpServletResponse response
    ) {

        loginService.login(requsetDto.getUsername(), requsetDto.getPassword(),session,response);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {

        loginService.logout(request,response);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
