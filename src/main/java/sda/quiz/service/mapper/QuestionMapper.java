package sda.quiz.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Question;


@Component
public class QuestionMapper implements IMapper<Question, QuestionDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public QuestionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Question convertDtoToEntity(QuestionDto questionDto) {
        Question question = new Question();
        modelMapper.map(questionDto,question);
        return question;
    }

    @Override
    public QuestionDto convertEntityToDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        modelMapper.map(question,questionDto);
        return questionDto;
    }
}
