package sda.quiz.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question")
    private Long idQuestion;

    @Column(name = "question",nullable = false)
    private String question;

    @Column(name="point")
    private Integer point;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "question")
    private List<Answer> answerList;


}
