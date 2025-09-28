package com.anujl.online_quiz_application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResultDTO {

    private  Integer score;

    private Integer total;
}
