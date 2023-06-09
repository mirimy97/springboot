package com.example.todoapp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity  // DB가 해당 객체를 인식 가능!
@AllArgsConstructor // 생성자 Lombok,
@NoArgsConstructor // 디폴트 생성자
@ToString  // toString Lombok
@Getter
public class Todo {
    @Id
    @GeneratedValue // 1, 2, 3... 자동생성 어노테이션
    @JsonProperty("id")
    private Long id;

    public void setContent(String content) {
        this.content = content;
    }

    @Column
    @JsonProperty("content")
    private String content;

//    Todo() {
//
//    } => 디폴트 생성자 @NoArgsConstructor

//    @Override
//    public String toString() {
//        return "Todo{" +
//                "id=" + id +
//                ", content='" + content + '\'' +
//                '}';
//    }

//    public Todo(Long id, String content) {
//        this.id = id;
//        this.content = content;
//    }
}
