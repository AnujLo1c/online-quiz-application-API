package com.anujl.online_quiz_application.service;

import com.anujl.online_quiz_application.dto.request.QuestionResponseDTO;
import com.anujl.online_quiz_application.dto.response.QuestionRequestDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    public QuestionResponseDTO addQuestion(Long quizId, @Valid QuestionRequestDTO dto) {
    }

    public List<QuestionResponseDTO> getQuestionsForQuiz(Long quizId) {
    }
}
