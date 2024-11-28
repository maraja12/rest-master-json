package com.fon.rest_master.converter;

public interface DtoEntityConverter <T, E>{

    T toDto(E e);
    E toEntity(T t);
}
