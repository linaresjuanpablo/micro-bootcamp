package com.example.bootcamp.infra.output.r2dbc.adapter.list;

import com.example.bootcamp.domain.ports.out.list.IBootcampCapabilityRepositoryPort;
import com.example.bootcamp.infra.output.r2dbc.repository.list.IR2BootcampListRepository;
import com.example.bootcamp.infra.output.r2dbc.repository.list.IR2CapabilityListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class BootcampCapabilityRepositoryAdapter implements IBootcampCapabilityRepositoryPort {

    private final IR2CapabilityListRepository ir2CapabilityListRepository;

    @Override
    public Flux<Long> findCapabilityIdsByBootcampId(Long bootcampId) {

        return ir2CapabilityListRepository.findCapabilityIdsByBootcampId(bootcampId);

    }
}
