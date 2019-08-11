package sda.quiz.service.mapper;

import org.springframework.stereotype.Component;

@Component
public interface IMapper<E,D> {

    E convertDtoToEntity(D d);

    D convertEntityToDto(E e);
}
