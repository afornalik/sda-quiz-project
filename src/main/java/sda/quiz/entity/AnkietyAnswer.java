package sda.quiz.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "ankiety_answer")
@Getter
@Setter
public class AnkietyAnswer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_ankiety_answer")
    private Long idAnkietyAnswer;


}
