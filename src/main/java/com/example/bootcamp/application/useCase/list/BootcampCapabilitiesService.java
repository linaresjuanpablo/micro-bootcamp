package com.example.bootcamp.application.useCase.list;

import com.example.bootcamp.domain.dto.BootcampWithCapabilitiesResponse;
import com.example.bootcamp.domain.ports.in.list.BootcampCapabilitiesQueryPort;
import com.example.bootcamp.domain.ports.out.top.CapabilityWebClientPort;
import com.example.bootcamp.infra.output.r2dbc.entity.BootcampEntity;
import com.example.bootcamp.infra.output.r2dbc.repository.BootcampRepository;
import com.example.bootcamp.infra.output.r2dbc.repository.CapabilityRepository;
import com.example.bootcamp.infra.output.r2dbc.repository.top.BootcampCapabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BootcampCapabilitiesService implements BootcampCapabilitiesQueryPort {

    private final BootcampRepository bootcampRepository;
    private final BootcampCapabilityRepository bootcampCapabilityRepository;
    private final CapabilityWebClientPort capabilityWebClientPort;


    @Override
    public Mono<BootcampWithCapabilitiesResponse> getBootcampWithCapabilities(Long bootcampId) {
        return Mono.zip(
                bootcampRepository.findById(bootcampId),
                bootcampCapabilityRepository.findCapabilityIdsByBootcampId(bootcampId).collectList()
        ).flatMap(tuple -> {
            BootcampEntity bootcamp = tuple.getT1();
            List<Long> capabilityIds = tuple.getT2();
            System.out.println("Capability IDs: " + capabilityIds);

            return capabilityWebClientPort.getCapabilitiesByIds(capabilityIds)
                    .collectList()
                    .map(capabilities -> BootcampWithCapabilitiesResponse.builder()
                            .bootcampId(bootcamp.getId())
                            .name(bootcamp.getName())
                            .description(bootcamp.getDescription())
                            .launchdate(bootcamp.getLaunchdate())
                            .duration(bootcamp.getDuration())
                            .capabilities(capabilities)
                            .build()
                    );
        });

    }
}
