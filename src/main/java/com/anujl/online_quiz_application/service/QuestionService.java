package com.anujl.online_quiz_application.service;

import com.anujl.online_quiz_application.dto.response.OptionResponseDTO;
import com.anujl.online_quiz_application.dto.response.QuestionResponseDTO;
import com.anujl.online_quiz_application.dto.request.QuestionRequestDTO;
import com.anujl.online_quiz_application.entity.OptionEntity;
import com.anujl.online_quiz_application.entity.QuestionEntity;
import com.anujl.online_quiz_application.entity.QuizEntity;
import com.anujl.online_quiz_application.exception.custom.ResourceNotFoundException;
import com.anujl.online_quiz_application.repository.QuestionRepository;
import com.anujl.online_quiz_application.repository.QuizRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class QuestionService {

    private QuizRepository quizRepository;
    private ModelMapper modelMapper;
    private QuestionRepository questionRepository;

    public QuestionResponseDTO addQuestion(Long quizId, @Valid QuestionRequestDTO dto) {
        QuizEntity quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        QuestionEntity question = modelMapper.map(dto, QuestionEntity.class);


        List<OptionEntity> options = dto.getOptions().stream()
                .map(optDto -> {
                    OptionEntity option = modelMapper.map(optDto, OptionEntity.class);
                    option.setQuestion(question);
                    return option;
                })
                .toList();

        question.setOptions(options);
        QuestionEntity savedQuestion = questionRepository.save(question);

        QuestionResponseDTO response = modelMapper.map(savedQuestion, QuestionResponseDTO.class);
        response.setOptions(savedQuestion.getOptions().stream()
                .map(opt -> modelMapper.map(opt, OptionResponseDTO.class))
                .toList());

        return response;
    }



    public List<QuestionResponseDTO> getQuestionsForQuiz(Long quizId) {
        List<QuestionEntity> questions = questionRepository.findByQuizId(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("No questions found for this quiz"));

        return questions.stream().map(question -> {
            QuestionResponseDTO dto = modelMapper.map(question, QuestionResponseDTO.class);
            dto.setOptions(question.getOptions().stream()
                    .map(opt ->
                         modelMapper.map(opt, OptionResponseDTO.class)


                    ).toList());
            return dto;
        }).toList();
    }
}
