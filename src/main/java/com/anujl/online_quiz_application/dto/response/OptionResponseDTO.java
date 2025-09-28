package com.anujl.online_quiz_application.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionResponseDTO {
    private Long id;
    @NotBlank
    private String text;

}
