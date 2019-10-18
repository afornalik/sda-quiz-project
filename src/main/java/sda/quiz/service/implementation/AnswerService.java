package sda.quiz.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.AnswerDto;
import sda.quiz.entity.Answer;
import sda.quiz.entity.Question;
import sda.quiz.service.IAnswerService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AnswerService implements IAnswerService {

    @Override
    public AnswerDto setAnswerToFalse(AnswerDto answerDto) {
        answerDto.setIsCorrect(false);
        return answerDto;
    }

    @Override
    public List<Answer> createAnswerList(Question question, int quantityOfAnswer) {
        return fillAnswerList(new ArrayList<>(),question,quantityOfAnswer);
    }

    private List<Answer> fillAnswerList(List<Answer> answerList,Question question,int quantityOfAnswer){
        if (quantityOfAnswer == 0) {
            return answerList;
        }
        Answer answer = new Answer();
        answer.setQuestion(question);
        answerList.add(answer);
        return fillAnswerList(answerList,question,quantityOfAnswer-1);
    }

}
