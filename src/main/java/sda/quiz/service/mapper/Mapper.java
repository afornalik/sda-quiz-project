package sda.quiz.service.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<E,D> {

    E convertDtoToEntity(D d);

    D convertEntityToDto(E e);
}
