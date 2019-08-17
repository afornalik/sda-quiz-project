package sda.quiz.service;

        import sda.quiz.dto.QuestionDto;

        import java.util.List;
        import java.util.Set;

public interface IQuestionService {

    void saveNewQuestion(QuestionDto questionDto) throws Exception;

    QuestionDto createEmptyQuestionWith4Answer();

    Set<QuestionDto> getAllQuestions();


    List<QuestionDto> showAllAvailableQuestion();

    QuestionDto setAllAnswerToFalse(QuestionDto questionDto);
}
