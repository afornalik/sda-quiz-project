package sda.quiz.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sda.quiz.dto.QuestionDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDoneDto {

    private Long idAnswerGiven;
    private QuestionDto questionDto;;
    private QuizDoneDto quizDoneDto;
    private Boolean isUserChooseCorrect;
}
