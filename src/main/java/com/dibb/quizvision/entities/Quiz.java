package com.dibb.quizvision.entities;

import com.dibb.quizvision.dtos.QuizDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Quizzes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "quiz_name")
    private String quizName;

    @ManyToOne
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Question> questionSet = new HashSet<>();

    public Quiz(QuizDto quizDto){
        if (quizDto.getQuizName() != null) {
            this.quizName = quizDto.getQuizName();
        }
    }
}
