package com.example.bootcamp.infra.input.controller.dto.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor

public class BootcampDeleteResponseDto {

    private Long bootcapId;
    private List<Long> deletedCapabilities;
    private List<Long> deletedTechnologies;
}
