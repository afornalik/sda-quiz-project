package sda.quiz.service;

import sda.quiz.dto.AnswerDto;
import sda.quiz.entity.Answer;
import sda.quiz.entity.Question;
import sda.quiz.service.implementation.exception.MismatchIdException;

import java.util.List;

public interface IAnswerService {

    AnswerDto setAnswerToFalse(AnswerDto answerDto);

    List<Answer> createAnswerList(Question question, int quantityOfAnswer);

}
