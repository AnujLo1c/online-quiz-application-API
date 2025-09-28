package com.anujl.online_quiz_application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "quiz")
public class QuizEntity {
    @Id
    private Long id;
    @Column(nullable = false)
    private String title;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<QuestionEntity> questions = new ArrayList<>();
}
