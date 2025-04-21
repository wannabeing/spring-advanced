package org.example.expert.domain.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.auth.dto.request.SigninRequest;
import org.example.expert.domain.auth.dto.request.SignupRequest;
import org.example.expert.domain.auth.dto.response.SigninResponse;
import org.example.expert.domain.auth.dto.response.SignupResponse;
import org.example.expert.domain.auth.service.AuthService;
import org.example.expert.domain.common.dto.SuccessResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public ResponseEntity<SuccessResponseDto<SignupResponse>> signup(
        @Valid @RequestBody SignupRequest signupRequest,
        HttpServletRequest httpRequest) {

        SignupResponse signupResponse = authService.signup(signupRequest);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.CREATED,
            httpRequest.getRequestURI(),
            "회원가입에 성공하였습니다.",
            signupResponse
        );
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<SuccessResponseDto<SigninResponse>> signin(
        @Valid @RequestBody SigninRequest signinRequest,
        HttpServletRequest httpRequest) {

        SigninResponse signInResponse = authService.signin(signinRequest);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.OK,
            httpRequest.getRequestURI(),
            "로그인에 성공하였습니다.",
            signInResponse
        );
    }
}
