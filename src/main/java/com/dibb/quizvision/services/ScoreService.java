package com.dibb.quizvision.services;

import com.dibb.quizvision.dtos.ScoreDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ScoreService {
    @Transactional
    void addScore(ScoreDto scoreDto, Long userId);

    List<ScoreDto> getUserScores(Long userId);
}
