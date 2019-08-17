package sda.quiz.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sda.quiz.dto.QuizDto;
import sda.quiz.dto.user.UserDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizTakenDto {

    private Long idQuizTaken;
    private UserDto userDto;
    private QuizDto quizDto;
    private List<AnswerGivenDto> answerGivenDtoList;
}
