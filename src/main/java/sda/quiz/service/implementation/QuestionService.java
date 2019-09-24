package sda.quiz.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.AnswerDto;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Answer;
import sda.quiz.entity.Question;
import sda.quiz.repository.IAnswerRepository;
import sda.quiz.repository.IQuestionRepository;
import sda.quiz.service.IAnswerService;
import sda.quiz.service.IQuestionService;
import sda.quiz.service.implementation.exception.MismatchIdException;
import sda.quiz.service.mapper.implementation.AnswerMapper;
import sda.quiz.service.mapper.implementation.QuestionMapper;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class QuestionService implements IQuestionService {


    private final IQuestionRepository questionRepository;
    private final IAnswerService answerService;
    private final QuestionMapper questionMapper;


    public QuestionService(IQuestionRepository questionRepository, IAnswerService answerService, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.answerService = answerService;
        this.questionMapper = questionMapper;
    }


    @Override
    public Set<QuestionDto> getAllQuestions() {
        return questionRepository.findAll()
                .stream()
                .map(questionMapper::convertEntityToDto)
                .collect(Collectors.toSet());
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
                Optional<Answer> answerDto1 = question.getAnswerList()
                        .stream()
                        .filter(answer -> answer.getIdAnswer()
                                .equals(answerDto.getIdAnswer()))
                        .findAny();
                if (answerDto1.isPresent()) {
                    if (!answerService.checkAnswer(answerDto1.get(), answerDto)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }


}
