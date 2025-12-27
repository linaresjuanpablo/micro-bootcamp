package com.example.bootcamp.infra.output.webclient.adapter;

import com.example.bootcamp.domain.model.BootcampSummary;
import com.example.bootcamp.domain.model.CapabilitySummary;
import com.example.bootcamp.domain.ports.out.ICapabilityClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor

public class CapabilityWebClientAdapter implements ICapabilityClientPort {

    private final WebClient tecnologiaWebClient;

    @Override
    public Mono<Boolean> existsById(Long capabilityId) {
        return tecnologiaWebClient
                .get()
                .uri("/api/cabability/{id}", capabilityId)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response-> Mono.empty())
                .toBodilessEntity()
                .map(r-> true)
                .defaultIfEmpty(false);
    }

    @Override
    public Flux<CapabilitySummary> findByIds(List<Long> capabilityIds) {
        return Flux.fromIterable(capabilityIds)
                .flatMap(id ->
                        tecnologiaWebClient
                                .get()
                                .uri("/api/capability/{id}", id)
                                .retrieve()
                                .bodyToMono(CapabilitySummary.class)
                        );

    }
}
