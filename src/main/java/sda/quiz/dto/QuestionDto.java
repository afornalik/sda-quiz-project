package sda.quiz.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sda.quiz.entity.Answer;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Long id;
    private String question;
    private Integer point;
    private List<AnswerDto> answersList;
    private Set<QuizDto> quiz;

}
