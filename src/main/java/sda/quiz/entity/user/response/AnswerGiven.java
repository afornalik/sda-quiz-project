package sda.quiz.entity.user.response;


import lombok.Getter;
import lombok.Setter;
import sda.quiz.entity.Question;

import javax.persistence.*;

@Entity
@Table(name = "answer_given")
@Getter
@Setter
public class AnswerGiven {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_answer_given")
    private Long idAnswerGiven;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_taken_id")
    private QuizTaken quizTaken;

    @Column(name = "is_user_choose_correct",nullable = false)
    private Boolean isUserChooseCorrect;


}
