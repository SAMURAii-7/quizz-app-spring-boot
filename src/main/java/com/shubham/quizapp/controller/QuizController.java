package com.shubham.quizapp.controller;

import com.shubham.quizapp.model.QuestionWrapper;
import com.shubham.quizapp.model.Response;
import com.shubham.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam String level, @RequestParam int numQues,
                                             @RequestParam String title) {
        level = level.substring(0, 1).toUpperCase() + level.substring(1);
        category = category.substring(0, 1).toUpperCase() + category.substring(1);
        return quizService.createQuiz(category, level, numQues, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id,responses);
    }

}
