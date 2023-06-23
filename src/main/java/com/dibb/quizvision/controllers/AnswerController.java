package com.dibb.quizvision.controllers;

import com.dibb.quizvision.dtos.AnswerDto;
import com.dibb.quizvision.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping("answer/{questionId}")
    public void addAnswer(@RequestBody AnswerDto answerDto, @PathVariable Long questionId){
        answerService.addAnswer(answerDto, questionId);
    }

    @GetMapping("/answer/{questionId}")
    public List<AnswerDto> getAnswers(@PathVariable Long questionId){
        return answerService.getAnswers(questionId);
    }
}
