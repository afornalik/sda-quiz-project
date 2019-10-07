package sda.quiz.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sda.quiz.entity.Answer;

import java.util.List;
import java.util.Objects;
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
    private List<QuizDto> quiz;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto that = (QuestionDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(question, that.question) &&
                Objects.equals(point, that.point) &&
                Objects.equals(answersList, that.answersList) &&
                Objects.equals(quiz, that.quiz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, point, answersList, quiz);
    }
}
