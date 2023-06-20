package com.dibb.quizvision.entities;

import com.dibb.quizvision.dtos.AnswerDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Quiz_Answers")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Answer(AnswerDto answerDto){
        if (answerDto.getAnswer() != null){
            this.answer = answerDto.getAnswer();
        }
    }
}
