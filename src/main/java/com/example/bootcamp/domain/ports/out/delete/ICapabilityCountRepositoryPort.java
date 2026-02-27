package com.example.bootcamp.domain.ports.out.delete;

import reactor.core.publisher.Mono;

public interface ICapabilityCountRepositoryPort {

    Mono<Long> countCapabilitiesByBootcamp(Long capabilityId);
}
