package com.example.todoapp.controller;


import com.example.todoapp.dto.TodoForm;
import com.example.todoapp.entity.Todo;
import com.example.todoapp.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoApiController {

    private final ToDoRepository toDoRepository;


    /* 전체 목록 가져오기 */
    @GetMapping("/todos")
    public List<Todo> all() {
        return toDoRepository.findAll();
    }

    /* getById 하나 가져오기 */
    @GetMapping("/todos/{todoId}")
    public ResponseEntity<Todo> getById(@PathVariable Long todoId) {
        /* 수정할 데이터 가져오기 */
        Optional<Todo> optionalTodo = toDoRepository.findById(todoId);

        /* optionalTodo 객체 비어있지 않다면 .ok(200코드) */
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            return ResponseEntity.ok(todo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /* 데이터 수정하기 */
    @PatchMapping("/todos/edit")
    public ResponseEntity<Todo> editById(@RequestBody TodoForm todoForm) {
        Long todoId = todoForm.getId();
        Optional<Todo> optionalTodo = toDoRepository.findById(todoId);

        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setContent(todoForm.getContent());
            toDoRepository.save(todo);

            return ResponseEntity.ok(todo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /* todo 생성 */
    @PostMapping("/todos/create")
    public ResponseEntity<Todo> create(@RequestBody TodoForm todoForm) {
        Todo todo = todoForm.toEntity();
        Todo saved = toDoRepository.save(todo);

        return ResponseEntity.ok(saved);
    }

    /* todo 삭제 */
    @DeleteMapping("todos/{todoId}/delete")
    public ResponseEntity<Todo> delete(@PathVariable Long todoId) {
        toDoRepository.deleteById(todoId);
        return ResponseEntity.noContent().build();
    }
}
