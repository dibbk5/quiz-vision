package com.dibb.quizvision.controllers;

import com.dibb.quizvision.dtos.QuizDto;
import com.dibb.quizvision.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/quiz")
    public void addQuiz(@RequestBody QuizDto quizDto){
        quizService.addQuiz(quizDto);
    }
}
