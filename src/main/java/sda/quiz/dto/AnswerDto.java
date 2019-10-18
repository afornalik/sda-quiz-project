package sda.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sda.quiz.entity.Question;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {



    private Long idAnswer;
    private String answer;
    private Boolean isCorrect;
    private QuestionDto question;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDto answerDto = (AnswerDto) o;
        return Objects.equals(idAnswer, answerDto.idAnswer) &&
                Objects.equals(answer, answerDto.answer) &&
                Objects.equals(isCorrect, answerDto.isCorrect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnswer, answer, isCorrect);
    }
}
