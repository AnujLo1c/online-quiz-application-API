package com.anujl.online_quiz_application.service;

import com.anujl.online_quiz_application.dto.QuizSummaryDTO;
import com.anujl.online_quiz_application.dto.request.QuestionRequestDTO;
import com.anujl.online_quiz_application.dto.response.QuizResponseDTO;
import com.anujl.online_quiz_application.dto.request.RequestResultDTO;
import com.anujl.online_quiz_application.dto.request.QuizRequestDTO;
import com.anujl.online_quiz_application.dto.response.ResponseResultDTO;
import com.anujl.online_quiz_application.entity.OptionEntity;
import com.anujl.online_quiz_application.entity.QuestionEntity;
import com.anujl.online_quiz_application.entity.QuestionType;
import com.anujl.online_quiz_application.entity.QuizEntity;
import com.anujl.online_quiz_application.exception.custom.ResourceNotFoundException;
import com.anujl.online_quiz_application.repository.QuestionRepository;
import com.anujl.online_quiz_application.repository.QuizRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {
  final   private QuizRepository quizRepository;

  final private QuestionRepository questionRepository;
final private ModelMapper modelMapper;


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

@Transactional
    public ResponseResultDTO getResult(Long quizId, RequestResultDTO requestResultDTO) {

        int score = 0,totalMarks=0;
        QuizEntity quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));


        List<QuestionEntity> questions = questionRepository.findByQuizId(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("No questions found for this quiz"));

        Map<Long, RequestResultDTO.AnswerDTO> userAnswers = requestResultDTO.getAnswers().stream()
                .collect(Collectors.toMap(RequestResultDTO.AnswerDTO::getQuestionId, q -> q));

        for(QuestionEntity entity:questions) {
            totalMarks += entity.getPoints();
            RequestResultDTO.AnswerDTO currAns = userAnswers.get(entity.getId());
            if (currAns == null) {
                continue;
            }
            switch (entity.getType()) {
                case TEXT -> {
                    String ans = currAns.getAnswerText();
                    if (ans == null || ans.isBlank()) {
                        continue;
                    }
                    score += entity.getPoints();
                }
                case SINGLE_CHOICE -> {

                    Long correctOptionId = entity.getOptions().stream()
                            .filter(OptionEntity::isCorrect)
                            .map(OptionEntity::getId).findFirst()
                            .orElseThrow(() -> new IllegalStateException("Question has no correct option"));
                    if (Objects.equals(currAns.getSelectedOptionId().getFirst(), correctOptionId)) {
                        score += entity.getPoints();
                    }
                }
                case MULTIPLE_CHOICE -> {
                    List<Long> correctOptionIds = entity.getOptions().stream()
                            .filter(OptionEntity::isCorrect)
                            .map(OptionEntity::getId).toList();
                    List<Long> selectedOptions=currAns.getSelectedOptionId();
                    int correct=0, totalCorrectOptions=correctOptionIds.size();
                    for(Long selectedId:selectedOptions){
                        if(correctOptionIds.contains(selectedId)){
                            correct++;
                        }
                    }
                    score+=entity.getPoints()*(correct/totalCorrectOptions);
                }
                default -> throw new IllegalArgumentException("Unknown question type.");
            }
        }
        return new ResponseResultDTO(score,totalMarks);
    }

}
