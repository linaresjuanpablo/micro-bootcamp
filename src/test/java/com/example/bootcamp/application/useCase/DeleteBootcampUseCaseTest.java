package com.example.bootcamp.application.useCase;

import com.example.bootcamp.application.useCase.delete.DeleteBootcampUseCase;
import com.example.bootcamp.domain.exception.ValidationException;
import com.example.bootcamp.domain.model.delete.CapabilityDeleteSumary;
import com.example.bootcamp.domain.model.delete.TechnologyDeleteSummary;
import com.example.bootcamp.domain.ports.out.delete.IDeleteBootcampRepositoryPort;
import com.example.bootcamp.domain.ports.out.delete.IDeleteCapabilityClientPort;
import com.example.bootcamp.domain.ports.out.delete.IDeleteTechnologyClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteBootcampUseCaseTest {

    @Mock
    private IDeleteBootcampRepositoryPort bootcampRepository;

    @Mock
    private IDeleteCapabilityClientPort capabilityClient;

    @Mock
    private IDeleteTechnologyClientPort technologyClient;

    @Mock
    private TransactionalOperator transactionalOperator;

    @InjectMocks
    private DeleteBootcampUseCase deleteBootcampUseCase;

    @BeforeEach
    void setUp() {

        // Simula comportamiento real de transacción reactiva
        when(transactionalOperator.transactional(Mockito.<Mono<Object>>any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Evita NullPointer en .then(...)
        //when(bootcampRepository.deleteBootcampCapabilityRelations(anyLong()))
            //    .thenReturn(Mono.empty());

        //when(bootcampRepository.deleteBootcamp(anyLong()))
          //      .thenReturn(Mono.empty());
    }

    @Test
    void shouldThrowErrorWhenBootcampDoesNotExist() {

        when(bootcampRepository.existsById(1L))
                .thenReturn(Mono.just(false));

        StepVerifier.create(deleteBootcampUseCase.deleteBootcamp(1L))
                .expectError(ValidationException.class)
                .verify();

       // verify(bootcampRepository, never()).findCapabilitiesByBootcampId(anyLong());
    }

    @Test
    void shouldDeleteBootcampWithoutCapabilities() {

        // Evita NullPointer en .then(...)
        when(bootcampRepository.deleteBootcampCapabilityRelations(anyLong()))
            .thenReturn(Mono.empty());

        when(bootcampRepository.deleteBootcamp(anyLong()))
              .thenReturn(Mono.empty());

        when(bootcampRepository.existsById(1L))
                .thenReturn(Mono.just(true));

        when(bootcampRepository.findCapabilitiesByBootcampId(1L))
                .thenReturn(Flux.empty());

        StepVerifier.create(deleteBootcampUseCase.deleteBootcamp(1L))
                .expectNextMatches(result ->
                        result.getBootcampId().equals(1L) &&
                                result.getDeletedCapabilities().isEmpty() &&
                                result.getDeletedTechnologies().isEmpty()
                )
                .verifyComplete();
    }

    @Test
    void shouldDeleteBootcampWithUniqueCapabilityAndTechnology() {

        CapabilityDeleteSumary capability = CapabilityDeleteSumary.builder()
                .id(100L)
                .technologies(
                        List.of(
                                TechnologyDeleteSummary.builder()
                                        .id(200L)
                                        .build()
                        )
                )
                .build();

        when(bootcampRepository.existsById(1L))
                .thenReturn(Mono.just(true));

        when(bootcampRepository.findCapabilitiesByBootcampId(1L))
                .thenReturn(Flux.just(capability));

        when(bootcampRepository.countBootcampsByCapability(100L))
                .thenReturn(Mono.just(1L));

        when(technologyClient.countBootcampsByTechnology(200L))
                .thenReturn(Mono.just(1L));

        when(capabilityClient.deleteCapability(100L))
                .thenReturn(Mono.empty());

        when(technologyClient.deleteTechnology(200L))
                .thenReturn(Mono.empty());

        StepVerifier.create(deleteBootcampUseCase.deleteBootcamp(1L))
                .expectNextMatches(result ->
                        result.getDeletedCapabilities().contains(100L) &&
                                result.getDeletedTechnologies().contains(200L)
                )
                .verifyComplete();

        verify(capabilityClient).deleteCapability(100L);
        verify(technologyClient).deleteTechnology(200L);
    }

    @Test
    void shouldNotDeleteSharedCapabilityOrTechnology() {

        CapabilityDeleteSumary capability = CapabilityDeleteSumary.builder()
                .id(100L)
                .technologies(
                        List.of(
                                TechnologyDeleteSummary.builder()
                                        .id(200L)
                                        .build()
                        )
                )
                .build();

        when(bootcampRepository.existsById(1L))
                .thenReturn(Mono.just(true));

        when(bootcampRepository.findCapabilitiesByBootcampId(1L))
                .thenReturn(Flux.just(capability));

        when(bootcampRepository.countBootcampsByCapability(100L))
                .thenReturn(Mono.just(2L));

        when(technologyClient.countBootcampsByTechnology(200L))
                .thenReturn(Mono.just(2L));

        StepVerifier.create(deleteBootcampUseCase.deleteBootcamp(1L))
                .expectNextMatches(result ->
                        result.getDeletedCapabilities().isEmpty() &&
                                result.getDeletedTechnologies().isEmpty()
                )
                .verifyComplete();

        verify(capabilityClient, never()).deleteCapability(anyLong());
        verify(technologyClient, never()).deleteTechnology(anyLong());
    }
}