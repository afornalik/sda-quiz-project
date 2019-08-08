package sda.quiz.service.mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sda.quiz.dto.AnswerDto;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Answer;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AnswerMapperTest {


    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private QuestionMapper questionMapper;

    QuestionDto questionDto;

    @Before
    public void init() {

        questionDto = new QuestionDto(4l,"What is your name",5,null);


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