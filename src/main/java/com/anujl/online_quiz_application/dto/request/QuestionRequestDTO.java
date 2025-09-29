package com.anujl.online_quiz_application.dto.request;

import com.anujl.online_quiz_application.entity.QuestionType;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDTO {
    @NotBlank
    private String text;

    @NotNull(message = "Points must be specified")
    @Min(value = 1, message = "Points must be at least 1")
    private Integer points;


    @NotNull
    private QuestionType type;
//    @Size(max = 300, message = "Answer text must not exceed 300 characters")
//    private String answerText;


    private List<OptionRequestDTO> options=new ArrayList<>();
}
