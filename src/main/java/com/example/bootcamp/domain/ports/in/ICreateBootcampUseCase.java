package com.example.bootcamp.domain.ports.in;

import com.example.bootcamp.domain.model.BootcampModel;
import reactor.core.publisher.Mono;

public interface ICreateBootcampUseCase {

    Mono<BootcampModel> createBoot (BootcampModel bootcampModel);
}
