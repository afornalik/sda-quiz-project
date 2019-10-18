package sda.quiz.service.mapper.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sda.quiz.dto.user.response.QuizDoneDto;
import sda.quiz.entity.user.response.QuizDone;
import sda.quiz.service.mapper.IMapper;

import java.util.stream.Collectors;

@Component
public class QuizTakenMapper implements IMapper<QuizDone, QuizDoneDto> {

    private final ModelMapper modelMapper;
    private final AnswerGivenMapper answerGivenMapper;

    @Autowired
    public QuizTakenMapper(ModelMapper modelMapper, AnswerGivenMapper answerGivenMapper) {
        this.modelMapper = modelMapper;
        this.answerGivenMapper = answerGivenMapper;
    }

    @Override
    public QuizDone convertDtoToEntity(QuizDoneDto quizDoneDto) {

        QuizDone quizDone = new QuizDone();
        modelMapper.map(quizDoneDto, quizDone);

        quizDone.setAnswersGiven(
                quizDoneDto.getAnswerDoneDtoList()
                        .stream()
                        .map(answerGivenMapper::convertDtoToEntity)
                        .collect(Collectors.toList()));

        return quizDone;
    }

    @Override
    public QuizDoneDto convertEntityToDto(QuizDone quizDone) {

        QuizDoneDto quizDoneDto = new QuizDoneDto();
        modelMapper.map(quizDone, quizDoneDto);

        quizDoneDto.setAnswerDoneDtoList(
                quizDone.getAnswersGiven()
                        .stream()
                        .map(answerGivenMapper::convertEntityToDto)
                        .collect(Collectors.toList()));

        return quizDoneDto;
    }
}
