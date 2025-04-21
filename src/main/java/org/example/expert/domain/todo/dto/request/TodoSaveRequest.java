package org.example.expert.domain.todo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TodoSaveRequest {

    @NotBlank(message = "제목은 필수 입력입니다.")
    private final String title;
    @NotBlank(message = "내용은 필수 입력입니다.")
    private final String contents;
}
