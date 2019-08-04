package sda.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sda.quiz.entity.Question;

@Getter
@AllArgsConstructor
public class AnswerDto {


    private Long id;
    private String answer;
    private Boolean correct;


}
