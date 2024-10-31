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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public static Todo from(TodoRequestDto requestDto) {
        Todo todo = new Todo();
        todo.initData(requestDto);
        return todo;
    }

    private void initData(TodoRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
    }

    public TodoResponseDto to() {
        return new TodoResponseDto(
                id,
                userName,
                title,
                description,
                comments.stream().map(Comment::to).toList(),
                getCreatedAt(),
                getModifiedAt()
        );
    }

    public void updateData(TodoRequestDto requestDto) {
        this.userName= requestDto.getUserName();
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
    }
}