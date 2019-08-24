package sda.quiz.service.mapper.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Question;
import sda.quiz.service.mapper.IMapper;

import java.util.stream.Collectors;


@Component
public class QuestionMapper implements IMapper<Question, QuestionDto> {

    private final ModelMapper modelMapper;
    private final AnswerMapper answerMapper;

    @Autowired
    public QuestionMapper(ModelMapper modelMapper, AnswerMapper answerMapper) {
        this.modelMapper = modelMapper;
        this.answerMapper = answerMapper;
    }

    @Override
    public Question convertDtoToEntity(QuestionDto questionDto) {
        Question question = new Question();
        modelMapper.map(questionDto,question);
        question.setAnswerList(questionDto.getAnswersList().stream().map(answerMapper::convertDtoToEntity).collect(Collectors.toList()));
        return question;
    }

    @Override
    public QuestionDto convertEntityToDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        modelMapper.map(question,questionDto);
        questionDto.setAnswersList(question.getAnswerList().stream().map(answerMapper::convertEntityToDto).collect(Collectors.toList()));
        return questionDto;
    }
}
