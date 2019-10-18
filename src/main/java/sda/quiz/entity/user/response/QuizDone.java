package sda.quiz.entity.user.response;


import lombok.Getter;
import lombok.Setter;
import sda.quiz.entity.Quiz;
import sda.quiz.entity.user.User;

import javax.persistence.*;
import java.util.List;

@Table(name="quiz_done")
@Entity
@Getter
@Setter
public class QuizDone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quiz_done")
    private Long idQuizTake;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quizTaken;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "quizDone")
    private List<AnswerDone> answersGiven;


}
