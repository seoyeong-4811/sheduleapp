package com.sparta.sheduleapp.controller;

import com.sparta.sheduleapp.dto.TodoRequestDto;
import com.sparta.sheduleapp.dto.TodoResponseDto;
import com.sparta.sheduleapp.dto.TodoResponsePage;
import com.sparta.sheduleapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    //1. 일정 생성하기
    @PostMapping()
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto requestDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.createTodo(requestDto));
    }

    //2. 전체 일정 조회하기
    @GetMapping()
    public ResponseEntity<TodoResponsePage> getTodoList(@RequestParam(required = false, defaultValue = "0") int page,
                                                        @RequestParam(required = false, defaultValue = "10") int size,
                                                        @RequestParam(required = false, defaultValue = "modifiedAt") String criteria){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.getTodoListWithPaging(page, size, criteria));
    }

    //3. 선택 일정 조회하기
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto>getTodo(@PathVariable Long todoId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.getTodo(todoId));
    }

    //4. 선택 일정 수정하기
    @PutMapping("/{todoId}")
    public ResponseEntity<Void> updateTodo(
            @PathVariable Long todoId,
            @RequestBody TodoRequestDto requestDto
    ){
        todoService.updateTodo(todoId, requestDto);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }

    //5. 선택 일정 삭제하기
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long todoId
    ){
        todoService.deleteTodo(todoId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }

    //일정 담당자 배정
    @PostMapping("/{todoId}/assign/{memberId}")
    public ResponseEntity<Void> assignMemberToTodo(@PathVariable Long memberId, @PathVariable Long todoId){
        todoService.assignMember(memberId, todoId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}

