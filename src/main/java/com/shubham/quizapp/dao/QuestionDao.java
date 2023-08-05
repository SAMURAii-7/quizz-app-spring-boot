package com.shubham.quizapp.dao;

import com.shubham.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionDao extends JpaRepository<Question, UUID> {

    List<Question> findByCategoryIgnoreCase(String category);

    @Query(
            value = "SELECT * FROM question q " +
                    "WHERE q.category=:category AND q.difficulty_level=:level " +
                    "ORDER BY RANDOM() " +
                    "LIMIT :numQues",
            nativeQuery = true
    )
    List<Question> findRandomQuestionsByCategoryAndLevel(String category, String level, int numQues);
}
