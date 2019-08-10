package sda.quiz.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quiz")
    private Long idQuiz;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name="create_date")
    private LocalDate createDate;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "quiz")
    private Set<Question> questions;
}
