package sda.quiz.service.mapper.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sda.quiz.dto.user.response.QuizTakenDto;
import sda.quiz.entity.user.response.QuizTaken;
import sda.quiz.service.mapper.IMapper;

import java.util.stream.Collectors;

@Component
public class QuizTakenMapper implements IMapper<QuizTaken, QuizTakenDto> {

    private final ModelMapper modelMapper;
    private final AnswerGivenMapper answerGivenMapper;

    @Autowired
    public QuizTakenMapper(ModelMapper modelMapper, AnswerGivenMapper answerGivenMapper) {
        this.modelMapper = modelMapper;
        this.answerGivenMapper = answerGivenMapper;
    }

    @Override
    public QuizTaken convertDtoToEntity(QuizTakenDto quizTakenDto) {

        QuizTaken quizTaken = new QuizTaken();
        modelMapper.map(quizTakenDto, quizTaken);

        quizTaken.setAnswersGiven(
                quizTakenDto.getAnswerGivenDtoList()
                        .stream()
                        .map(answerGivenMapper::convertDtoToEntity)
                        .collect(Collectors.toList()));

        return quizTaken;
    }

    @Override
    public QuizTakenDto convertEntityToDto(QuizTaken quizTaken) {

        QuizTakenDto quizTakenDto = new QuizTakenDto();
        modelMapper.map(quizTaken,quizTakenDto);

        quizTakenDto.setAnswerGivenDtoList(
                quizTaken.getAnswersGiven()
                        .stream()
                        .map(answerGivenMapper::convertEntityToDto)
                        .collect(Collectors.toList()));

        return quizTakenDto;
    }
}
