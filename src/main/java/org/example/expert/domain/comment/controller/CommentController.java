package org.example.expert.domain.comment.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.dto.request.CommentSaveRequest;
import org.example.expert.domain.comment.dto.response.CommentResponse;
import org.example.expert.domain.comment.dto.response.CommentSaveResponse;
import org.example.expert.domain.comment.service.CommentService;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.common.dto.SuccessResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/todos/{todoId}/comments")
    public ResponseEntity<SuccessResponseDto<CommentSaveResponse>> saveComment(
        @Auth AuthUser authUser,
        @PathVariable long todoId,
        @Valid @RequestBody CommentSaveRequest commentSaveRequest,
        HttpServletRequest httpRequest
    ) {

        CommentSaveResponse response = commentService.saveComment(authUser, todoId, commentSaveRequest);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.CREATED,
            httpRequest.getRequestURI(),
            "성공적으로 댓글을 작성하였습니다.",
            response
        );
    }

    @GetMapping("/todos/{todoId}/comments")
    public ResponseEntity<SuccessResponseDto<List<CommentResponse>>> getComments(
        @PathVariable long todoId,
        HttpServletRequest httpRequest) {

        List<CommentResponse> response = commentService.getComments(todoId);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.OK,
            httpRequest.getRequestURI(),
            "성공적으로 댓글을 조회하였습니다.",
            response
        );
    }
}
