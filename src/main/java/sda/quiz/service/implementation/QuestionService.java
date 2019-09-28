package sda.quiz.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.AnswerDto;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Answer;
import sda.quiz.entity.Question;
import sda.quiz.service.IAnswerService;
import sda.quiz.service.IQuestionService;
import sda.quiz.service.implementation.exception.MismatchIdException;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class QuestionService implements IQuestionService {


    private final IAnswerService answerService;


    public QuestionService(IAnswerService answerService) {
        this.answerService = answerService;
    }



    @Override
    public QuestionDto setAllAnswerToFalse(QuestionDto questionDto) {
        questionDto.setAnswersList(
                questionDto.getAnswersList()
                        .stream()
                        .map(answerService::setAnswerToFalse)
                        .collect(Collectors.toList()));
        return questionDto;

    }

    @Override
    public boolean checkAnswerToQuestion(Question question, QuestionDto questionDto) throws MismatchIdException {
        if (!question.getIdQuestion().equals(questionDto.getId())) {
            throw new MismatchIdException("Question ID number mismatch");
        } else {
            for (AnswerDto answerDto : questionDto.getAnswersList()) {
                Optional<Answer> foundAnswer = question.getAnswerList()
                        .stream()
                        .filter(answer -> answer.getIdAnswer()
                                .equals(answerDto.getIdAnswer()))
                        .findAny();
                if (foundAnswer.isPresent()) {
                    if (!answerService.checkAnswer(foundAnswer.get(), answerDto)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }


}
