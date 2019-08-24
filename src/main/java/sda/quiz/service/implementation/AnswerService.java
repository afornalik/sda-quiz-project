package sda.quiz.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.AnswerDto;
import sda.quiz.entity.Answer;
import sda.quiz.service.IAnswerService;
import sda.quiz.service.implementation.exception.AnswersAreNullException;
import sda.quiz.service.implementation.exception.MismatchIdException;

@Service
@Transactional
public class AnswerService implements IAnswerService {

    @Override
    public AnswerDto setAnswerToFalse(AnswerDto answerDto) {
        answerDto.setIsCorrect(false);
        return answerDto;
    }

    @Override
    public boolean checkAnswer(Answer answer, AnswerDto userAnswer) throws AnswersAreNullException, MismatchIdException {
        if(answer == null || userAnswer == null){
            throw new AnswersAreNullException("One of answer is not set");
        }else if(!answer.getIdAnswer().equals(userAnswer.getIdAnswer())){
            throw new MismatchIdException("Answer id number mismatch");
        } else {
            return answer.getIsCorrect() == userAnswer.getIsCorrect();
        }
    }
}
