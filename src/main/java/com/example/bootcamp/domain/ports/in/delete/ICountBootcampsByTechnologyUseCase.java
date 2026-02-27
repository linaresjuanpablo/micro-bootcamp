package com.example.bootcamp.domain.ports.in.delete;

import reactor.core.publisher.Mono;

public interface ICountBootcampsByTechnologyUseCase {

    Mono<Long> execute(Long technologyId);
}
