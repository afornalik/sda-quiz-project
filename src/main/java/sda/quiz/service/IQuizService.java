package sda.quiz.service;


import sda.quiz.dto.QuizDto;

public interface  IQuizService {

    QuizDto createEmptyQuiz();

    void saveQuiz(QuizDto quizDto);
}
