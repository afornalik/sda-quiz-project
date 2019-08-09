package sda.quiz.service.validator;

import org.springframework.stereotype.Component;
import sda.quiz.dto.QuestionDto;
import sda.quiz.service.validator.exception.QuestionValidationException;

@Component
public class QuestionIValidator implements IValidator<QuestionDto> {



    @Override
    public boolean isCorrect(QuestionDto questionDto) throws Exception {

   /*     if(questionDto.getPoint() == null ) {
            throw new QuestionValidationException("Set point for question");
        }
        if(questionDto.getPoint() <0) {
            throw new QuestionValidationException("Point cannot have minus value");
        }
        if (questionDto.getPoint() == 0){
            throw new QuestionValidationException("Point cannot be set to 0");
        }
        if(questionDto.getQuestion() == null ){
            throw new QuestionValidationException("Please enter question");
        }
        if(questionDto.getQuestion().length() <10){
            throw new QuestionValidationException("Question is to short(min 10 character");
        }
        if(questionDto.getQuestion().length() >255) {
            throw new QuestionValidationException("Question is to long (max 255 character)");
        }
        if(questionDto.getAnswersList().size() <2){
            throw new QuestionValidationException("Question need to have at least two answer");
        }
        if(questionDto.getAnswersList().size() >6){
            throw new QuestionValidationException("Question cannot have more than 6 answer");
        }*/

        return true;
    }
}
