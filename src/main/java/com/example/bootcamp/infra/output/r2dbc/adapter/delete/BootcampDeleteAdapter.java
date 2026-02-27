package com.example.bootcamp.infra.output.r2dbc.adapter.delete;

import com.example.bootcamp.domain.model.CapabilitySummary;
import com.example.bootcamp.domain.model.delete.CapabilityDeleteSumary;
import com.example.bootcamp.domain.model.delete.TechnologyDeleteSummary;
import com.example.bootcamp.domain.ports.out.delete.IDeleteBootcampRepositoryPort;
import com.example.bootcamp.infra.output.r2dbc.repository.delete.IR2BootcampDeleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Repository

public class BootcampDeleteAdapter implements IDeleteBootcampRepositoryPort {

    private final IR2BootcampDeleteRepository ir2BootcampDeleteRepository;


    @Override
    public Mono<Boolean> existsById(Long bootcampId) {
        return ir2BootcampDeleteRepository.existsById(bootcampId);
    }

    @Override
    public Flux<CapabilityDeleteSumary> findCapabilitiesByBootcampId(Long bootcampId) {
        return ir2BootcampDeleteRepository.findCapabilityIdsByBootcampId(bootcampId)
                .map(capabilityId -> CapabilityDeleteSumary.builder()
                        .id(capabilityId)
                        .technologies(List.of())
                        .build());
    }
    @Override
    public Mono<Void> deleteBootcampCapabilityRelations(Long bootcampId) {
        return ir2BootcampDeleteRepository.deleteRelationsByBootcampId(bootcampId);

    }
    @Override
    public Mono<Void> deleteBootcamp(Long bootcampId) {
        return ir2BootcampDeleteRepository.deleteById(bootcampId);
    }

    @Override
    public Mono<Void> deleteBootcampTechnologyRelations(Long bootcampId) {
        return ir2BootcampDeleteRepository.deleteTechnologyRelationsByBootcampId(bootcampId);
    }

    @Override
    public Mono<Long> countBootcampsByCapability(Long capabilityId) {
        return ir2BootcampDeleteRepository.countBootcampsByCapability(capabilityId);
    }

    /*@Override
    public Mono<Void> deleteCapability(Long capabilityId) {
        return ir2BootcampDeleteRepository.deleteCapabilityById(capabilityId);
    }*/
}



