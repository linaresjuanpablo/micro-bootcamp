package com.example.bootcamp.application.useCase.delete;

import com.example.bootcamp.domain.ports.in.delete.ICountBootcampsByCapabilityUseCase;
import com.example.bootcamp.domain.ports.out.delete.ICapabilityCountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class CountBootcampsByCapabilityUseCase implements ICountBootcampsByCapabilityUseCase {

    private final ICapabilityCountRepositoryPort capabilityCountRepositoryPort;

    @Override
    public Mono<Long> exceute(Long capabilityId) {
        return capabilityCountRepositoryPort.countBootcampsByCapability(capabilityId);
    }
}
