package org.example.expert.domain.manager.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ManagerSaveRequest {

    @NotNull(message = "담당자 아이디 입력은 필수입니다.")
    @Size(min = 1, message = "유효한 담당자 아이디 범위를 입력해주세요.")
    private final Long managerUserId; // 일정 작성자가 배치하는 유저 id
}
