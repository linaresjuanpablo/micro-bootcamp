package com.example.bootcamp.infra.output.r2dbc.adapter.list;

import com.example.bootcamp.domain.ports.out.list.IBootcampTechnologyRepositoryPort;
import com.example.bootcamp.infra.output.r2dbc.repository.list.IR2TechnologyListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class BootcampTechnologyRepositoryAdapter implements IBootcampTechnologyRepositoryPort {

    private final IR2TechnologyListRepository ir2TechnologyListRepository;


    @Override
    public Flux<Long> findTechnologyIdsByBootcampId(Long bootcampId) {
        return ir2TechnologyListRepository.findTechnologyIdsByBootcampId(bootcampId);

    }


}
