package com.anujl.online_quiz_application.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "questions")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private Integer points;
    @Column(nullable = false)
    private String text;

    //add-ons
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;

//    @Column(length = 300)
//    private String answerText;
    //

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OptionEntity> options = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "quiz_id")

    private QuizEntity quiz;
}
