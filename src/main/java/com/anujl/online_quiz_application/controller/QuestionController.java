package com.anujl.online_quiz_application.controller;
import com.anujl.online_quiz_application.dto.response.QuestionResponseDTO;
import com.anujl.online_quiz_application.dto.request.QuestionRequestDTO;
import com.anujl.online_quiz_application.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz/{quizId}/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public QuestionResponseDTO addQuestion(@PathVariable Long quizId,
                                           @Valid @RequestBody QuestionRequestDTO dto) {
        return questionService.addQuestion(quizId, dto);
    }

    @GetMapping
    public List<QuestionResponseDTO> getQuestionsForQuiz(@PathVariable Long quizId) {
        return questionService.getQuestionsForQuiz(quizId);
    }
}
