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

import java.util.List;

@Component
@RequiredArgsConstructor

public class BootcampAdapter implements IBootcampRepositoryPort {

    private final IR2BootcampRepository ir2BootcampRepository;
    private final IBootcampMapperEntity iBootcampMapperEntity;

    @Override
    public Mono<BootcampModel> save(BootcampModel bootcampModel) {
        R2BootcampEntity entity = iBootcampMapperEntity.r2Entity(bootcampModel);

        return ir2BootcampRepository.save(entity)
                .flatMap(savedEntity->{
                            Long bootcampId = savedEntity.getId();
                            List<Mono<Void>> inserts = bootcampModel.getCapabilityIds().stream()
                                    .map(capId -> ir2BootcampRepository.saveBootcampCapability(bootcampId, capId))
                                    .toList();

                            return Flux.merge(inserts)
                                    .then(Mono.just(bootcampModel.toBuilder()
                                            .id(bootcampId)
                                            .build()
                                    ));
                        });




    }

}
