package sda.quiz.service;

import sda.quiz.dto.AnswerDto;
import sda.quiz.entity.Answer;
import sda.quiz.service.implementation.exception.AnswersAreNullException;
import sda.quiz.service.implementation.exception.MismatchIdException;

import java.util.List;

public interface IAnswerService {

    AnswerDto setAnswerToFalse(AnswerDto answerDto);

    boolean checkAnswer(Answer answer, AnswerDto userAnswer) throws AnswersAreNullException, MismatchIdException;
}
