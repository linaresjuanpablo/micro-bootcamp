package com.example.bootcamp.application.useCase.list;

import com.example.bootcamp.domain.dto.list.BootcampPageResponse;
import com.example.bootcamp.domain.model.CapabilitySummary;
import com.example.bootcamp.domain.model.TechnologySummary;
import com.example.bootcamp.domain.model.list.BootcampListModel;
import com.example.bootcamp.domain.model.list.PaginationMetadata;
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



    @Override
    public Mono<BootcampPageResponse> listBootcampsPage(
            int page,
            int size,
            String sortBy,
            String direction) {
        return Mono.defer(() -> {

        //validacion basica de parametros
        String validatedSortKey = validateSortkey(sortBy);
        String validatedDirection = validateDirection(direction);

        IBootcampRepositoryPort.SortKey sortKey =
                IBootcampRepositoryPort.SortKey.valueOf(validatedSortKey.toUpperCase());
        IBootcampRepositoryPort.SortDirection sortDirection =
                IBootcampRepositoryPort.SortDirection.valueOf(validatedDirection.toUpperCase());

        Mono<Long> totalElementsMono = iBootcampRepositoryPort.count();

        Flux<BootcampListModel> bootcampFlux = iBootcampRepositoryPort.findAll(page, size, sortKey, sortDirection)
                .flatMapSequential(this::enrichBootcampWithCapabilitiesAndTechnologies);

        return Mono.zip(bootcampFlux.collectList(), totalElementsMono)
                .map(tuple -> {
                    List<BootcampListModel> content = tuple.getT1();
                    Long totalElements = tuple.getT2();
                    int totalPages = (int) Math.ceil((double) totalElements / size);

                    PaginationMetadata metadata = PaginationMetadata.builder()
                            .page(page)
                            .size(size)
                            .totalElements(totalElements)
                            .totalPages(totalPages)
                            .sortBy(sortBy)
                            .direction(direction)
                            .build();
                    return BootcampPageResponse.builder()
                            .content(content)
                            .metadata(metadata)
                            .build();
                });
        });
    }



        /*return iBootcampRepositoryPort.findAll(page, size, sortKey, sortDirection)
                .flatMapSequential(this::enrichBootcampWithCapabilitiesAndTechnologies
                );*/
    //}
    private Mono<BootcampListModel>enrichBootcampWithCapabilitiesAndTechnologies(BootcampListModel bootcamp){
        Long bootcampId = bootcamp.getId();

        Mono<List<CapabilitySummary>> capabilitiesMono = iBootcampCapabilityRepositoryPort.findCapabilityIdsByBootcampId(bootcampId)
                .collectList()
                .flatMap(ids-> ids.isEmpty()
                        ? Mono.just(List.of())
                        : iCapabilityClientPort.findByIds(ids).collectList()
                );

        return capabilitiesMono.map(capabilities -> bootcamp.toBuilder()
                .capabilities(capabilities)
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
