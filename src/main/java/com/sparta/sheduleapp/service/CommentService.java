package com.sparta.sheduleapp.service;

import com.sparta.sheduleapp.dto.CommentRequestDto;
import com.sparta.sheduleapp.dto.CommentResponseDto;
import com.sparta.sheduleapp.entity.Comment;
import com.sparta.sheduleapp.entity.Member;
import com.sparta.sheduleapp.entity.Todo;
import com.sparta.sheduleapp.repository.CommentRepository;
import com.sparta.sheduleapp.repository.MemberRepository;
import com.sparta.sheduleapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, Long todoId) {
        Todo todo = todoRepository.findTodoById(todoId);
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + requestDto.getMemberId()));
        Comment comment = Comment.from(requestDto, todo, member);
        Comment savedComment = commentRepository.save(comment);
        return savedComment.to();
    }

    @Transactional
    public void updateComment(CommentRequestDto requestDto, Long todoId, Long commentId) {
        todoRepository.findTodoById(todoId);
        Comment comment = commentRepository.findCommentById(commentId);
        comment.updateData(requestDto);
    }

    @Transactional
    public void deleteComment(Long todoId, Long commentId) {
        todoRepository.findTodoById(todoId);
        commentRepository.findCommentById(commentId);
        commentRepository.deleteById(commentId);
    }
}