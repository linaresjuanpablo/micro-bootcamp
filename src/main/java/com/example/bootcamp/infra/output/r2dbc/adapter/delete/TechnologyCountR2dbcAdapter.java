package com.example.bootcamp.infra.output.r2dbc.adapter.delete;

import com.example.bootcamp.domain.ports.out.delete.ITechnologyCountRepositoryPort;
import com.example.bootcamp.infra.output.r2dbc.repository.delete.IR2TechnologyCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor

public class TechnologyCountR2dbcAdapter implements ITechnologyCountRepositoryPort {

    private final IR2TechnologyCountRepository repository;

    @Override
    public Mono<Long> countBootcampsByTechnology(Long technologyId) {
        return repository.countBootcampsByTechnology(technologyId);
    }
}
