package com.sparta.sheduleapp.entity;

import com.sparta.sheduleapp.dto.TodoRequestDto;
import com.sparta.sheduleapp.dto.TodoResponseDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Todo extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member creator;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "schedule",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private List<Member> memberList = new ArrayList<>();

    public static Todo from(TodoRequestDto requestDto, Member member) {
        Todo todo = new Todo();
        todo.initData(requestDto, member);
        return todo;
    }

    private void initData(TodoRequestDto requestDto, Member member) {
        this.creator = member;
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
    }

    public TodoResponseDto to() {
        return new TodoResponseDto(
                id,
                this.creator.getMemberName(),
                title,
                description,
                comments.stream().map(Comment::to).toList(),
                getCreatedAt(),
                getModifiedAt()
        );
    }

    public void updateData(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
    }

    public void addMember(Member member){
        this.memberList.add(member);
        member.getTodoList().add(this);
    }
}