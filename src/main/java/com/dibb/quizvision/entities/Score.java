package com.dibb.quizvision.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Scores")
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
}
