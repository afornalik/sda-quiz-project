package sda.quiz.service;

        import sda.quiz.dto.QuestionDto;
        import sda.quiz.entity.Question;
        import sda.quiz.entity.Quiz;
        import sda.quiz.service.implementation.exception.MismatchIdException;

        import java.util.List;
        import java.util.Set;

public interface IQuestionService {


    QuestionDto setAllAnswerToFalse(QuestionDto questionDto);

    List<Question> createQuestionList( int quantityOfQuestion, int quantityOfAnswer);

}
