package sda.quiz.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sda.quiz.dto.AnswerDto;
import sda.quiz.entity.Answer;

@Component
public class AnswerMapper implements IMapper<Answer, AnswerDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public AnswerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Answer convertDtoToEntity(AnswerDto answerDto)  {
        Answer answer = new Answer();
        modelMapper.map(answerDto,answer);
        return answer;
    }

    @Override
    public AnswerDto convertEntityToDto(Answer answer)  {
        AnswerDto answerDto = new AnswerDto();
        modelMapper.map(answer,answerDto);
        return answerDto;
    }
}
