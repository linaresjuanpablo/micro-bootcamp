package com.example.bootcamp.domain.ports.out.delete;

import reactor.core.publisher.Mono;

public interface IDeleteTechnologyClientPort {

    Mono<Long> countBootcampsByTechnology(Long techologyId);
    Mono<Void> deleteTechnology(Long technologyId);
}
