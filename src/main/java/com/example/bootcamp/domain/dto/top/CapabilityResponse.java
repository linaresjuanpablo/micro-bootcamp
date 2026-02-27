package com.example.bootcamp.domain.dto.top;

import lombok.Builder;

import java.util.List;

@Builder

public record CapabilityResponse(
        Long id,
        String name,
        List<TechnologyResponse> technologies

) {
}
