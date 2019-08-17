package sda.quiz.service.mapper.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sda.quiz.dto.user.response.AnswerGivenDto;
import sda.quiz.entity.user.response.AnswerGiven;
import sda.quiz.service.mapper.IMapper;

@Component
public class AnswerGivenMapper implements IMapper<AnswerGiven, AnswerGivenDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public AnswerGivenMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AnswerGiven convertDtoToEntity(AnswerGivenDto answerGivenDto) {
        AnswerGiven answerGiven = new AnswerGiven();
        modelMapper.map(answerGivenDto,answerGiven);
        return answerGiven;
    }

    @Override
    public AnswerGivenDto convertEntityToDto(AnswerGiven answerGiven) {
        AnswerGivenDto answerGivenDto = new AnswerGivenDto();
        modelMapper.map(answerGiven,answerGivenDto);
        return answerGivenDto;
    }
}
