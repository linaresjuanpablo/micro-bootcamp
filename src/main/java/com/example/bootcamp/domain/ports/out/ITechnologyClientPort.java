package com.example.bootcamp.domain.ports.out;

import com.example.bootcamp.domain.model.TechnologySummary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyClientPort {

    Flux<TechnologySummary> findByBootcampId(Long bootcampId);

    Mono<List<TechnologySummary>> findByBootcampTechnologiesByIds(List<Long> technologyIds);
    Flux<TechnologySummary> findByIds(List<Long> technologiesIds);

}
