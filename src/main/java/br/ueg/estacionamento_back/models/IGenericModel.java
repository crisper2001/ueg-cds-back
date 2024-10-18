package br.ueg.estacionamento_back.models;

public interface IGenericModel<TYPE_PK> {
    TYPE_PK getId();
    void setId(TYPE_PK id);
}
