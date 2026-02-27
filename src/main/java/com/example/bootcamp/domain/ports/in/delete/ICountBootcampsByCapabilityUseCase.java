package com.example.bootcamp.domain.ports.in.delete;

import reactor.core.publisher.Mono;

public interface ICountBootcampsByCapabilityUseCase {

    Mono<Long> exceute(Long bootcampId);
}
