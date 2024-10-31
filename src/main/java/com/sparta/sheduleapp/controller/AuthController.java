package com.sparta.sheduleapp.controller;

import com.sparta.sheduleapp.dto.AuthRequestDto;
import com.sparta.sheduleapp.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody AuthRequestDto requestDto, HttpServletResponse response){
        authService.signUp(requestDto, response);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDto requestDto, HttpServletResponse response){
        authService.login(requestDto, response);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("로그인 완료");
    }
}