package com.anujl.online_quiz_application.service;

import com.anujl.online_quiz_application.dto.response.OptionResponseDTO;
import com.anujl.online_quiz_application.dto.response.QuestionResponseDTO;
import com.anujl.online_quiz_application.dto.request.QuestionRequestDTO;
import com.anujl.online_quiz_application.entity.OptionEntity;
import com.anujl.online_quiz_application.entity.QuestionEntity;
import com.anujl.online_quiz_application.entity.QuizEntity;
import com.anujl.online_quiz_application.repository.QuizRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class QuestionService {

    private QuizRepository quizRepository;

    public QuestionResponseDTO addQuestion(Long quizId, @Valid QuestionRequestDTO dto) {

    }


    public List<QuestionResponseDTO> getQuestionsForQuiz(Long quizId) {
    }
}
