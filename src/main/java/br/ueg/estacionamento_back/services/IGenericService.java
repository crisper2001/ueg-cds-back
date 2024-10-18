package br.ueg.estacionamento_back.services;

import java.util.List;

public interface IGenericService<T> {
    T create(T entity);
    List<T> getAll();
    T getById(long id);
    T updateById(T entity);
    T deleteById(long id);
    void validateBusinessLogic(T entity);
    T validateIdExists(long id);
    T internalGet(long id);
    void updateDataDB(T entity, T updatedEntity);
}
