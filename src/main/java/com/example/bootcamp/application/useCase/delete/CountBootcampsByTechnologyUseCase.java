package com.example.bootcamp.application.useCase.delete;

import com.example.bootcamp.domain.ports.in.delete.ICountBootcampsByTechnologyUseCase;
import com.example.bootcamp.domain.ports.out.delete.ITechnologyCountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class CountBootcampsByTechnologyUseCase implements ICountBootcampsByTechnologyUseCase {

    private final ITechnologyCountRepositoryPort technologyCountRepositoryPort;

    @Override
    public Mono<Long> execute(Long technologyId) {
        return technologyCountRepositoryPort.countBootcampsByTechnology(technologyId);
    }
}
