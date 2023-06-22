package com.dibb.quizvision.repositories;

import com.dibb.quizvision.entities.Question;
import com.dibb.quizvision.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByQuizEquals(Quiz quiz);
}
