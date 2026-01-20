package com.example.bootcamp.domain.ports.in.delete;

import com.example.bootcamp.domain.model.delete.BootcampDeleteModel;
import reactor.core.publisher.Mono;

public interface IDeleteBootcampUseCase {
    Mono<BootcampDeleteModel> deleteBootcamp(Long bootcampId);
}
