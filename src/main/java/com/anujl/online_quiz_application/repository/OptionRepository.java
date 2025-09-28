package com.anujl.online_quiz_application.repository;

import com.anujl.online_quiz_application.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OptionRepository extends JpaRepository<QuizEntity, Long> {}
