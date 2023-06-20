package com.dibb.quizvision.dtos;

import com.dibb.quizvision.entities.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto implements Serializable {
    private Long id;
    private String quizName;
    private UserDto userDto;
    private Set<QuestionDto> questionDtoSet = new HashSet<>();

    public QuizDto(Quiz quiz){
        if (quiz.getId() != null){
            this.id = quiz.getId();
        }
        if (quiz.getQuizName() != null) {
            this.quizName = quiz.getQuizName();
        }
    }
}
