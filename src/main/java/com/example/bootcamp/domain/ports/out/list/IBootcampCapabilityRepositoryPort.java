package com.example.bootcamp.domain.ports.out.list;

import reactor.core.publisher.Flux;

public interface IBootcampCapabilityRepositoryPort {

    Flux<Long> findCapabilityIdsByBootcampId (Long bootcampId);
}
