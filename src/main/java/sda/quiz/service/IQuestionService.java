package sda.quiz.service;

        import sda.quiz.dto.QuestionDto;
        import sda.quiz.entity.Question;
        import sda.quiz.service.implementation.exception.MismatchIdException;

        import java.util.Set;

public interface IQuestionService {


    QuestionDto setAllAnswerToFalse(QuestionDto questionDto);

    boolean checkAnswerToQuestion(Question question, QuestionDto questionDto) throws MismatchIdException;

}
