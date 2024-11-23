package com.burglak.linker.mapper;

public interface Mapper <T, U> {

    U mapTo(T t);

    T mapFrom(U u);

}
