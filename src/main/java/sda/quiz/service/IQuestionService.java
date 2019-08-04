package sda.quiz.service;

import sda.quiz.dto.QuestionDto;

import java.util.List;

public interface IQuestionService {

    void createNewQuestion(QuestionDto questionDto) throws Exception;

    List<QuestionDto> showAllAvailableQuestion();
}
