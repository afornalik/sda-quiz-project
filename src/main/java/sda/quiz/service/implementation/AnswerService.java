package sda.quiz.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.quiz.dto.AnswerDto;
import sda.quiz.entity.Answer;
import sda.quiz.service.IAnswerService;
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
    public boolean checkAnswer(Answer answer, AnswerDto userAnswer) throws MismatchIdException {
         if(!answer.getIdAnswer().equals(userAnswer.getIdAnswer())){
            throw new MismatchIdException("Answer ID number mismatch");
        } else {
            return answer.getIsCorrect() == userAnswer.getIsCorrect();
        }
    }
}
