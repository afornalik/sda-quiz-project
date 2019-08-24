package sda.quiz.service.mapper.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sda.quiz.dto.QuizDto;
import sda.quiz.entity.Quiz;
import sda.quiz.service.mapper.IMapper;

import java.util.stream.Collectors;

@Component
public class QuizMapper implements IMapper<Quiz, QuizDto> {


    private final ModelMapper modelMapper;
    private final QuestionMapper questionMapper;


    @Autowired
    public QuizMapper(ModelMapper modelMapper, QuestionMapper questionMapper) {
        this.modelMapper = modelMapper;
        this.questionMapper = questionMapper;
    }

    @Override
    public Quiz convertDtoToEntity(QuizDto quizDto) {
        Quiz quiz = new Quiz();
        modelMapper.map(quizDto,quiz);
        if(quizDto.getQuestions() != null){
            quiz.setQuestions(quizDto.getQuestions().stream().map(questionMapper::convertDtoToEntity).collect(Collectors.toList()));
        }
        return quiz;
    }

    @Override
    public QuizDto convertEntityToDto(Quiz quiz) {
        QuizDto quizDto = new QuizDto();
        modelMapper.map(quiz,quizDto);
        quizDto.setQuestions(quiz.getQuestions().stream().map(questionMapper::convertEntityToDto).collect(Collectors.toList()));
        return quizDto;
    }
}
