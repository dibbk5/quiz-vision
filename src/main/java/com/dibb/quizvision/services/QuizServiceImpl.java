package com.dibb.quizvision.services;

import com.dibb.quizvision.dtos.QuizDto;
import com.dibb.quizvision.entities.Quiz;
import com.dibb.quizvision.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl {
    @Autowired
    private QuizRepository quizRepository;

    @Transactional
    public void addQuiz(QuizDto quizDto){
        Quiz quiz = new Quiz(quizDto);
        quizRepository.saveAndFlush(quiz);
    }

    public List<QuizDto> getAllQuizzes(){
        List<Quiz> quizList = quizRepository.findAll();
        return quizList.stream().map(quiz -> new QuizDto(quiz)).collect(Collectors.toList());
    }

    public Optional<QuizDto> getQuizById(Long quizId){
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        return quizOptional.map(QuizDto::new);
    }

}
