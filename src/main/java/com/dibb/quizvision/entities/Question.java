package com.dibb.quizvision.entities;

import com.dibb.quizvision.dtos.QuestionDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Quiz_Questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne
    @JsonBackReference
    private Quiz quiz;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Answer> answerSet = new HashSet<>();

    public Question(QuestionDto questionDto){
        if (questionDto.getDescription() != null) {
            this.description = questionDto.getDescription();
        }
    }
}
