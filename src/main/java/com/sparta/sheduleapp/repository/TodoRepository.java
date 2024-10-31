package com.sparta.sheduleapp.repository;

import com.sparta.sheduleapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    default Todo findTodoById(Long id){
        return findById(id).orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + id));
    }
}

