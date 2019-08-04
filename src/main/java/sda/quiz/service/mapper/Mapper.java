package sda.quiz.service.mapper;

public interface Mapper<E,D> {

    E convertDtoToEntity(D d);

    D convertEntityToDto(E e);
}
