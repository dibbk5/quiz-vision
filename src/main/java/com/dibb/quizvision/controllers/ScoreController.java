package com.dibb.quizvision.controllers;

import com.dibb.quizvision.dtos.ScoreDto;
import com.dibb.quizvision.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/scores")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @PostMapping("/score/{userId}")
    public void addScore(@RequestBody ScoreDto scoreDto, @PathVariable Long userId){
        scoreService.addScore(scoreDto, userId);
    }

    @GetMapping("/score/{userId}")
    public List<ScoreDto> getAllScoresForUser(@PathVariable Long userId){
        return scoreService.getUserScores(userId);
    }
}
