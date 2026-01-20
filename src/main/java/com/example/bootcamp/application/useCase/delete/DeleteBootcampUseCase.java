package com.example.bootcamp.application.useCase.delete;

import com.example.bootcamp.domain.exception.ValidationException;
import com.example.bootcamp.domain.model.CapabilitySummary;
import com.example.bootcamp.domain.model.TechnologySummary;
import com.example.bootcamp.domain.model.delete.BootcampDeleteModel;
import com.example.bootcamp.domain.model.delete.CapabilityDeleteSumary;
import com.example.bootcamp.domain.model.delete.TechnologyDeleteSummary;
import com.example.bootcamp.domain.ports.in.delete.IDeleteBootcampUseCase;
import com.example.bootcamp.domain.ports.out.delete.IDeleteBootcampRepositoryPort;
import com.example.bootcamp.domain.ports.out.delete.IDeleteCapabilityClientPort;
import com.example.bootcamp.domain.ports.out.delete.IDeleteTechnologyClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service

public class DeleteBootcampUseCase implements IDeleteBootcampUseCase {

    private static final String ERR_BOOTCAMP_NOT_FOUND = "ERR_BOOTCAMP_NOT_FOUND";

    private final IDeleteBootcampRepositoryPort iDeleteBootcampRepositoryPort;
    //private  final IDeleteCapabilityClientPort capabilityClientPort;
    private final IDeleteTechnologyClientPort iDeleteTechnologyClientPort;
    private final TransactionalOperator transactionalOperator;

    @Override
    public Mono<BootcampDeleteModel> deleteBootcamp(Long bootcampId) {

        List<Long> deletedCapabilities = new ArrayList<>();
        List<Long> deletedTechnologies = new ArrayList<>();
        return iDeleteBootcampRepositoryPort.existsById(bootcampId)
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new ValidationException(ERR_BOOTCAMP_NOT_FOUND, "El bootcamp no existe")))
                .thenMany(iDeleteBootcampRepositoryPort.findCapabilitiesByBootcampId(bootcampId))
                .flatMap(capability -> processCapability(capability, deletedCapabilities, deletedTechnologies))
                .then(iDeleteBootcampRepositoryPort.deleteBootcampCapabilityRelations(bootcampId))
                .then(iDeleteBootcampRepositoryPort.deleteBootcampCapabilityRelations(bootcampId))
                .then(iDeleteBootcampRepositoryPort.deleteBootcamp(bootcampId))
                .then(Mono.just(new BootcampDeleteModel(bootcampId, deletedCapabilities, deletedTechnologies)))
                .as(transactionalOperator::transactional);

    }
    private Mono<Void> processCapability(
            CapabilityDeleteSumary capability,
            List<Long> deletedCapabilities,
            List<Long> deleteTechnologies

    ) {
        return iDeleteBootcampRepositoryPort.countBootcampsByCapability(capability.getId())
                .flatMap(count -> {
                    if (count == 1) {
                        deletedCapabilities.add(capability.getId());
                        return Flux.fromIterable(capability.getTechnologies())
                                        .concatMap(tech -> processTechnology(tech, deleteTechnologies))
                                                .then();
                    }
                    return Mono.empty();
                });
    }

    /*private Mono<Void> deleteCapabilityAndTechnologies(
            CapabilityDeleteSumary capability,
            List<Long> deletedCapabilities,
            List<Long> deletedTechnologies
    ) {

        return Flux.fromIterable(capability.getTechnologies())
                .concatMap(tech -> processTechnology(tech, deletedTechnologies))
                .then(capabilityClientPort.deleteCapability(capability.getId())
                        .doOnSuccess(v -> deletedCapabilities.add(capability.getId())));
    }*/

    private Mono<Void> processTechnology(
            TechnologyDeleteSummary technology,
            List<Long> deletedTechnologies
            ) {
        return iDeleteTechnologyClientPort.countBootcampsByTechnology(technology.getId())
                .flatMap(count -> {
                    if (count == 1) {
                        return iDeleteTechnologyClientPort.deleteTechnology(technology.getId());
                    }
                    return Mono.empty();
                });
    }
}




