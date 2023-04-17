package com.example.todoapp.controller;

import com.example.todoapp.dto.TodoForm;
import com.example.todoapp.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 브라우저와 통신 Sprin MVC
@Controller
@RequiredArgsConstructor
public class TodoAppController {

    @GetMapping("/")
    public String index() {

        return "todos";
    }

    @PostMapping("/addTodo")
    public String addTodo(TodoForm form) {
        System.out.println(form.toString());
        // 1. dto를 Entity로 변환
        Todo todo = form.toEntity();
        // 2. Repository 에게 Entity를 DB에 저장하게 함
        return "redirect:/";
    }
}
