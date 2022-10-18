package com.smart.consumption;

public interface ModelMapper<MODEL, ENTITY> {
    MODEL mapEntityToModel(ENTITY entity);
    ENTITY mapModelToEntity(MODEL model);

}
