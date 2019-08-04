package sda.quiz.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Question;
import sda.quiz.repository.IAnswerRepository;
import sda.quiz.repository.IQuestionRepository;

import java.util.Optional;

public class QuestionMapper implements Mapper<Question, QuestionDto> {

    @Autowired
    private IAnswerRepository answerRepository;

    @Autowired
    private IQuestionRepository questionRepository;


    @Override
    public Question convertDtoToEntity(QuestionDto questionDto) {
        Question question;
        if(questionDto.getId() == null){
            question = new Question();
        }else {
            question= questionRepository.findById(questionDto.getId()).get();
        }
        question.setAnswerList(questionDto.getAnswers());
        question.setContent(questionDto.getQuestion());
        question.setPoint(questionDto.getPoint());
        return question;
    }

    @Override
    public QuestionDto convertEntityToDto(Question question) {
        QuestionDto questionDto = new QuestionDto(
                question.getIdQuestion(),question.getContent(),question.getPoint(),question.getAnswerList());
        return questionDto;
    }
}
