package sda.quiz.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_answer")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_answer")
    private Long idUserAnswer;



}
