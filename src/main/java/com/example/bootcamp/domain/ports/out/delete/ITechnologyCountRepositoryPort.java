package com.example.bootcamp.domain.ports.out.delete;

import reactor.core.publisher.Mono;

public interface ITechnologyCountRepositoryPort {

    Mono<Long> countBootcampsByTechnology(Long technologyId);
}
