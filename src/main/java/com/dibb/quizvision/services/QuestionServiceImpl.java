package com.dibb.quizvision.services;

import com.dibb.quizvision.dtos.QuestionDto;
import com.dibb.quizvision.dtos.QuizDto;
import com.dibb.quizvision.entities.Question;
import com.dibb.quizvision.entities.Quiz;
import com.dibb.quizvision.repositories.QuestionRepository;
import com.dibb.quizvision.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizRepository quizRepository;

    @Override
    @Transactional
    public Long addQuestion(QuestionDto questionDto, Long quizId){
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        Question question = new Question(questionDto);
        quizOptional.ifPresent(question::setQuiz);
        questionRepository.saveAndFlush(question);
        Long questionId = question.getId();
        return questionId;
    }

    @Override
    @Transactional
    public void deleteQuestion(Long questionId){
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        questionOptional.ifPresent(question -> questionRepository.delete(question));
    }

    @Override
    public List<QuestionDto> getAllQuestionsForQuiz(Long quizId){
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isPresent()){
            List<Question> questionList = questionRepository.findAllByQuizEquals(quizOptional.get());
            return questionList.stream().map(question -> new QuestionDto(question)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
