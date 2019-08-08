package sda.quiz.service.mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import sda.quiz.QuizApplication;
import sda.quiz.dto.AnswerDto;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Answer;
import sda.quiz.entity.Question;
import sda.quiz.repository.IQuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionMapperTest {


    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

    private QuestionDto dummyQuestionDto;

    private List<AnswerDto> answerList;

    @Before
    public void init() {
        Answer answer1 = new Answer(5l,"fsfsd",true,null);
        Answer answer2 = answer1;
        Answer answer3 = answer2;

        answerList = new ArrayList<>();
        answerList.add(answerMapper.convertEntityToDto(answer1));
        answerList.add(answerMapper.convertEntityToDto(answer2));
        answerList.add(answerMapper.convertEntityToDto(answer3));
    }

    @Test
    public void shouldReturnEntity()  {
        //given
        dummyQuestionDto = new QuestionDto(null,"What is my name ?",5,answerList);

        //when
        Question result = questionMapper.convertDtoToEntity(dummyQuestionDto);
        result.setAnswerList(dummyQuestionDto.getAnswersList().stream().map(answerDto -> answerMapper.convertDtoToEntity(answerDto)).collect(Collectors.toList()));

        //than
        Assert.assertEquals(result.getIdQuestion(),dummyQuestionDto.getId());
        Assert.assertEquals(result.getAnswerList().size(),dummyQuestionDto.getAnswersList().size());
        Assert.assertEquals(result.getQuestion(),dummyQuestionDto.getQuestion());
        Assert.assertEquals(result.getPoint(),dummyQuestionDto.getPoint());
    }



}