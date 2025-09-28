package com.anujl.online_quiz_application.controller;

import com.anujl.online_quiz_application.dto.QuizSummaryDTO;
import com.anujl.online_quiz_application.dto.response.QuizResponseDTO;
import com.anujl.online_quiz_application.dto.request.RequestResultDTO;
import com.anujl.online_quiz_application.dto.request.QuizRequestDTO;
import com.anujl.online_quiz_application.dto.response.ResponseResultDTO;
import com.anujl.online_quiz_application.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizResponseDTO> createQuiz(@Valid @RequestBody QuizRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.createQuiz(dto));
    }

    @GetMapping
    public ResponseEntity<List<QuizSummaryDTO>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @PostMapping("{quizId}/submit")
    public ResponseEntity<ResponseResultDTO> getQuizScore(
            @PathVariable Long quizId,
            @Valid @RequestBody RequestResultDTO requestResultDTO){
return ResponseEntity.ok(quizService.getResult(quizId,requestResultDTO));
    }

}
