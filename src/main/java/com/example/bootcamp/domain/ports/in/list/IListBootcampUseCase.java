package com.example.bootcamp.domain.ports.in.list;

import com.example.bootcamp.domain.model.BootcampModel;
import com.example.bootcamp.domain.model.list.BootcampListModel;
import reactor.core.publisher.Flux;

public interface IListBootcampUseCase {

    Flux<BootcampListModel> listBootcamps(
            int page,
            int size,
            String sorty,
            String direction
    );
}
