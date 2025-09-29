package com.anujl.online_quiz_application.service;

import com.anujl.online_quiz_application.dto.request.OptionRequestDTO;
import com.anujl.online_quiz_application.dto.request.QuestionRequestDTO;
import com.anujl.online_quiz_application.dto.response.OptionResponseDTO;
import com.anujl.online_quiz_application.dto.response.QuestionResponseDTO;
import com.anujl.online_quiz_application.entity.OptionEntity;
import com.anujl.online_quiz_application.entity.QuestionEntity;
import com.anujl.online_quiz_application.entity.QuestionType;
import com.anujl.online_quiz_application.entity.QuizEntity;
import com.anujl.online_quiz_application.exception.custom.ResourceNotFoundException;
import com.anujl.online_quiz_application.repository.QuestionRepository;
import com.anujl.online_quiz_application.repository.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock private QuizRepository quizRepository;
    @Mock private QuestionRepository questionRepository;
    @Mock private ModelMapper modelMapper;

    @InjectMocks
    private QuestionService questionService;

    private QuizEntity mockQuiz;
    private QuestionRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        mockQuiz = new QuizEntity();
        mockQuiz.setId(1L);

        OptionRequestDTO optionDTO = new OptionRequestDTO();
        optionDTO.setText("Option A");
        optionDTO.setCorrect(true);

        requestDTO = new QuestionRequestDTO();
        requestDTO.setText("What is 2+2?");
        requestDTO.setPoints(5);
        requestDTO.setType(QuestionType.SINGLE_CHOICE);
        requestDTO.setOptions(List.of(optionDTO));
    }

    @Test
    void addQuestion_ShouldThrow_WhenQuizNotFound() {
        when(quizRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> questionService.addQuestion(1L, requestDTO))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Quiz not found");

        verify(questionRepository, never()).save(any());
    }

    @Test
    void addQuestion_ShouldSaveAndReturnResponse_WhenValid() {
        Long quizId = 1L;

        QuizEntity quiz = new QuizEntity();
        quiz.setId(quizId);

        QuestionRequestDTO requestDTO = new QuestionRequestDTO();
        requestDTO.setText("Sample Question?");
        requestDTO.setPoints(5);
        requestDTO.setType(QuestionType.MULTIPLE_CHOICE);

        OptionRequestDTO optionDTO = new OptionRequestDTO("Option A", true);
        requestDTO.setOptions(List.of(optionDTO));

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuiz(quiz);

        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setText("Option A");
        optionEntity.setCorrect(true);
        optionEntity.setQuestion(questionEntity);

        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        questionResponseDTO.setText("Sample Question?");
        questionResponseDTO.setPoints(5);
        questionResponseDTO.setType(QuestionType.MULTIPLE_CHOICE);

        OptionResponseDTO optionResponseDTO = new OptionResponseDTO();
        optionResponseDTO.setText("Option A");


                when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));

        when(modelMapper.map(any(QuestionRequestDTO.class), eq(QuestionEntity.class)))
                .thenReturn(questionEntity);

        when(modelMapper.map(any(OptionRequestDTO.class), eq(OptionEntity.class)))
                .thenReturn(optionEntity);

        when(questionRepository.save(any(QuestionEntity.class)))
                .thenReturn(questionEntity);

        when(modelMapper.map(any(QuestionEntity.class), eq(QuestionResponseDTO.class)))
                .thenReturn(questionResponseDTO);

        when(modelMapper.map(any(OptionEntity.class), eq(OptionResponseDTO.class)))
                .thenReturn(optionResponseDTO);

                QuestionResponseDTO response = questionService.addQuestion(quizId, requestDTO);

                assertNotNull(response);
        assertEquals("Sample Question?", response.getText());
        assertEquals(5, response.getPoints());
        assertEquals(QuestionType.MULTIPLE_CHOICE, response.getType());
        assertEquals(1, response.getOptions().size());
    }
    @Test
    void getQuestionsForQuiz_ShouldThrow_WhenNoQuestionsFound() {
        when(questionRepository.findByQuizId(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> questionService.getQuestionsForQuiz(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No questions found");
    }

    @Test
    void getQuestionsForQuiz_ShouldReturnList_WhenQuestionsExist() {
        QuestionEntity mockEntity = new QuestionEntity();
        mockEntity.setId(200L);
        mockEntity.setText("Sample Q");
        mockEntity.setType(QuestionType.SINGLE_CHOICE);

        QuestionResponseDTO mockResponse = new QuestionResponseDTO();
        mockResponse.setId(200L);
        mockResponse.setText("Sample Q");
        mockResponse.setType(QuestionType.SINGLE_CHOICE);

        when(questionRepository.findByQuizId(1L)).thenReturn(Optional.of(List.of(mockEntity)));
        when(modelMapper.map(any(QuestionEntity.class), eq(QuestionResponseDTO.class))).thenReturn(mockResponse);

        List<QuestionResponseDTO> result = questionService.getQuestionsForQuiz(1L);

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getId()).isEqualTo(200L);

        verify(questionRepository).findByQuizId(1L);
    }
}
