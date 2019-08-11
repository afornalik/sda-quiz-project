package sda.quiz.service;


import sda.quiz.dto.QuizDto;

import java.util.List;

public interface  IQuizService {

    QuizDto createEmptyQuiz();

    void saveQuiz(QuizDto quizDto,Long[] questionIdList);

    List<QuizDto> getAllQuiz();
}
