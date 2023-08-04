package com.shubham.quizapp.controller;

import com.shubham.quizapp.entity.Question;
import com.shubham.quizapp.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("question")
public class QuestionController {

    //Field Injection:
    //@Autowired
    //QuestionService questionService;
    //Using constructor injection below instead of field injection using @Autowired
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getAllQuestionsByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable UUID id) {
        return questionService.deleteQuestion(id);
    }

    @PutMapping("update/{id}")
    public String updateQuestion(@PathVariable UUID id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

}
