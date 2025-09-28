package com.anujl.online_quiz_application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty
    private List<OptionRequestDTO> options=new ArrayList<>();
}
