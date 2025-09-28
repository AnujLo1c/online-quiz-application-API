package com.anujl.online_quiz_application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResultDTO {
    @NotEmpty
    private List<Long> questionId;
    @NotEmpty
    private List<Long> selectedOptionIds;
}
