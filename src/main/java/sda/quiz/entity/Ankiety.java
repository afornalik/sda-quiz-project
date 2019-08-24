package sda.quiz.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ankiety")
@Getter
@Setter
public class Ankiety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ankiety")
    private Long idAnkiety;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String nameAnkiety;

    private String qustionAnkieta;
    private String qustionAnkietaAnswer1;
    private String qustionAnkietaAnswer2;
    private String qustionAnkietaAnswer3;
    private String qustionAnkietaAnswer4;

    private String qustionAnkieta1;
    private String qustionAnkieta1Answer1;
    private String qustionAnkieta1Answer2;
    private String qustionAnkieta1Answer3;
    private String qustionAnkieta1Answer4;

    private String qustionAnkieta2;
    private String qustionAnkieta2Answer1;
    private String qustionAnkieta2Answer2;
    private String qustionAnkieta2Answer3;
    private String qustionAnkieta2Answer4;

    private String qustionAnkieta3;
    private String qustionAnkieta3Answer1;
    private String qustionAnkieta3Answer2;
    private String qustionAnkieta3Answer3;
    private String qustionAnkieta3Answer4;

    private String qustionAnkieta4;
    private String qustionAnkieta4Answer1;
    private String qustionAnkieta4Answer2;
    private String qustionAnkieta4Answer3;
    private String qustionAnkieta4Answer4;



    //matryca
    private Integer scaleOfResponse;

    private boolean singleOrMultiple;

    private String question;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private String question7;
    private String question8;
    private String question9;
    private String answer;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;
    private String answer6;
    private String answer7;
    private String answer8;
    private String answer9;

    //otwarte

    private String questionOpen;
    private String questionOpen1;
    private String questionOpen2;
    private String questionOpen3;
    private String questionOpen4;
}