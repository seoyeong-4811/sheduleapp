package com.sparta.sheduleapp.dto;


import com.sparta.sheduleapp.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class TodoResponsePage {
    private List<TodoResponseDto> todos;
    private int totalPages;
    private long totalElements;

    public TodoResponsePage(Page<Todo> page) {
        this.todos = page.getContent().stream()
                .map(Todo::to)
                .collect(Collectors.toList());
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}