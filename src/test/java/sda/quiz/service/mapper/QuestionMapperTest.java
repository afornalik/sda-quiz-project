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
import org.springframework.test.context.junit4.SpringRunner;
import sda.quiz.dto.QuestionDto;
import sda.quiz.entity.Answer;
import sda.quiz.entity.Question;
import sda.quiz.repository.IQuestionRepository;
import sda.quiz.service.mapper.exception.ConvertDtoToEntityException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionMapperTest {



    @Mock
    private IQuestionRepository questionRepository;

    @InjectMocks
    private Mapper< Question,QuestionDto> questionMapper = new QuestionMapper(questionRepository);



    private List<Answer> answerList;

    @Before
    public void init() {
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        Answer answer3 = new Answer();

        answerList = new ArrayList<>();
        answerList.add(answer1);
        answerList.add(answer2);
        answerList.add(answer3);
    }

    @Test
    public void shouldReturnEntity() throws ConvertDtoToEntityException {
        //given
        BDDMockito.when(questionRepository.findById(Mockito.any())).thenReturn(Mockito.any());
        QuestionDto dummyQuestionDto = new QuestionDto(null,"What is my name ?",5,answerList);

        //when
        Question result = questionMapper.convertDtoToEntity(dummyQuestionDto);

        //than
        Assert.assertEquals(result.getIdQuestion(),dummyQuestionDto.getId());
        Assert.assertEquals(result.getAnswerList(),dummyQuestionDto.getAnswers());
        Assert.assertEquals(result.getContent(),dummyQuestionDto.getQuestion());
        Assert.assertEquals(result.getPoint(),dummyQuestionDto.getPoint());
    }



}