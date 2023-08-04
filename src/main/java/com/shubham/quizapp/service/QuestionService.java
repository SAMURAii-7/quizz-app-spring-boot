package com.shubham.quizapp.service;

import com.shubham.quizapp.dao.QuestionDao;
import com.shubham.quizapp.entity.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {

        Logger logger = LoggerFactory.getLogger(getClass());

        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while retrieving questions: ", e);
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        return questionDao.findByCategoryIgnoreCase(category);
    }

    public ResponseEntity<String> addQuestion(Question question) {

        Logger logger = LoggerFactory.getLogger(getClass());

        UUID uuid = UUID.randomUUID();
        question.setId(uuid);
        questionDao.save(question);
        try{
            return new ResponseEntity<>("Question Added!", HttpStatus.CREATED);
        } catch(Exception e) {
            logger.error("Question could not be added: ", e);
        }

        return new ResponseEntity<>("Failed!", HttpStatus.BAD_REQUEST);
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
