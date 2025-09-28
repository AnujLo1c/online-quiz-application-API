package com.anujl.online_quiz_application.repository;

import com.anujl.online_quiz_application.entity.QuestionEntity;
import com.anujl.online_quiz_application.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuizEntity, Long> {
    Optional<List<QuestionEntity>> findByQuizId(Long quizId);
}
