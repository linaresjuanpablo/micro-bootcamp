package com.example.bootcamp.infra.output.r2dbc.adapter.list;


import com.example.bootcamp.domain.model.list.BootcampListModel;
import com.example.bootcamp.domain.ports.out.list.IBootcampRepositoryPort;
import com.example.bootcamp.infra.output.r2dbc.mapper.list.BootcampListMapper;
import com.example.bootcamp.infra.output.r2dbc.repository.list.IR2BootcampListRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor

public class BootcampListAdapter implements IBootcampRepositoryPort {

    private final IR2BootcampListRepository ir2BootcampListRepository;
    private final BootcampListMapper bootcampListMapper;

    @Override
    public Flux<BootcampListModel> findAll(
            int page,
            int size,
            SortKey sortBy,
            SortDirection direction) {
        long offset = (long) page * size;
        return ir2BootcampListRepository.findAllPage(size, offset, sortBy.name(), direction.name())
                .map(bootcampListMapper::toBootcampModel);
    }
}
