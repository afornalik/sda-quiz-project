package sda.quiz.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "answer")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_answer")
    private Long idAnswer;

    @Column(name = "answer_body",nullable = false)
    private String answerBody;

    @Column(name ="is_correct",nullable = false)
    private Boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id",nullable = false)
    private Question question;

}
