package com.dibb.quizvision.repositories;

import com.dibb.quizvision.entities.Answer;
import com.dibb.quizvision.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestionEquals(Question question);
}
