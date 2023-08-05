package com.shubham.quizapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Response {
    private UUID id;
    private String response;
}
