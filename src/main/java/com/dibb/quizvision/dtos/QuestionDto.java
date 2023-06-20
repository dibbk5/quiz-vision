package com.dibb.quizvision.dtos;

import com.dibb.quizvision.entities.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto implements Serializable {
    private Long id;
    private String description;
    private QuizDto quizDto;
    private Set<AnswerDto> answerDtoSet = new HashSet<>();

    public QuestionDto(Question question){
        if (question.getId() != null){
            this.id = question.getId();
        }
        if (question.getDescription() != null) {
            this.description = question.getDescription();
        }
    }
}
