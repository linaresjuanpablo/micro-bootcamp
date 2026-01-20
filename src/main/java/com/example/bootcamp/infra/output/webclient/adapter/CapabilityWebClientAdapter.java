package com.example.bootcamp.infra.output.webclient.adapter;


import com.example.bootcamp.domain.model.CapabilitySummary;
import com.example.bootcamp.domain.ports.out.ICapabilityClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
//@RequiredArgsConstructor

public class CapabilityWebClientAdapter implements ICapabilityClientPort {

    @Qualifier("capabilitywebclient")

    private final WebClient webClient;// = WebClient.create("http://localhost:8092/api/capability");

    public CapabilityWebClientAdapter(@Qualifier("capabilitywebclient") WebClient webClient ){
        this.webClient = webClient;
    }

    @Override
    public Flux<CapabilitySummary> findByBootcampId(Long bootcampId) {

        return webClient.get()
                .uri("/byBootcamp/{bootcampId}", bootcampId)
                .retrieve()
                .bodyToFlux(CapabilitySummary.class);

    }
    @Override
    public Flux<CapabilitySummary> findByIds(List<Long> ids) {
        String idsParam = ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/by-ids")
                        .queryParam("ids", idsParam)
                        .build())
                .retrieve()
                .bodyToFlux(CapabilitySummary.class);

    }
}
