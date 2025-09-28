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
@Table(name = "question")
public class QuestionEntity {
    @Id
    private Long id;
    @Column(nullable = false)
    private Integer points;
    @Column(nullable = false)
    private String text;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<OptionEntity> options = new ArrayList<>();

}
