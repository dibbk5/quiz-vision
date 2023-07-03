package com.dibb.quizvision.services;

import com.dibb.quizvision.dtos.ScoreDto;
import com.dibb.quizvision.entities.Answer;
import com.dibb.quizvision.entities.Score;
import com.dibb.quizvision.entities.User;
import com.dibb.quizvision.repositories.ScoreRepository;
import com.dibb.quizvision.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addScore(ScoreDto scoreDto, Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        Score score = new Score(scoreDto);
        userOptional.ifPresent(score::setUser);
        scoreRepository.saveAndFlush(score);
    }

    @Override
    public List<ScoreDto> getUserScores(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Score> scoreList = scoreRepository.findAllByUserEquals(userOptional.get());
            return scoreList.stream().map(score -> new ScoreDto(score)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
