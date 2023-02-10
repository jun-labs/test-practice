package study.project.codeexample.exam.converter;

public interface DomainModelConverTer<T, E> {

    E convertToDomainModel(T t);

    T convertToEntity(E e);
}
