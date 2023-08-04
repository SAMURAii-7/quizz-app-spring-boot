package com.shubham.quizapp.service;

import com.shubham.quizapp.dao.QuestionDao;
import com.shubham.quizapp.entity.Question;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        return questionDao.findByCategoryIgnoreCase(category);
    }

    public String addQuestion(Question question) {
        UUID uuid = UUID.randomUUID();
        question.setId(uuid);
        questionDao.save(question);
        return "Question Added!";
    }

    public String deleteQuestion(UUID id) {
        questionDao.deleteById(id);
        return "Question Deleted!";
    }

    public String updateQuestion(UUID id, Question question) {
        Question oldQuestion = questionDao.getReferenceById(id);
        String[] ignoredProperties = {"id"};
        BeanUtils.copyProperties(question, oldQuestion, ignoredProperties);
        questionDao.save(oldQuestion);
        return "Question Updated!";
    }
}
