package com.example.bootcamp.domain.dto.top;

import lombok.Builder;

@Builder

public record TechnologyResponse(
        Long id,
        String name

) {
}
