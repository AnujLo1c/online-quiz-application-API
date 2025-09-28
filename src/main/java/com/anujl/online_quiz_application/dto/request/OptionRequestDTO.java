package com.anujl.online_quiz_application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionRequestDTO {
    @NotBlank
    private String text;
    private boolean correct;
}
