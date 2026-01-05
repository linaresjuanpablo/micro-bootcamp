package com.example.bootcamp.infra.output.webclient.adapter;

import com.example.bootcamp.domain.model.TechnologySummary;
import com.example.bootcamp.domain.ports.out.ITechnologyClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TechnologyWebClientAdapter implements ITechnologyClientPort {

    private final WebClient webClient = WebClient.create("http://localhost:8091/api/technology");



    @Override
    public Flux<TechnologySummary> findByBootcampId(Long bootcampId) {
        return webClient.get()
                .uri("/byBootcamp/{bootcampId}", bootcampId)
                .retrieve()
                .bodyToFlux(TechnologySummary.class);

    }

    @Override
    public Mono<List<TechnologySummary>> findByBootcampTechnologiesByIds(List<Long> technologyIds) {
        return null;
    }



    @Override
    public Flux<TechnologySummary> findByIds(List<Long> ids) {
        return webClient.post()
                .uri("/findByIds")
                .bodyValue(ids)
                .retrieve()
                .bodyToFlux(TechnologySummary.class);

    }
}
