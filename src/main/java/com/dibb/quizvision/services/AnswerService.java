package com.dibb.quizvision.services;

import com.dibb.quizvision.dtos.AnswerDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnswerService {
    @Transactional
    void addAnswer(AnswerDto answerDto, Long questionId);

    List<AnswerDto> getAnswers(Long questionId);
}
