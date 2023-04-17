package com.example.todoapp.dto;

import com.example.todoapp.entity.Todo;

public class TodoForm {

    public TodoForm(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TodoForm{" +
                "content='" + content + '\'' +
                '}';
    }

    private String content;

    public Todo toEntity() {
        return new Todo(id:null, content);
    }
}
