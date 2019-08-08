package sda.quiz.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sda.quiz.entity.Answer;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Long id;
    private String question;
    private Integer point;
    private List<AnswerDto> answersList;

}
