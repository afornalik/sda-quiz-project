package sda.quiz.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sda.quiz.dto.QuizDto;
import sda.quiz.entity.Quiz;

@Component
public class QuizMapper implements IMapper<Quiz, QuizDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public QuizMapper(ModelMapper modelMapper) {

        this.modelMapper = modelMapper;
    }

    @Override
    public Quiz convertDtoToEntity(QuizDto quizDto) {
        Quiz quiz = new Quiz();
        modelMapper.map(quizDto,quiz);
        return quiz;
    }

    @Override
    public QuizDto convertEntityToDto(Quiz quiz) {
        QuizDto quizDto = new QuizDto();
        modelMapper.map(quiz,quizDto);
        return quizDto;
    }
}
