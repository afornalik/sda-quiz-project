package sda.quiz.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Question;
import sda.quiz.repository.IAnswerRepository;
import sda.quiz.repository.IQuestionRepository;
import sda.quiz.service.mapper.exception.ConvertDtoToEntityException;

import java.util.Optional;

@Component
public class QuestionMapper implements Mapper<Question, QuestionDto> {

    @Autowired
    private IAnswerRepository answerRepository;

    @Autowired
    private IQuestionRepository questionRepository;


    @Override
    public Question convertDtoToEntity(QuestionDto questionDto) throws ConvertDtoToEntityException {
        Question question;
        try {

            if (questionDto.getId() == null) {
                question = new Question();
            } else {
                question = questionRepository.findById(questionDto.getId()).get();
            }
            question.setAnswerList(questionDto.getAnswers());
            question.setContent(questionDto.getQuestion());
            question.setPoint(questionDto.getPoint());
        } catch (Exception e) {
            throw new ConvertDtoToEntityException();
        }
        return question;
    }

    @Override
    public QuestionDto convertEntityToDto(Question question) {
        QuestionDto questionDto = new QuestionDto(
                question.getIdQuestion(), question.getContent(), question.getPoint(), question.getAnswerList());
        return questionDto;
    }
}
