package com.example.todoapp.controller;

import com.example.todoapp.dto.TodoForm;
import com.example.todoapp.entity.Todo;
import com.example.todoapp.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 브라우저와 통신 Sprin MVC
@Controller
@RequiredArgsConstructor
@Slf4j // 로깅을 위한 골뱅이(어노테이션)
public class TodoAppController {

    @Autowired  // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ToDoRepository toDoRepository;
    @GetMapping("/")
    public String index() {

        return "todos";
    }

    @PostMapping("/addTodo")
    public String addTodo(TodoForm form) {
        log.info(form.toString());
//        System.out.println(form.toString());   => 로깅 기능으로 대체

        // 1. dto를 Entity로 변환
        Todo todo = form.toEntity();
        log.info(todo.toString());
//        System.out.println(todo.toString());

        // 2. Repository 에게 Entity를 DB에 저장하게 함
        Todo saved = toDoRepository.save(todo);
        log.info(saved.toString());
//        System.out.println(saved.toString());

        return "redirect:/";
    }
}
