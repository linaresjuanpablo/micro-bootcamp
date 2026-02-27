package com.example.bootcamp.domain.ports.out.list;

import com.example.bootcamp.domain.model.BootcampModel;
import com.example.bootcamp.domain.model.list.BootcampListModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.naming.ldap.SortKey;

public interface IBootcampRepositoryPort {

    Flux<BootcampListModel> findAll(
            int page,
            int size,
            SortKey sortBy,
            SortDirection direction
    );
    enum SortKey{NAME, CAPACITY_COUNT}
    enum SortDirection {ASC, DESC}
    Mono<Long> count();


}
