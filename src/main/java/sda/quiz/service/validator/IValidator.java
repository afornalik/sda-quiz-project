package sda.quiz.service.validator;

import sda.quiz.service.validator.exception.QuestionValidationException;

public interface IValidator<O> {

    boolean isCorrect(O o) throws Exception, QuestionValidationException;
}
