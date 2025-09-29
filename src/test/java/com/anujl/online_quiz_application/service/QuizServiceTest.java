package com.anujl.online_quiz_application.service;

import com.anujl.online_quiz_application.dto.QuizSummaryDTO;
import com.anujl.online_quiz_application.dto.request.QuizRequestDTO;
import com.anujl.online_quiz_application.dto.request.RequestResultDTO;
import com.anujl.online_quiz_application.dto.response.QuizResponseDTO;
import com.anujl.online_quiz_application.dto.response.ResponseResultDTO;
import com.anujl.online_quiz_application.entity.OptionEntity;
import com.anujl.online_quiz_application.entity.QuestionEntity;

import com.anujl.online_quiz_application.entity.QuestionType;
import com.anujl.online_quiz_application.entity.QuizEntity;
import com.anujl.online_quiz_application.repository.QuestionRepository;
import com.anujl.online_quiz_application.repository.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private QuizService quizService;

    private QuizRequestDTO quizRequestDTO;
    private QuizEntity mockQuiz;
    private QuizResponseDTO mockQuizResponse;

    private QuestionEntity textQuestion;
    private QuestionEntity singleChoiceQuestion;
    private QuestionEntity multipleChoiceQuestion;

    @BeforeEach
    void setUp() {
        
        quizRequestDTO = new QuizRequestDTO();
        quizRequestDTO.setTitle("Sample Quiz");
        quizRequestDTO.setQuestions(new ArrayList<>());

        
        mockQuiz = new QuizEntity();
        mockQuiz.setId(1L);
        mockQuiz.setTitle("Sample Quiz");
        mockQuiz.setQuestions(new ArrayList<>());

        
        mockQuizResponse = new QuizResponseDTO();
        mockQuizResponse.setId(1L);
        mockQuizResponse.setTitle("Sample Quiz");

        
        
        textQuestion = new QuestionEntity();
        textQuestion.setId(1L);
        textQuestion.setType(QuestionType.TEXT);
        textQuestion.setPoints(5);

        
        singleChoiceQuestion = new QuestionEntity();
        singleChoiceQuestion.setId(2L);
        singleChoiceQuestion.setType(QuestionType.SINGLE_CHOICE);
        singleChoiceQuestion.setPoints(10);
        OptionEntity correctSingleOption = new OptionEntity();
        correctSingleOption.setId(101L);
        correctSingleOption.setCorrect(true);
        singleChoiceQuestion.setOptions(List.of(correctSingleOption));

        
        multipleChoiceQuestion = new QuestionEntity();
        multipleChoiceQuestion.setId(3L);
        multipleChoiceQuestion.setType(QuestionType.MULTIPLE_CHOICE);
        multipleChoiceQuestion.setPoints(20);
        OptionEntity option1 = new OptionEntity();
        option1.setId(201L);
        option1.setCorrect(true);
        OptionEntity option2 = new OptionEntity();
        option2.setId(202L);
        option2.setCorrect(true);
        OptionEntity option3 = new OptionEntity();
        option3.setId(203L);
        option3.setCorrect(false);
        multipleChoiceQuestion.setOptions(List.of(option1, option2, option3));
    }

    @Test
    void createQuiz_ShouldReturnSavedQuizDTO() {
        lenient().when(modelMapper.map(any(QuizRequestDTO.class), eq(QuizEntity.class)))
                .thenReturn(mockQuiz);
        lenient().when(quizRepository.save(any(QuizEntity.class)))
                .thenReturn(mockQuiz);
        lenient().when(modelMapper.map(any(QuizEntity.class), eq(QuizResponseDTO.class)))
                .thenReturn(mockQuizResponse);

        QuizResponseDTO created = quizService.createQuiz(quizRequestDTO);

        assertNotNull(created);
        assertEquals("Sample Quiz", created.getTitle());
        verify(quizRepository, times(1)).save(any(QuizEntity.class));
    }

    @Test
    void getAllQuizzes_ShouldReturnListOfQuizSummaryDTOs() {
        QuizSummaryDTO summaryDTO = new QuizSummaryDTO();
        summaryDTO.setId(1L);
        summaryDTO.setTitle("Sample Quiz");

        when(quizRepository.findAll()).thenReturn(List.of(mockQuiz));
        when(modelMapper.map(any(QuizEntity.class), eq(QuizSummaryDTO.class))).thenReturn(summaryDTO);

        List<QuizSummaryDTO> summaries = quizService.getAllQuizzes();

        assertNotNull(summaries);
        assertEquals(1, summaries.size());
        assertEquals("Sample Quiz", summaries.get(0).getTitle());
        verify(quizRepository, times(1)).findAll();
    }

    @Test
    void getResult_ShouldCalculateScore() {
        
        when(quizRepository.findById(anyLong())).thenReturn(Optional.of(mockQuiz));
        when(questionRepository.findByQuizId(anyLong()))
                .thenReturn(Optional.of(List.of(textQuestion, singleChoiceQuestion, multipleChoiceQuestion)));

        
        RequestResultDTO.AnswerDTO textAnswer = new RequestResultDTO.AnswerDTO(1L, null, "Some answer");
        RequestResultDTO.AnswerDTO singleChoiceAnswer = new RequestResultDTO.AnswerDTO(2L, List.of(101L), null);
        RequestResultDTO.AnswerDTO multipleChoiceAnswer = new RequestResultDTO.AnswerDTO(3L, List.of(201L, 203L), null);

        RequestResultDTO requestResultDTO = new RequestResultDTO(List.of(textAnswer, singleChoiceAnswer, multipleChoiceAnswer));

        
        ResponseResultDTO result = quizService.getResult(1L, requestResultDTO);

        
        int expectedScore = 5 + 10 + (20 * 1 / 2); 
        int expectedTotal = 5 + 10 + 20;
        assertEquals(expectedScore, result.getScore());

    }
}
