package org.example.expert.domain.user.dto.request;

import org.example.expert.domain.user.enums.UserRole;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleChangeRequest {

    @NotNull(message = "유저 권한은 필수 입력입니다. (ADMIN, USER)")
    private UserRole userRole;
}
