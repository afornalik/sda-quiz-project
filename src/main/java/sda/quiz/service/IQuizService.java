package sda.quiz.service;


import sda.quiz.dto.QuestionDto;
import sda.quiz.dto.QuizDto;

import java.util.List;
import java.util.Map;

public interface  IQuizService {

    QuizDto createEmptyQuiz();

    void saveQuiz(QuizDto quizDto,Long[] questionIdList);

    List<QuizDto> getAllQuiz();

    void deleteQuiz(Long id);

    QuizDto getQuizById(Long id ,boolean resetAnswer);

    Map<QuestionDto, Boolean> checkAllAnswer(QuizDto quizDto);
}
