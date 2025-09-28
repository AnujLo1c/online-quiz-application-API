package com.anujl.online_quiz_application.service;

import com.anujl.online_quiz_application.dto.QuizSummaryDTO;
import com.anujl.online_quiz_application.dto.request.QuestionRequestDTO;
import com.anujl.online_quiz_application.dto.response.QuizResponseDTO;
import com.anujl.online_quiz_application.dto.request.RequestResultDTO;
import com.anujl.online_quiz_application.dto.request.QuizRequestDTO;
import com.anujl.online_quiz_application.dto.response.ResponseResultDTO;
import com.anujl.online_quiz_application.entity.OptionEntity;
import com.anujl.online_quiz_application.entity.QuestionEntity;
import com.anujl.online_quiz_application.entity.QuizEntity;
import com.anujl.online_quiz_application.exception.custom.ResourceNotFoundException;
import com.anujl.online_quiz_application.repository.QuestionRepository;
import com.anujl.online_quiz_application.repository.QuizRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@NoArgsConstructor
public class QuizService {
    private QuizRepository quizRepository;
private QuestionRepository questionRepository;
private ModelMapper modelMapper;


    public QuizResponseDTO createQuiz( QuizRequestDTO dto) {
QuizEntity quizEntity=modelMapper.map(dto, QuizEntity.class);
QuizEntity savedQuiz=quizRepository.save(quizEntity);
return modelMapper.map(savedQuiz, QuizResponseDTO.class);
    }

    public List<QuizSummaryDTO> getAllQuizzes() {
    List<QuizEntity> quizEntities=quizRepository.findAll();
    if(!quizEntities.isEmpty()){
        List<QuizSummaryDTO> quizList=new ArrayList<>();
        quizEntities.forEach(quiz ->{
            quizList.add(modelMapper.map(quiz, QuizSummaryDTO.class));
        } );
        return quizList;
    }

throw new ResourceNotFoundException("No Quiz in the DB");

    }


    public ResponseResultDTO getResult(Long quizId, RequestResultDTO requestResultDTO) {


        QuizEntity quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));


        List<QuestionEntity> questions = questionRepository.findByQuizId(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("No questions found for this quiz"));

        Map<Long, QuestionEntity> questionMap = questions.stream()
                .collect(Collectors.toMap(QuestionEntity::getId, q -> q));
        int score = 0,i=0,totalMarks=0;
List<Long> selectedOptions=requestResultDTO.getSelectedOptionIDs();
        for (Long questionId : requestResultDTO.getQuestionIDs()) {
            QuestionEntity question = questionMap.get(questionId);


            if (question == null) continue;


            Long correctOptionId = question.getOptions().stream()
                    .filter(OptionEntity::isCorrect)
                    .map(OptionEntity::getId).findFirst()
                    .orElseThrow(() -> new IllegalStateException("Question has no correct option"));


            if(Objects.equals(correctOptionId, selectedOptions.get(i))){
score+=question.getPoints();
            }
            totalMarks+=question.getPoints();
        }


        return new ResponseResultDTO(score,totalMarks);
    }

}
