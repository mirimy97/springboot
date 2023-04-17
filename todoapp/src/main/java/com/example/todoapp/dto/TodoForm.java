package com.example.todoapp.dto;

import com.example.todoapp.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.ToString;

// Lombok 사용 Refactoring
@AllArgsConstructor  // 아래 주석처리 한 부분과 같은 효과
@ToString
public class TodoForm {

//    public TodoForm(String content) {
//        this.content = content;
//    }

//    @Override
//    public String toString() {
//        return "TodoForm{" +
//                "content='" + content + '\'' +
//                '}';
//    }

    private String content;

    public Todo toEntity() {
        return new Todo(null, content);
    }
}
