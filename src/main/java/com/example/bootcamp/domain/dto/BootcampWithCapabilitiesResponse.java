package com.example.bootcamp.domain.dto;

import com.example.bootcamp.domain.dto.top.CapabilityResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder

public record BootcampWithCapabilitiesResponse(
        Long bootcampId,
        String name,
        String description,
        LocalDate launchdate,
        Integer duration,
        List<CapabilityResponse> capabilities
) {
}
