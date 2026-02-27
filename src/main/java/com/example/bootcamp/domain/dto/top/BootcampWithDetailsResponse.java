package com.example.bootcamp.domain.dto.top;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder

public record BootcampWithDetailsResponse(

        Long id,
        String name,
        String description,
        LocalDate launchdate,
        Integer duration,
        List<CapabilityResponse> capabilities

) {
}
