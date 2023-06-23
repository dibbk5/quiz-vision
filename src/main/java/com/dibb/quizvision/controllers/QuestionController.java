package com.dibb.quizvision.controllers;

import com.dibb.quizvision.dtos.QuestionDto;
import com.dibb.quizvision.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/question/{quizId}")
    public Long addQuestion(@RequestBody QuestionDto questionDto, @PathVariable Long quizId){
        return questionService.addQuestion(questionDto, quizId);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable Long questionId){
        questionService.deleteQuestion(questionId);
    }

    @GetMapping("/question/{quizId}")
    public List<QuestionDto> getAllQuestionsForQuiz(@PathVariable Long quizId){
        return questionService.getAllQuestionsForQuiz(quizId);
    }
}
