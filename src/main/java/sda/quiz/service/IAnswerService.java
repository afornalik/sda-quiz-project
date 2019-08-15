package sda.quiz.service;

import sda.quiz.dto.AnswerDto;

import java.util.List;

public interface IAnswerService {

    List<AnswerDto> getAllAnswerForQuestion(Long id);
}
