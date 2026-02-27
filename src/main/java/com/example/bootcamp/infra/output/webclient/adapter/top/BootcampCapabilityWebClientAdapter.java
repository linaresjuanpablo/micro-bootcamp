package com.example.bootcamp.infra.output.webclient.adapter.top;

import com.example.bootcamp.domain.dto.top.CapabilityResponse;
import com.example.bootcamp.domain.ports.out.top.CapabilityWebClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
//@RequiredArgsConstructor

public class BootcampCapabilityWebClientAdapter implements CapabilityWebClientPort {

    private final WebClient capabilityWebClienTop;

    public BootcampCapabilityWebClientAdapter(@Qualifier("capabilityWebClienTop") WebClient capabilityWebClienTop) {
        this.capabilityWebClienTop = capabilityWebClienTop;
    }

    @Override
    public Flux<CapabilityResponse> getCapabilitiesByIds(List<Long> ids) {
        String idsParam = ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        System.out.println("Calling micro capability with ids=" + idsParam);


        return capabilityWebClienTop.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/capability/by-ids")
                        .queryParam("ids", idsParam)
                        .build())
                .retrieve()
                .bodyToFlux(CapabilityResponse.class);
    }

}
