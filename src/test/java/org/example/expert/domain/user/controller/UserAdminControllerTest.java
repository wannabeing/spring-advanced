package org.example.expert.domain.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.example.expert.config.AdminApiLoggingAop;
import org.example.expert.config.JwtUtil;
import org.example.expert.domain.user.dto.request.UserRoleChangeRequest;
import org.example.expert.domain.user.enums.UserRole;
import org.example.expert.domain.user.service.UserAdminService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Import(AdminApiLoggingAop.class)
class UserAdminControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	JwtUtil jwtUtil;

	@MockBean
	UserAdminService userAdminService;

	@Test
	@DisplayName("어드민 페이지에서 유저 권한을 성공적으로 수정")
	void changeAdminByUserRole() throws Exception {
		// given
		long userId = 1L;
		String token = jwtUtil.createToken(1L, "wannabeing@123.com", UserRole.ADMIN);
		UserRoleChangeRequest requestDto = new UserRoleChangeRequest("ADMIN");
		String requestBody = new ObjectMapper().writeValueAsString(requestDto);

		// when
		willDoNothing().given(userAdminService).changeUserRole(userId, requestDto);

		// then
		mockMvc.perform(patch("/admin/users/{userId}", userId)
				.header("Authorization", token) // 임의 토큰 추가
				.contentType("application/json")
				.content(requestBody))
				.andExpect(status().isOk());
	}

}