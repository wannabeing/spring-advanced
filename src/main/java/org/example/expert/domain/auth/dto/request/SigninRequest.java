package org.example.expert.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SigninRequest {
    
    @NotBlank(message = "이메일은 필수 입력입니다.") @Email(message = "올바른 이메일 형식을 입력해주세요.")
    private final String email;
    @NotBlank(message = "비밀번호는 필수 입력입니다.") @Size(max = 20, message = "비밀번호는 20자 이내로 입력해주세요.")
    private final String password;
}
