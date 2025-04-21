package org.example.expert.domain.manager.controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.config.JwtUtil;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.common.dto.SuccessResponseDto;
import org.example.expert.domain.manager.dto.request.ManagerSaveRequest;
import org.example.expert.domain.manager.dto.response.ManagerResponse;
import org.example.expert.domain.manager.dto.response.ManagerSaveResponse;
import org.example.expert.domain.manager.service.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    private final JwtUtil jwtUtil;

    @PostMapping("/todos/{todoId}/managers")
    public ResponseEntity<SuccessResponseDto<ManagerSaveResponse>> saveManager(
        @Auth AuthUser authUser,
        @PathVariable long todoId,
        @Valid @RequestBody ManagerSaveRequest managerSaveRequest,
        HttpServletRequest httpRequest
    ) {

        ManagerSaveResponse response = managerService.saveManager(authUser, todoId, managerSaveRequest);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.CREATED,
            httpRequest.getRequestURI(),
            "성공적으로 담당자를 등록하였습니다.",
            response
        );
    }

    @GetMapping("/todos/{todoId}/managers")
    public ResponseEntity<SuccessResponseDto<List<ManagerResponse>>> getMembers(
        @PathVariable long todoId,
        HttpServletRequest httpRequest) {

        List<ManagerResponse> response = managerService.getManagers(todoId);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.OK,
            httpRequest.getRequestURI(),
            "성공적으로 매니저 리스트를 조회하였습니다.",
            response
        );
    }

    @DeleteMapping("/todos/{todoId}/managers/{managerId}")
    public ResponseEntity<SuccessResponseDto<Void>> deleteManager(
        @RequestHeader("Authorization") String bearerToken,
        @PathVariable long todoId,
        @PathVariable long managerId,
        HttpServletRequest httpRequest
    ) {
        Claims claims = jwtUtil.extractClaims(bearerToken.substring(7));
        long userId = Long.parseLong(claims.getSubject());
        managerService.deleteManager(userId, todoId, managerId);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.OK,
            httpRequest.getRequestURI(),
            "성공적으로 담당자를 삭제하였습니다.",
            null
        );
    }
}
