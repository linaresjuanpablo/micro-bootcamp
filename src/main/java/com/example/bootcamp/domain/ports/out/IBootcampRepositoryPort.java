package com.example.bootcamp.domain.ports.out;

import com.example.bootcamp.domain.model.BootcampModel;
import com.example.bootcamp.domain.model.BootcampSummary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IBootcampRepositoryPort {

    Mono<BootcampModel> save (BootcampModel bootcampModel);

}
