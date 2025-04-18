package org.example.expert.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AdminApiLoggingAop {

	private final ObjectMapper objectMapper;

	@Around("execution(* org.example.expert.domain.comment.controller.CommentAdminController.*(..)) || " +
		"execution(* org.example.expert.domain.user.controller.UserAdminController.*(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		// RequestContextHolder.getRequestAttributes()에는 HTTP 요청에 대한 모든 정보가 담겨 있음
		// 인터페이스이므로 구현체가 필요함 → ServletRequestAttributes 으로 다운캐스팅 필요
		// 다운캐스팅 후에, getRequest() 메서드로 HttpServletRequest 객체를 추출
		// ❗️HTTP 요청이 아닌 경우, NPE 발생하므로 Objects.requiredNonNull() 설정을 IDE 가 도와줌
		HttpServletRequest request = ((ServletRequestAttributes)Objects.requireNonNull(
			RequestContextHolder.getRequestAttributes())).getRequest();

		String requestUrl = request.getRequestURI();
		String requestTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Long userId = (Long) request.getAttribute("userId");
		log.info("사용자 ID: {}", userId);
		log.info("요청 URL: {}", requestUrl);
		log.info("요청 시간: {}", requestTime);

		List<Object> validArgList = new ArrayList<>();
		Object[] args = joinPoint.getArgs(); // AOP 에게 홀딩된 메서드의 모든 파라미터들을 배열로 갖고 있음
		for (Object arg : args) {
			if (arg instanceof HttpServletRequest){
				continue; // 실제 요청 정보가 아니고, 변환이 안되므로 continue
			}
			try {
				validArgList.add(arg);
			} catch (Exception e) {
				validArgList.add("[JSON 변환 실패]: " + arg.getClass().getSimpleName());
			}
		}
		String requestBody = objectMapper.writeValueAsString(validArgList); // 직렬화
		log.info("요청 바디: {}", requestBody);

		Object result = joinPoint.proceed(); // 실제 메서드 실행해서 응답값 가져옴

		String responseBody = objectMapper.writeValueAsString(result); // 직렬화
		log.info("응답 바디: {}", responseBody);

		return result;
	}
}