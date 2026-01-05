package com.example.bootcamp.application.useCase;

import com.example.bootcamp.application.useCase.list.ListBootcampUseCase;
import com.example.bootcamp.domain.model.CapabilitySummary;
import com.example.bootcamp.domain.model.TechnologySummary;
import com.example.bootcamp.domain.model.list.BootcampListModel;
import com.example.bootcamp.domain.ports.out.ICapabilityClientPort;
import com.example.bootcamp.domain.ports.out.ITechnologyClientPort;
import com.example.bootcamp.domain.ports.out.list.IBootcampCapabilityRepositoryPort;
import com.example.bootcamp.domain.ports.out.list.IBootcampRepositoryPort;
import com.example.bootcamp.domain.ports.out.list.IBootcampTechnologyRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;

public class ListBootcampUseCaseTest {

    private IBootcampRepositoryPort iBootcampRepositoryPort;
    private ICapabilityClientPort iCapabilityClientPort;
    private IBootcampCapabilityRepositoryPort iBootcampCapabilityRepositoryPort;
    private IBootcampTechnologyRepositoryPort iBootcampTechnologyRepositoryPort;
    private ITechnologyClientPort iTechnologyClientPort;

    private ListBootcampUseCase listBootcampUseCase;

    @BeforeEach
    void setUp(){
        iBootcampRepositoryPort = Mockito.mock(IBootcampRepositoryPort.class);
        iCapabilityClientPort = Mockito.mock(ICapabilityClientPort.class);
        iBootcampCapabilityRepositoryPort = Mockito.mock(IBootcampCapabilityRepositoryPort.class);
        iBootcampTechnologyRepositoryPort = Mockito.mock(IBootcampTechnologyRepositoryPort.class);
        iTechnologyClientPort = Mockito.mock(ITechnologyClientPort.class);

        listBootcampUseCase = new ListBootcampUseCase(
                iBootcampRepositoryPort,
                iCapabilityClientPort,
                iBootcampCapabilityRepositoryPort,
                iBootcampTechnologyRepositoryPort,
                iTechnologyClientPort
        );
    }
    @Test
    void shouldListBootcamsWithCapabilitiesAndTechnologies(){
        Long bootcampId = 1L;
        BootcampListModel bootcamp = BootcampListModel.builder()
                .id(bootcampId)
                .name("java Bootcamp")
                .description("Backend training")
                .launchdate(LocalDate.now())
                .duration(12)
                .build();

        CapabilitySummary capability = new CapabilitySummary(bootcampId, "Spring Boot", List.of());
        TechnologySummary technology = new TechnologySummary(bootcampId, "PostgreSQL");

        when(iBootcampRepositoryPort.findAll(any(Integer.class), any(Integer.class), any(), any()))
                .thenReturn(Flux.just(bootcamp));

        when(iBootcampCapabilityRepositoryPort.findCapabilityIdsByBootcampId(bootcampId))
                .thenReturn(Flux.just(bootcampId));

        when(iCapabilityClientPort.findByIds(List.of(bootcampId)))
                .thenReturn(Flux.just(capability));

        when(iBootcampTechnologyRepositoryPort.findTechnologyIdsByBootcampId(bootcampId))
                .thenReturn(Flux.just(bootcampId));

        when(iTechnologyClientPort.findByids(List.of(bootcampId)))
                .thenReturn(Flux.just(technology));
        Flux<BootcampListModel> result = listBootcampUseCase.listBootcamps(0, 10, "name", "asc");

        StepVerifier.create(result)
                .assertNext(enriched ->{
                    assert enriched.getCapabilities().size() == 1;
                    assert enriched.getCapabilities().get(0).getName().equals("Spring Boot");
                    assert enriched.getTechnologies().size() == 1;
                    assert enriched.getTechnologies().get(0).getName().equals("PostgreSQL");
                })
                .verifyComplete();

    }
}
