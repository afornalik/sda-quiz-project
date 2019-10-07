package sda.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sda.quiz.entity.utilities.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {

    private Long idQuiz;
    private String title;
    private String description;
    private LocalDate createDate;
    private List<QuestionDto> questions;
    private Category category;



}
