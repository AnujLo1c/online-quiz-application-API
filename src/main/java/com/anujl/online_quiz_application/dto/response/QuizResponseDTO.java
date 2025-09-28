package com.anujl.online_quiz_application.dto.response;


import com.anujl.online_quiz_application.dto.request.QuestionRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResponseDTO {
    private Long id;
    @NotBlank
    private String title;

    private List<QuestionRequestDTO> questions = new ArrayList<>();
}
