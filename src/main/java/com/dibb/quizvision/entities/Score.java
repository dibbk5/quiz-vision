package com.dibb.quizvision.entities;

import com.dibb.quizvision.dtos.ScoreDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Scores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "score")
    private double score;

    @Column(name = "quiz_name")
    private String quizName;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Score(ScoreDto scoreDto){
        if (scoreDto.getQuizName() != null){
            this.quizName = scoreDto.getQuizName();
        }
    }
}
