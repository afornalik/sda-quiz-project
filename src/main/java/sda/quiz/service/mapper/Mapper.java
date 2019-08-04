package sda.quiz.service.mapper;

import org.springframework.stereotype.Component;
import sda.quiz.service.mapper.exception.ConvertDtoToEntityException;
import sda.quiz.service.mapper.exception.ConvertEntityToDtoException;

@Component
public interface Mapper<E,D> {

    E convertDtoToEntity(D d) throws ConvertDtoToEntityException;

    D convertEntityToDto(E e) throws ConvertEntityToDtoException;
}
