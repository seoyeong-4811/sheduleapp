package com.sparta.sheduleapp.service;

import com.sparta.sheduleapp.dto.TodoRequestDto;
import com.sparta.sheduleapp.dto.TodoResponseDto;
import com.sparta.sheduleapp.dto.TodoResponsePage;
import com.sparta.sheduleapp.entity.Todo;
import com.sparta.sheduleapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = todoRepository.save(Todo.from(requestDto));
        return todo.to();
    }

    //전체 목록 조회
    public List<TodoResponseDto> getTodoList() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream()
                .map(Todo::to)
                .collect(Collectors.toList());
    }

    //페이징 적용 조회
    public TodoResponsePage getTodoListWithPaging(int page, int size, String criteria) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, criteria));
        Page<Todo> todos = todoRepository.findAll(pageable);
        return new TodoResponsePage(todos);
    }

    public TodoResponseDto getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + todoId));
        return todo.to();
    }

    @Transactional
    public void updateTodo(Long todoId, TodoRequestDto requestDto) {
        Todo todo = todoRepository.findTodoById(todoId);
        todo.updateData(requestDto);
    }

    @Transactional
    public void deleteTodo(Long todoId) {
        todoRepository.findTodoById(todoId);
        todoRepository.deleteById(todoId);
    }
}
