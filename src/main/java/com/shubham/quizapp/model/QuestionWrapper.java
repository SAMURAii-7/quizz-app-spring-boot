package com.shubham.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {
    private UUID id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
