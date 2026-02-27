package com.example.bootcamp.application.useCase;

import com.example.bootcamp.infra.output.r2dbc.entity.BootcampEntity;
import com.example.bootcamp.infra.output.r2dbc.repository.BootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class BootcampService {

    private final BootcampRepository repository;

    public Mono<BootcampEntity> findById(Long id){
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Bootcamp no encontrado")));
    }


}
