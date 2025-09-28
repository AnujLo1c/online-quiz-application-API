package com.anujl.online_quiz_application.dto.response;

import com.anujl.online_quiz_application.dto.request.OptionRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private List<OptionResponseDTO> options=new ArrayList<>();
}
