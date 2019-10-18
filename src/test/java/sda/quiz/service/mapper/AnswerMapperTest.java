package sda.quiz.service.mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sda.quiz.dto.AnswerDto;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Answer;

import sda.quiz.service.mapper.implementation.AnswerMapper;
import sda.quiz.service.mapper.implementation.QuestionMapper;

@SpringBootTest
@RunWith(JUnit4.class)
public class AnswerMapperTest {

    private ModelMapper modelMapper = new ModelMapper();
    private AnswerMapper answerMapper  = new AnswerMapper(modelMapper);



    private QuestionDto questionDto;

    @Before
    public void init() {
        questionDto = new QuestionDto(4l,"What is your name",5,null,null);
    }

    @Test
    public void shouldConvertDtoToEntity(){
        //given
        AnswerDto answerDto = new AnswerDto(5l,"yes it's true",true,questionDto);

        //when
        Answer answer = answerMapper.convertDtoToEntity(answerDto);

        //than
        Assert.assertEquals(answerDto.getIdAnswer(),answer.getIdAnswer());
        Assert.assertEquals(answerDto.getAnswer(),answer.getAnswer());
        Assert.assertEquals(answerDto.getIsCorrect(),answer.getIsCorrect());
        Assert.assertEquals(answerDto.getQuestion().getId(),answer.getQuestion().getIdQuestion());
    }
}