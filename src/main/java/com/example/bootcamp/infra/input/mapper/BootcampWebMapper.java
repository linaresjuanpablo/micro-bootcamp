package com.example.bootcamp.infra.input.mapper;

import com.example.bootcamp.domain.model.BootcampModel;
import com.example.bootcamp.infra.input.controller.dto.BootcampResponse;
import com.example.bootcamp.infra.input.controller.dto.CreateBootcampRequest;
import org.springframework.stereotype.Component;

@Component

public class BootcampWebMapper {
    public BootcampModel toDomain(CreateBootcampRequest request){
        return BootcampModel.builder()
                .name(request.getName())
                .description(request.getDescription())
                .launchdate(request.getLaunchdate())
                .duration(request.getDuration())
                .capabilityIds(request.getCapabilityIds())
                .build();
    }

    public BootcampResponse toResponse(BootcampModel bootcampModel){
        return BootcampResponse.builder()
                .id(bootcampModel.getId())
                .name(bootcampModel.getName())
                .description(bootcampModel.getDescription())
                .launchdate(bootcampModel.getLaunchdate())
                .duration(bootcampModel.getDuration())
                .capabilityIds(bootcampModel.getCapabilityIds())
                .build();
    }
}
