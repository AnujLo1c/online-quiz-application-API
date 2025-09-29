package com.anujl.online_quiz_application.service;

import com.anujl.online_quiz_application.dto.request.OptionRequestDTO;
import com.anujl.online_quiz_application.dto.response.OptionResponseDTO;
import com.anujl.online_quiz_application.dto.response.QuestionResponseDTO;
import com.anujl.online_quiz_application.dto.request.QuestionRequestDTO;
import com.anujl.online_quiz_application.entity.OptionEntity;
import com.anujl.online_quiz_application.entity.QuestionEntity;
import com.anujl.online_quiz_application.entity.QuestionType;
import com.anujl.online_quiz_application.entity.QuizEntity;
import com.anujl.online_quiz_application.exception.custom.ResourceNotFoundException;
import com.anujl.online_quiz_application.repository.QuestionRepository;
import com.anujl.online_quiz_application.repository.QuizRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.pl.REGON;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class QuestionService {

  final  private QuizRepository quizRepository;
  final  private ModelMapper modelMapper;
  final  private QuestionRepository questionRepository;

  @Transactional
    public QuestionResponseDTO addQuestion(Long quizId, @Valid QuestionRequestDTO dto) {
        QuizEntity quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        QuestionEntity question = modelMapper.map(dto, QuestionEntity.class);
        question.setQuiz(quiz);
      if(dto.getType()!= QuestionType.TEXT) {
          List<OptionEntity> options = new ArrayList<>();
          for (OptionRequestDTO optDto : dto.getOptions()) {
              OptionEntity option = modelMapper.map(optDto, OptionEntity.class);
              option.setQuestion(question);
              options.add(option);
          }
          question.setOptions(options);
      }
      else{
          question.setOptions(null);
      }
        QuestionEntity savedQuestion = questionRepository.save(question);
        QuestionResponseDTO response = modelMapper.map(savedQuestion, QuestionResponseDTO.class);
        if(response.getType()!= QuestionType.TEXT){
            response.setOptions(savedQuestion.getOptions().stream()
                    .map(opt -> modelMapper.map(opt, OptionResponseDTO.class))
                    .toList());
        }
        else{
            response.setOptions(null);
        }
        return response;
    }


    public List<QuestionResponseDTO> getQuestionsForQuiz(Long quizId) {
        List<QuestionEntity> questions = questionRepository.findByQuizId(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("No questions found for this quiz"));
        return questions.stream().map(question -> {
            QuestionResponseDTO dto = modelMapper.map(question, QuestionResponseDTO.class);
            if(dto.getType()!=QuestionType.TEXT){
            dto.setOptions(question.getOptions().stream()
                    .map(opt ->
                         modelMapper.map(opt, OptionResponseDTO.class)


                    ).toList());
            }
            return dto;
        }).toList();

        }
}
