package org.example.expert.domain.todo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.common.dto.SuccessResponseDto;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<SuccessResponseDto<TodoSaveResponse>> saveTodo(
        @Auth AuthUser authUser,
        @Valid @RequestBody TodoSaveRequest todoSaveRequest,
        HttpServletRequest httpRequest
    ) {

        TodoSaveResponse response = todoService.saveTodo(authUser, todoSaveRequest);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.CREATED,
            httpRequest.getRequestURI(),
            "성공적으로 todo 생성하였습니다.",
            response
        );
    }

    @GetMapping("/todos")
    public ResponseEntity<SuccessResponseDto<Page<TodoResponse>>> getTodos(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size,
        HttpServletRequest httpRequest
    ) {

        Page<TodoResponse> response = todoService.getTodos(page, size);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.OK,
            httpRequest.getRequestURI(),
            "todo 리스트를 성공적으로 조회하였습니다.",
            response
        );
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<SuccessResponseDto<TodoResponse>> getTodo(
        @PathVariable long todoId,
        HttpServletRequest httpRequest) {

        TodoResponse response = todoService.getTodo(todoId);

        return SuccessResponseDto.createResponseEntityDto(
            HttpStatus.OK,
            httpRequest.getRequestURI(),
            "단일 todo 성공적으로 조회하였습니다.",
            response
        );
    }
}
