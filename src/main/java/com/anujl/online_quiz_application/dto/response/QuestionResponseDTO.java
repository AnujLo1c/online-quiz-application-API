package com.anujl.online_quiz_application.dto.response;

import com.anujl.online_quiz_application.dto.request.OptionRequestDTO;
import com.anujl.online_quiz_application.entity.QuestionType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponseDTO {
    private Long id;
    @NotBlank
    private String text;

    private Integer points;


@NotNull
    private QuestionType type;
//    @Size(max = 300, message = "Answer text must not exceed 300 characters")
//    private String answerText;


    @NotEmpty
    private List<OptionResponseDTO> options=new ArrayList<>();
}
