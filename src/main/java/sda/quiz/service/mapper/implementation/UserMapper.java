package sda.quiz.service.mapper.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sda.quiz.dto.user.UserDto;
import sda.quiz.entity.user.User;
import sda.quiz.service.mapper.IMapper;

import java.util.stream.Collectors;

@Component
public class UserMapper implements IMapper<User, UserDto> {

    private final ModelMapper modelMapper;
    private final QuizTakenMapper quizTakenMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper, QuizTakenMapper quizTakenMapper) {
        this.modelMapper = modelMapper;
        this.quizTakenMapper = quizTakenMapper;
    }

    @Override
    public User convertDtoToEntity(UserDto userDto) {

        User user = new User();
        modelMapper.map(userDto,user);

        user.setQuizTakenList(
                userDto.getQuizTakenDtoList()
                        .stream()
                        .map(quizTakenMapper::convertDtoToEntity)
                        .collect(Collectors.toList()));

        return user;
    }

    @Override
    public UserDto convertEntityToDto(User user) {

        UserDto userDto = new UserDto();
        modelMapper.map(user,userDto);

        userDto.setQuizTakenDtoList(
                user.getQuizTakenList()
                        .stream()
                        .map(quizTakenMapper::convertEntityToDto)
                        .collect(Collectors.toList()));

        return userDto;
    }
}
