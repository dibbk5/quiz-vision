package com.dibb.quizvision.services;

import com.dibb.quizvision.dtos.QuestionDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionService {
    @Transactional
    Long addQuestion(QuestionDto questionDto, Long quizId);

    @Transactional
    void deleteQuestion(Long questionId);

    List<QuestionDto> getAllQuestionsForQuiz(Long quizId);
}
