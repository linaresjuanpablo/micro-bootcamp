package com.example.bootcamp.infra.output.r2dbc.adapter;

import com.example.bootcamp.domain.model.BootcampModel;
import com.example.bootcamp.domain.ports.out.IBootcampRepositoryPort;
import com.example.bootcamp.infra.output.r2dbc.entity.R2BootcampEntity;
import com.example.bootcamp.infra.output.r2dbc.mapper.IBootcampMapperEntity;
import com.example.bootcamp.infra.output.r2dbc.repository.IR2BootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor

public class BootcampAdapter implements IBootcampRepositoryPort {

    private final IR2BootcampRepository ir2BootcampRepository;
    private final IBootcampMapperEntity iBootcampMapperEntity;

    @Override
    public Mono<BootcampModel> save(BootcampModel bootcampModel) {
        R2BootcampEntity entity = iBootcampMapperEntity.r2Entity(bootcampModel);

        return ir2BootcampRepository.save(entity)
                .map(savedEntity->
                        bootcampModel.toBuilder()
                                .id(savedEntity.getId())
                                .build());

    }
}
