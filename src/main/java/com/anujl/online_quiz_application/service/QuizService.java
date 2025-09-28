package com.anujl.online_quiz_application.service;

import com.anujl.online_quiz_application.dto.request.QuizResponseDTO;
import com.anujl.online_quiz_application.dto.response.QuizRequestDTO;
import com.anujl.online_quiz_application.repository.QuizRepository;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@NoArgsConstructor
public class QuizService {
    private QuizRepository quizRepository;



    public QuizResponseDTO createQuiz( QuizRequestDTO dto) {

    }

    public List<QuizResponseDTO> getAllQuizzes() {

    }


}
