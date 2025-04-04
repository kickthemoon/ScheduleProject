package com.study.schedule.user.mvc;

import com.study.schedule.config.deleteCheck.DeleteCheckPasswordDto;
import com.study.schedule.user.dto.updateDto.UpdateUsernameAndEmailDto;
import com.study.schedule.user.dto.UserRequsetDto;
import com.study.schedule.user.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> singUp(@RequestBody @Valid UserRequsetDto requsetDto) {

        UserResponseDto signUpResponseDto =
                userService.signUp(
                        requsetDto.getUsername(), requsetDto.getPassword(), requsetDto.getEmail()
                );

         return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> userResponseDtoList = userService.findAll();

        return new ResponseEntity<>(userResponseDtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUsernameAndEmail(
            @PathVariable Long id,
            @RequestBody UpdateUsernameAndEmailDto requestDto
            ) {
        userService.updateUsernameAndEmail(id, requestDto.getNewUsername(), requestDto.getNewEmail(), requestDto.getCheckPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody DeleteCheckPasswordDto requestDto, HttpServletRequest request, HttpServletResponse response) {
        // config > deleteCheck > DeleteCheckPassowrdDto

        userService.delete(id,requestDto.getCheckPassword(),request,response);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
