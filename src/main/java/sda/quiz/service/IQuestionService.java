package sda.quiz.service;

import sda.quiz.dto.QuestionDto;

import java.util.List;

public interface IQuestionService {

    void saveNewQuestion(QuestionDto questionDto) throws Exception;

    QuestionDto createEmptyQuestionWith4Answer();

    List<QuestionDto> showAllAvailableQuestion();
}
