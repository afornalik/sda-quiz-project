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
import sda.quiz.entity.Question;
//import sda.quiz.service.mapper.implementation.AnswerMapper;
//import sda.quiz.service.mapper.implementation.QuestionMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionMapperTest {


    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

    private QuestionDto dummyQuestionDto;
    private Question dummyEntityQuestion;

    private List<AnswerDto> answerList;
    private List<Answer> answerEntityList;

    @Before
    public void init() {
        Answer answer1 = new Answer(5l,"fsfsd",true,null);
        Answer answer2 = answer1;
        Answer answer3 = answer2;

        answerEntityList = new ArrayList<>(Arrays.asList(answer1,answer2,answer3));

        answerList = new ArrayList<>();
        answerList.add(answerMapper.convertEntityToDto(answer1));
        answerList.add(answerMapper.convertEntityToDto(answer2));
        answerList.add(answerMapper.convertEntityToDto(answer3));
    }

    @Test
    public void shouldReturnQuestionEntity()  {
        //given
        //dummyQuestionDto = new QuestionDto(null,"What is my name ?",5,answerList,null);

        //when
        Question result = questionMapper.convertDtoToEntity(dummyQuestionDto);

        //than
        Assert.assertEquals(result.getIdQuestion(),dummyQuestionDto.getId());
        Assert.assertEquals(result.getAnswerList().size(),dummyQuestionDto.getAnswersList().size());
        Assert.assertEquals(result.getQuestion(),dummyQuestionDto.getQuestion());
        Assert.assertEquals(result.getPoint(),dummyQuestionDto.getPoint());
    }

    @Test
    public void shouldReturnQuestionDto() {
        //given
    //    dummyEntityQuestion = new Question(5l,"sdfsdf",5,answerEntityList,null);

        //when
        QuestionDto result = questionMapper.convertEntityToDto(dummyEntityQuestion);

        //then
        Assert.assertEquals(result.getId(),dummyEntityQuestion.getIdQuestion());
        Assert.assertEquals(result.getAnswersList().size(),dummyEntityQuestion.getAnswerList().size());
        Assert.assertEquals(result.getPoint(),dummyEntityQuestion.getPoint());
        Assert.assertEquals(result.getQuestion(),dummyEntityQuestion.getQuestion());
      //  Assert.assertEquals(result.getQuiz(),dummyEntityQuestion.getQuiz());


    }



}