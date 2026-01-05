package com.example.bootcamp.application.useCase.list;

import com.example.bootcamp.domain.model.CapabilitySummary;
import com.example.bootcamp.domain.model.TechnologySummary;
import com.example.bootcamp.domain.model.list.BootcampListModel;
import com.example.bootcamp.domain.ports.in.list.IListBootcampUseCase;
import com.example.bootcamp.domain.ports.out.ICapabilityClientPort;
import com.example.bootcamp.domain.ports.out.ITechnologyClientPort;
import com.example.bootcamp.domain.ports.out.list.IBootcampCapabilityRepositoryPort;
import com.example.bootcamp.domain.ports.out.list.IBootcampRepositoryPort;
import com.example.bootcamp.domain.ports.out.list.IBootcampTechnologyRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ListBootcampUseCase  implements IListBootcampUseCase {

    private final IBootcampRepositoryPort iBootcampRepositoryPort;
    private final ICapabilityClientPort iCapabilityClientPort;
    private final IBootcampCapabilityRepositoryPort iBootcampCapabilityRepositoryPort;
    private final IBootcampTechnologyRepositoryPort iBootcampTechnologyRepositoryPort;
    private final ITechnologyClientPort iTechnologyClientPort;

    @Override
    public Flux<BootcampListModel> listBootcamps(
            int page,
            int size,
            String sortBy,
            String direction) {
        //validacion basica de parametros
        IBootcampRepositoryPort.SortKey sortKey =
                IBootcampRepositoryPort.SortKey.valueOf(sortBy.toUpperCase());
        IBootcampRepositoryPort.SortDirection sortDirection =
                IBootcampRepositoryPort.SortDirection.valueOf(direction.toUpperCase());
        return iBootcampRepositoryPort.findAll(page, size, sortKey, sortDirection)
                .flatMapSequential(this::enrichBootcampWithCapabilitiesAndTechnologies
                );
    }
    private Mono<BootcampListModel>enrichBootcampWithCapabilitiesAndTechnologies(BootcampListModel bootcamp){
        Long bootcampId = bootcamp.getId();

        Mono<List<CapabilitySummary>> capabilitiesMono = iBootcampCapabilityRepositoryPort.findCapabilityIdsByBootcampId(bootcampId)
                .collectList()
                .flatMap(ids-> ids.isEmpty()
                        ? Mono.just(List.of())
                        : iCapabilityClientPort.findByIds(ids).collectList()
                );
        Mono<List<TechnologySummary>> technologiesMono = iBootcampTechnologyRepositoryPort.findTechnologyIdsByBootcampId(bootcampId)
                .collectList()
                .flatMap(ids-> ids.isEmpty()
                ? Mono.just(List.of())
                        : iTechnologyClientPort.findByIds(ids).collectList()
                );
        return Mono.zip(capabilitiesMono, technologiesMono)
                .map(tuple -> bootcamp.toBuilder()
                        .capabilities(tuple.getT1())
                                .technologies(tuple.getT2())
                                .build()
                        );
    }
    private String validateSortkey(String sortBy){
        return switch (sortBy.toLowerCase()){
            case "name"-> "name";
            case "capacitycount" -> "capacityCount";
            default -> throw new IllegalArgumentException("Invalid sortBy: " + sortBy);
        };
    }
    private String validateDirection(String direction){
        return switch (direction.toLowerCase()){
            case "asc" -> "asc";
            case "desc" -> "desc";
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        };
    }

}
