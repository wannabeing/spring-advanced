package org.example.expert.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.common.dto.SuccessResponseDto;
import org.example.expert.domain.user.dto.request.UserChangePasswordRequest;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<SuccessResponseDto<UserResponse>> getUser(
        @PathVariable long userId,
        HttpServletRequest httpRequest) {

        UserResponse response = userService.getUser(userId);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.OK,
            httpRequest.getRequestURI(),
            "유저를 성공적으로 조회하였습니다.",
            response
        );
    }

    @PutMapping("/users")
    public ResponseEntity<SuccessResponseDto<Void>> changePassword(
        @Auth AuthUser authUser,
        @RequestBody UserChangePasswordRequest userChangePasswordRequest,
        HttpServletRequest httpRequest) {

        userService.changePassword(authUser.getId(), userChangePasswordRequest);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.OK,
            httpRequest.getRequestURI(),
            "유저의 비밀번호를 성공적으로 변경하였습니다.",
            null
        );
    }
}
