package sda.quiz.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question")
    private Long idQuestion;

    @Column(name = "question",nullable = false)
    private String question;

    @Column(name="point")
    private Integer point;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "questions",cascade = CascadeType.ALL)
    private Set<Quiz> quiz;



}
