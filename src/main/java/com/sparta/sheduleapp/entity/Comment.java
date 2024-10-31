package com.sparta.sheduleapp.entity;

import com.sparta.sheduleapp.dto.CommentRequestDto;
import com.sparta.sheduleapp.dto.CommentResponseDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Comment extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public static Comment from(CommentRequestDto requestDto, Todo todo, Member member) {
        Comment comment = new Comment();
        comment.initData(requestDto, todo, member);
        return comment;
    }

    private void initData(CommentRequestDto requestDto, Todo todo, Member member) {
        this.comment= requestDto.getComment();
        this.member = member;
        this.todo = todo;
    }

    public CommentResponseDto to() {
        return new CommentResponseDto(
                this.id,
                this.comment,
                this.member.getMemberName()
        );
    }

    public void updateData(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}