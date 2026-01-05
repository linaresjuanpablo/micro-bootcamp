package com.example.bootcamp.domain.ports.out.list;

import reactor.core.publisher.Flux;

public interface IBootcampTechnologyRepositoryPort {

    Flux<Long> findTechnologyIdsByBootcampId(Long bootcampId);


}
