package com.example.bootcamp.application.useCase;


import com.example.bootcamp.application.useCase.list.ListBootcampUseCase;
import com.example.bootcamp.domain.model.CapabilitySummary;
import com.example.bootcamp.domain.model.list.BootcampListModel;
import com.example.bootcamp.domain.model.list.PaginationMetadata;
import com.example.bootcamp.domain.ports.out.ICapabilityClientPort;
import com.example.bootcamp.domain.ports.out.list.IBootcampCapabilityRepositoryPort;
import com.example.bootcamp.domain.ports.out.list.IBootcampRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ListBootcampUseCaseTest {

    @Mock
    private IBootcampRepositoryPort bootcampRepository;

    @Mock
    private ICapabilityClientPort capabilityClientPort;

    @Mock
    private IBootcampCapabilityRepositoryPort bootcampCapabilityRepositoryPort;

    @InjectMocks
    private ListBootcampUseCase listBootcampUseCase;

    private BootcampListModel bootcamp1;
    private BootcampListModel bootcamp2;

    @BeforeEach
    void setUp() {
        bootcamp1 = BootcampListModel.builder()
                .id(1L)
                .name("Bootcamp Java")
                .build();

        bootcamp2 = BootcampListModel.builder()
                .id(2L)
                .name("Bootcamp Node.js")
                .build();


    }

    @Test
    void shouldReturnBootcampsWithCapabilitiesAndMetadata() {
        // Mock repositorio principal
        when(bootcampRepository.findAll(0, 10, IBootcampRepositoryPort.SortKey.NAME, IBootcampRepositoryPort.SortDirection.ASC))
                .thenReturn(Flux.just(bootcamp1, bootcamp2));

        // Mock count total
        when(bootcampRepository.count()).thenReturn(Mono.just(2L));

        // Mock capacidades asociadas
        when(bootcampCapabilityRepositoryPort.findCapabilityIdsByBootcampId(1L))
                .thenReturn(Flux.just(100L, 101L));
        when(bootcampCapabilityRepositoryPort.findCapabilityIdsByBootcampId(2L))
                .thenReturn(Flux.empty());

        // Mock cliente de capacidades
        when(capabilityClientPort.findByIds(anyList()))
                .thenAnswer(invocation -> {
                    List<Long> ids = invocation.getArgument(0);
                    return Flux.fromIterable(ids)
                            .map(id -> CapabilitySummary.builder().id(id).name("Capability-" + id).build());
                });

        StepVerifier.create(listBootcampUseCase.listBootcampsPage(0, 10, "name", "asc"))
                .expectNextMatches(response -> {
                    // Validar metadatos
                    PaginationMetadata metadata = response.getMetadata();
                    return metadata.getPage() == 0 &&
                            metadata.getSize() == 10 &&
                            metadata.getTotalElements() == 2 &&
                            metadata.getTotalPages() == 1 &&
                            metadata.getSortBy().equals("name") &&
                            metadata.getDirection().equals("asc") &&
                            // Validar contenido
                            response.getContent().size() == 2 &&
                            response.getContent().get(0).getCapabilities().size() == 2 &&
                            response.getContent().get(1).getCapabilities().isEmpty();
                })
                .verifyComplete();
    }

    @Test
    void shouldHandleEmptyBootcamps() {
        when(bootcampRepository.findAll(0, 10, IBootcampRepositoryPort.SortKey.NAME, IBootcampRepositoryPort.SortDirection.ASC))
                .thenReturn(Flux.empty());
        when(bootcampRepository.count()).thenReturn(Mono.just(0L));

        StepVerifier.create(listBootcampUseCase.listBootcampsPage(0, 10, "name", "asc"))
                .expectNextMatches(response ->
                        response.getContent().isEmpty() &&
                                response.getMetadata().getTotalElements() == 0 &&
                                response.getMetadata().getTotalPages() == 0
                )
                .verifyComplete();
    }

    @Test
    void shouldThrowErrorForInvalidSortKey() {
        StepVerifier.create(listBootcampUseCase.listBootcampsPage(0, 10, "invalid", "asc"))
                .expectErrorMatches(e -> e instanceof IllegalArgumentException &&
                        e.getMessage().equals("Invalid sortBy: invalid"))

                .verify();
    }

    @Test
    void shouldThrowErrorForInvalidDirection() {
        StepVerifier.create(listBootcampUseCase.listBootcampsPage(0, 10, "name", "wrong"))
                .expectErrorMatches(e -> e instanceof IllegalArgumentException &&
                        e.getMessage().equals("Invalid direction: wrong"))
                .verify();
    }


}