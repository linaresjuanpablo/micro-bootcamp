package com.example.bootcamp.infra.output.r2dbc.mapper.delete;

import com.example.bootcamp.domain.model.delete.CapabilityCountBootcampModel;
import com.example.bootcamp.infra.input.dto.delete.CapabilityBootcampCountResponse;
import com.example.bootcamp.infra.output.r2dbc.entity.delete.R2CapabilityCountBootcampEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CapabilityCountMapper {

    CapabilityBootcampCountResponse toBootcampCountResponse(Long capabilityId, Long bootcampCount);

    CapabilityCountBootcampModel toDomainModel(R2CapabilityCountBootcampEntity r2CapabilityCountBootcampEntity);

    R2CapabilityCountBootcampEntity toEntity(CapabilityCountBootcampModel capabilityCountBootcampModel);
}
