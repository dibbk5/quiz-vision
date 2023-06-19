package com.dibb.quizvision.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Quiz_Answers")
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "answer")
    private String answer;

    @Column(name = "correct_answer")
    private boolean correctAnswer;

    @ManyToOne
    @JsonBackReference
    private Question question;
}
