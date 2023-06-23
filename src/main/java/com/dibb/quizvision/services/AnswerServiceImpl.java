package com.dibb.quizvision.services;

import com.dibb.quizvision.dtos.AnswerDto;
import com.dibb.quizvision.entities.Answer;
import com.dibb.quizvision.entities.Question;
import com.dibb.quizvision.repositories.AnswerRepository;
import com.dibb.quizvision.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    @Transactional
    public void addAnswer(AnswerDto answerDto, Long questionId){
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        Answer answer = new Answer(answerDto);
        questionOptional.ifPresent(answer::setQuestion);
        answerRepository.saveAndFlush(answer);
    }

    @Override
    public List<AnswerDto> getAnswers(Long questionId){
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isPresent()){
            List<Answer> answerList = answerRepository.findAllByQuestionEquals(questionOptional.get());
            return answerList.stream().map(answer -> new AnswerDto(answer)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
