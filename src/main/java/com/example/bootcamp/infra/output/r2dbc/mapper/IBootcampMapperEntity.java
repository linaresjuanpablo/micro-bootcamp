package com.example.bootcamp.infra.output.r2dbc.mapper;

import com.example.bootcamp.domain.model.BootcampModel;
import com.example.bootcamp.infra.output.r2dbc.entity.R2BootcampEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface IBootcampMapperEntity {

    R2BootcampEntity r2Entity(BootcampModel bootcampModel);

    @Mapping(target = "capabilityIds", ignore = true)
    BootcampModel bootModel(R2BootcampEntity entity);


}
