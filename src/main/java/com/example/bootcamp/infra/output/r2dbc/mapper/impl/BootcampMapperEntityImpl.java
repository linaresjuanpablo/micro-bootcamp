/*package com.example.bootcamp.infra.output.r2dbc.mapper.impl;

import com.example.bootcamp.domain.model.BootcampModel;
import com.example.bootcamp.infra.output.r2dbc.entity.R2BootcampEntity;
import com.example.bootcamp.infra.output.r2dbc.mapper.IBootcampMapperEntity;

import java.util.List;

public class BootcampMapperEntityImpl implements IBootcampMapperEntity {

    @Override
    public R2BootcampEntity r2Entity(BootcampModel bootcampModel) {
        R2BootcampEntity entity = new R2BootcampEntity();
        entity.setId(bootcampModel.getId());
        entity.setName(bootcampModel.getName());
        entity.setDescription(bootcampModel.getDescription());
        entity.setLaunchdate(bootcampModel.getLaunchdate());
        entity.setDuration(bootcampModel.getDuration());
        return entity;
    }

    /*@Override
    public BootcampModel bootModel(R2BootcampEntity r2BootcampEntity, List<Long> bootIds) {
        BootcampModel bootcampModel = new BootcampModel();
        bootcampModel.setId(r2BootcampEntity.getId());
        bootcampModel.setName(r2BootcampEntity.getName());
        bootcampModel.setDescription(r2BootcampEntity.getDescription());
        bootcampModel.setLaunchdate(r2BootcampEntity.getLaunchdate());
        bootcampModel.setDuration(r2BootcampEntity.getDuration());
        return bootcampModel;
    }*/

  /*  @Override
    public BootcampModel bootModel(R2BootcampEntity entity) {
        return null;
    }
}*/
