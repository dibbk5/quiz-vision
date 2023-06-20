package com.dibb.quizvision.dtos;

import com.dibb.quizvision.entities.Score;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDto implements Serializable {
    private Long id;
    private double score;
    private String quizName;
    private UserDto userDto;

    public ScoreDto(Score score){
        if (score.getId() != null){
            this.id = score.getId();
        }
        if (score.getQuizName() != null){
            this.quizName = score.getQuizName();
        }
    }
}
