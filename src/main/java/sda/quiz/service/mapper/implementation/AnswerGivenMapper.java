package sda.quiz.service.mapper.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sda.quiz.dto.user.response.AnswerDoneDto;
import sda.quiz.entity.user.response.AnswerDone;
import sda.quiz.service.mapper.IMapper;

@Component
public class AnswerGivenMapper implements IMapper<AnswerDone, AnswerDoneDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public AnswerGivenMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AnswerDone convertDtoToEntity(AnswerDoneDto answerDoneDto) {
        AnswerDone answerDone = new AnswerDone();
        modelMapper.map(answerDoneDto, answerDone);
        return answerDone;
    }

    @Override
    public AnswerDoneDto convertEntityToDto(AnswerDone answerDone) {
        AnswerDoneDto answerDoneDto = new AnswerDoneDto();
        modelMapper.map(answerDone, answerDoneDto);
        return answerDoneDto;
    }
}
