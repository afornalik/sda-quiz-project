package sda.quiz.service;

import sda.quiz.dto.AnswerDto;
import sda.quiz.entity.Answer;
import sda.quiz.service.implementation.exception.MismatchIdException;

public interface IAnswerService {

    AnswerDto setAnswerToFalse(AnswerDto answerDto);

    boolean checkAnswer(Answer answer, AnswerDto userAnswer) throws MismatchIdException;
}
