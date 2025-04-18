package org.example.expert.domain.comment.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.example.expert.config.AdminApiLoggingAop;
import org.example.expert.config.JwtUtil;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.example.expert.domain.user.enums.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // FIXME: Jwt 부분 없애고, 단위테스트로 변경해야 함
@AutoConfigureMockMvc
@Import(AdminApiLoggingAop.class)
class CommentAdminControllerTest {

	@Autowired MockMvc mockMvc;

	@Autowired JwtUtil jwtUtil;

	@MockBean
	CommentAdminService commentAdminService;

	@Test
	@DisplayName("어드민 페이지에서 댓글을 삭제를 성공적으로 수행")
	void deleteAdminByCommentId() throws Exception {
		// given
		long commentId = 1L;
		String token = jwtUtil.createToken(1L, "wannabeing@123.com", UserRole.ADMIN);

		// when
		willDoNothing().given(commentAdminService).deleteComment(commentId);

		// then
		mockMvc.perform(delete("/admin/comments/{commentId}", commentId)
				.header("Authorization", token)) // 임의 토큰 추가
			.andExpect(status().isOk());
	}
}