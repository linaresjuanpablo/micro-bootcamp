package com.example.bootcamp.application.useCase;

import com.example.bootcamp.domain.model.BootcampModel;
import com.example.bootcamp.domain.ports.out.IBootcampRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

public class BootcampUseCaseTest {

    @Mock
    private IBootcampRepositoryPort repositoryPort;

    @InjectMocks
    private BootcampUseCase bootcampUseCase;

    @Test
    void shouldCreateBootcampSuccessfully() {
        BootcampModel input = BootcampModel.builder()
                .name("Bootcamp Reactivo")
                .description("Spring WebFlux")
                .launchdate(LocalDate.of(2025, 2, 1))
                .duration(120)
                .capabilityIds(List.of(1L, 2L))
                .build();

        BootcampModel saved = input.toBuilder()
                .id(1L)
                .name("BOOTCAMP REACTIVO")
                .description("SPRING WEBFLUX")
                .build();

        when(repositoryPort.save(any()))
                .thenReturn(Mono.just(saved));

        StepVerifier.create(bootcampUseCase.createBoot(input))
                .expectNextMatches(result ->
                        result.getId().equals(1L) &&
                                result.getName().equals("BOOTCAMP REACTIVO") &&
                                result.getCapabilityIds().size() == 2
                )
                .verifyComplete();
    }


}
