package sda.quiz.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Answer;
import sda.quiz.entity.Question;
import sda.quiz.entity.Quiz;
import sda.quiz.service.IAnswerService;
import sda.quiz.service.IQuestionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    public List<Question> createQuestionList( int quantityOfQuestion, int quantityOfAnswer) {
        return fillQuestionList(new ArrayList<>(),quantityOfQuestion,quantityOfAnswer);
    }

    private List<Question> fillQuestionList( List<Question> questionList, int quantityOfQuestion, int quantityOfAnswer) {
        if (quantityOfQuestion == 0) {
            return questionList;
        }
        Question question = new Question();
        question.setAnswerList(answerService.createAnswerList(question,quantityOfAnswer));

        questionList.add(question);
        return fillQuestionList(questionList, quantityOfQuestion - 1, quantityOfAnswer);
    }


}
