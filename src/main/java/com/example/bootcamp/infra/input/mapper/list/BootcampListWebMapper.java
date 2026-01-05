package com.example.bootcamp.infra.input.mapper.list;


import com.example.bootcamp.domain.model.CapabilitySummary;
import com.example.bootcamp.domain.model.TechnologySummary;
import com.example.bootcamp.domain.model.list.BootcampListModel;
import com.example.bootcamp.infra.input.controller.dto.list.BootcampListResponse;
import com.example.bootcamp.infra.input.controller.dto.list.CapabilityResponse;
import com.example.bootcamp.infra.input.controller.dto.list.TechnologyResponse;
import org.springframework.stereotype.Component;

@Component
public class BootcampListWebMapper {
    public BootcampListResponse toResponse(BootcampListModel bootcampListModel){
        return BootcampListResponse.builder()
                .id(bootcampListModel.getId())
                .name(bootcampListModel.getName())
                .description(bootcampListModel.getDescription())
                .lauchDate(bootcampListModel.getLaunchDate())
                .duration(bootcampListModel.getDuration())
                .capabilities(bootcampListModel.getCapabilities().stream()
                        .map(this::toCapabilityResponse)
                        .toList()
                )
                .build();
    }
    private CapabilityResponse toCapabilityResponse(CapabilitySummary capability) {
        return CapabilityResponse.builder()
                .id(capability.getId())
                .name(capability.getName())
                .technologies(
                        capability.getTechnologies().stream()
                                .map(t-> TechnologyResponse.builder()
                                        .id(t.getId())
                                                .name(t.getName())
                                                .build()
                                        )
                                .toList()
                )
                .build();
    }
    private TechnologyResponse toTechnologyResponse(TechnologySummary tech) {
        return TechnologyResponse.builder()
                .id(tech.getId())
                .name(tech.getName())
                .build();
    }
}
