package com.anujl.online_quiz_application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class RequestResultDTO {
    @NotEmpty
    private List<Long> questionIDs;
    @NotEmpty
    private List<Long> selectedOptionIDs;
}
