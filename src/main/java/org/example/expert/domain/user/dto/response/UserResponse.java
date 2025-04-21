package org.example.expert.domain.user.dto.response;

import org.example.expert.domain.user.entity.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponse {

    private final Long id;
    private final String email;

    public UserResponse(User user){
        this.id = user.getId();
        this.email = user.getEmail();
    }
}