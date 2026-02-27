package com.example.bootcamp.infra.input.mapper.delete;

import com.example.bootcamp.domain.model.delete.BootcampDeleteModel;
import com.example.bootcamp.infra.input.controller.dto.delete.BootcampDeleteResponseDto;

public class BootcampDeleteWebMapper {

    public static BootcampDeleteResponseDto toDto(BootcampDeleteModel model){
        return BootcampDeleteResponseDto.builder()
                .bootcapId(model.getBootcampId())
                .deletedCapabilities(model.getDeletedCapabilities())
                .deletedTechnologies(model.getDeletedTechnologies())
                .build();

    }
}
