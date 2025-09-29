package com.anujl.online_quiz_application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestResultDTO {

    @NotNull
    private List<AnswerDTO> answers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerDTO {
        @NotNull
        private Long questionId;

//        @NotNull

        private List<Long> selectedOptionId;

        @Size(max = 300,message = "Answer size limit exceeded.")
        private String answerText;

    }
}