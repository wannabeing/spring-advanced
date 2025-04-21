package org.example.expert.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.example.expert.domain.common.dto.SuccessResponseDto;
import org.example.expert.domain.user.dto.request.UserRoleChangeRequest;
import org.example.expert.domain.user.service.UserAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAdminController {

    private final UserAdminService userAdminService;

    @PatchMapping("/admin/users/{userId}")
    public ResponseEntity<SuccessResponseDto<Void>> changeUserRole(
        @PathVariable long userId,
        @RequestBody UserRoleChangeRequest userRoleChangeRequest,
        HttpServletRequest httpRequest) {

        userAdminService.changeUserRole(userId, userRoleChangeRequest);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.OK,
            httpRequest.getRequestURI(),
            "유저의 권한을 성공적으로 변경하였습니다.",
            null
        );
    }
}
