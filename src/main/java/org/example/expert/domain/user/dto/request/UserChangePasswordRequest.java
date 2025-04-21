package org.example.expert.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserChangePasswordRequest {

    @NotBlank(message = "현재 비밀번호는 필수 값입니다.")
    private String oldPassword;

    @NotBlank(message = "새 비밀번호는 필수 값입니다.")
    @Size(min = 8, message = "새 비밀번호는 최소 8자 이상입니다.")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*\\d).*$",
        message = "새 비밀번호는 숫자와 대문자를 포함해야 합니다."
    )
    private String newPassword;
}
