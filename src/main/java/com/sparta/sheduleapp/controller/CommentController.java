package com.sparta.sheduleapp.controller;

import com.sparta.sheduleapp.dto.CommentRequestDto;
import com.sparta.sheduleapp.dto.CommentResponseDto;
import com.sparta.sheduleapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo/{todoId}/comment")
public class CommentController {

    private final CommentService commentService;

    //댓글 생성하기
    @PostMapping()
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long todoId){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.createComment(requestDto, todoId));
    }

    //댓글 수정하기
    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long todoId, @PathVariable Long commentId){
        commentService.updateComment(requestDto, todoId, commentId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    //댓글 삭제하기
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long todoId, @PathVariable Long commentId){
        commentService.deleteComment(todoId, commentId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}