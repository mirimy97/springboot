package com.example.todoapp.controller;

import com.example.todoapp.dto.TodoForm;
import com.example.todoapp.entity.Todo;
import com.example.todoapp.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

// 브라우저와 통신 Spring MVC
@Controller
@RequiredArgsConstructor
@Slf4j // 로깅을 위한 골뱅이(어노테이션)
public class TodoAppController {

    @Autowired  // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ToDoRepository toDoRepository;
    @GetMapping("/")
    public String index(Model model) {
        // 1. 모든 todos를 가져온다 => Repository
        // Repository에서 method override 해줘서 ArrayList<Todo> 가능,
        // 상위 타입인 List<Todo>도 가능
        List<Todo> toDoEntityList = toDoRepository.findAll();

        // 2. 가져온 todo 묶음을 뷰로 전달한다.
        model.addAttribute("todoList", toDoEntityList);

        // 3. 뷰 페이지를 설정한다.
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

    // 단일 데이터 조회
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        // 0. url로 전해진 params 가져오기
        log.info("id= " + id);

        // 1. id로 데이터를 가져옴
        // id 값으로 찾고 (fineById), 없다면 null을 반환 .orElse(null)
        Todo todoEntity = toDoRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 등록
        // "todo" 라는 이름으로 todoEntity를 등록
        model.addAttribute("todo", todoEntity);

        // 3. 보여줄 페이지를 설정
        return "show";
    }
}
