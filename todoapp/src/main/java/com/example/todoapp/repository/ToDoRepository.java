package com.example.todoapp.repository;

import com.example.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ToDoRepository extends CrudRepository<Todo, Long> {  // <관리대상 entity, entity 대표값 type>

    // 우클릭 - Generate - Override Method
    @Override
    ArrayList<Todo> findAll();
}
