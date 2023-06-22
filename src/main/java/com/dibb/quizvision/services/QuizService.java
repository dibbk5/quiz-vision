package com.dibb.quizvision.services;

import com.dibb.quizvision.dtos.QuizDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    @Transactional
    void addQuiz(QuizDto quizDto);

    List<QuizDto> getAllQuizzes();

    Optional<QuizDto> getQuizById(Long quizId);
}
