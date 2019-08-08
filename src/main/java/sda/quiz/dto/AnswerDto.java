package sda.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sda.quiz.entity.Question;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {


    private Long idAnswer;
    private String answer;
    private Boolean isCorrect;
    private QuestionDto question;

}
