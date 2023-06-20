package com.dibb.quizvision.dtos;

import com.dibb.quizvision.entities.Answer;
import com.dibb.quizvision.entities.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto implements Serializable {
    private Long id;
    private String answer;
    private boolean correctAnswer;
    private QuestionDto questionDto;

    public AnswerDto(Answer answer){
        if (answer.getId() != null){
            this.id = answer.getId();
        }
        if (answer.getAnswer() != null){
            this.answer = answer.getAnswer();
        }
    }
}
