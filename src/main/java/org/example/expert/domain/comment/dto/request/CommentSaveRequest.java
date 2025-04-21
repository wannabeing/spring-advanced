package org.example.expert.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentSaveRequest {

    @NotBlank(message = "댓글 작성을 필수입니다.")
    @Size(min = 3, message = "댓글은 최소 3자 이상입니다.")
    private final String contents;
}
