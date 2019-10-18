package sda.quiz.entity.user.response;


import lombok.Getter;
import lombok.Setter;
import sda.quiz.entity.Question;

import javax.persistence.*;

@Entity
@Table(name = "answer_done")
@Getter
@Setter
public class AnswerDone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_answer_done")
    private Long idAnswerGiven;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_done_id")
    private QuizDone quizDone;

    @Column(name = "is_user_choose_correct",nullable = false)
    private Boolean isUserChooseCorrect;


}
