package com.example.bootcamp.infra.output.r2dbc.adapter.delete;

import com.example.bootcamp.domain.ports.out.delete.ICapabilityCountRepositoryPort;
import com.example.bootcamp.infra.output.r2dbc.mapper.delete.CapabilityCountMapper;
import com.example.bootcamp.infra.output.r2dbc.repository.delete.IR2CapabilityCountBootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor

public class CapabilityCountBootcampAdapter implements ICapabilityCountRepositoryPort {

    private final IR2CapabilityCountBootcampRepository ir2CapabilityCountBootcampRepository;
    private final CapabilityCountMapper capabilityCountMapper;

    @Override
    public Mono<Long> countBootcampsByCapability(Long capabilityId) {
        return ir2CapabilityCountBootcampRepository.countByCapabilityId(capabilityId);
    }
}
