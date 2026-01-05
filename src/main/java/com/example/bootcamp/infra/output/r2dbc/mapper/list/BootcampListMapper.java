package com.example.bootcamp.infra.output.r2dbc.mapper.list;

import com.example.bootcamp.domain.model.BootcampModel;
import com.example.bootcamp.domain.model.CapabilitySummary;
import com.example.bootcamp.domain.model.list.BootcampListModel;
import com.example.bootcamp.domain.model.list.CapabilityModel;
import com.example.bootcamp.domain.model.list.TechnologyModel;
import com.example.bootcamp.infra.output.r2dbc.entity.list.R2BootcampListCapabilityEntity;
import com.example.bootcamp.infra.output.r2dbc.entity.list.R2BootcampListEntity;
import com.example.bootcamp.infra.output.r2dbc.entity.list.R2CapabilityEntity;
import com.example.bootcamp.infra.output.r2dbc.entity.list.R2TechnologyEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootcampListMapper {

    public BootcampListModel toBootcampModel(
            R2BootcampListEntity entity

    ){
        return BootcampListModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .launchDate(entity.getLaunchDate())
                .duration(entity.getDuration())
                .build();
    }
    public CapabilityModel toCapabilityModel(
            R2CapabilityEntity entity,
            List<TechnologyModel> technologies
    ){
        return CapabilityModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .technologies(technologies)
                .build();
    }
    public TechnologyModel technologyModel(R2TechnologyEntity entity){
        return TechnologyModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
