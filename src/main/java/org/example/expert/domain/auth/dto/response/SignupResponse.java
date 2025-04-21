package org.example.expert.domain.auth.dto.response;

import org.example.expert.domain.user.dto.response.UserResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupResponse {

    private final String bearerToken;
    private final UserResponse user;
}
