package com.shubham.quizapp.dao;

import com.shubham.quizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionDao extends JpaRepository<Question, UUID> {

    List<Question> findByCategoryIgnoreCase(String category);

}
