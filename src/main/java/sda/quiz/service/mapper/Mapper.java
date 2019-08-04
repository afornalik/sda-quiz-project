package sda.quiz.service.mapper;

import sda.quiz.service.mapper.exception.ConvertDtoToEntityException;
import sda.quiz.service.mapper.exception.ConvertEntityToDtoException;

public interface Mapper<E,D> {

    E convertDtoToEntity(D d) throws ConvertDtoToEntityException;

    D convertEntityToDto(E e) throws ConvertEntityToDtoException;
}
