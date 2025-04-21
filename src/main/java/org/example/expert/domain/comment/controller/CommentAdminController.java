package org.example.expert.domain.comment.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.example.expert.domain.common.dto.SuccessResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentAdminController {

    private final CommentAdminService commentAdminService;

    @DeleteMapping("/admin/comments/{commentId}")
    public ResponseEntity<SuccessResponseDto<Void>> deleteComment(
        @PathVariable long commentId,
        HttpServletRequest httpRequest) {

        commentAdminService.deleteComment(commentId);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.OK,
            httpRequest.getRequestURI(),
            "성공적으로 댓글을 삭제하였습니다.",
            null
        );
    }
}
