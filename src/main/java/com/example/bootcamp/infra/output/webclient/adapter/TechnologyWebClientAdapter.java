/*package com.example.bootcamp.infra.output.webclient.adapter;

import com.example.bootcamp.domain.exception.ExternalServiceException;
import com.example.bootcamp.domain.model.TechnologySummary;
import com.example.bootcamp.domain.ports.out.ITechnologyClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
//@RequiredArgsConstructor
public class TechnologyWebClientAdapter implements ITechnologyClientPort {

    private final WebClient webClient;// = WebClient.create("http://localhost:8091/api/technology");

    public TechnologyWebClientAdapter(@Qualifier("technologywebclient") WebClient webClient){
        this.webClient = webClient;
    }


    @Override
    public Flux<TechnologySummary> findByBootcampId(Long bootcampId) {
        return webClient
                .get()
                .uri("/byBootcamp/{bootcampId}", bootcampId)
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response -> Mono.error(new ExternalServiceException("Error technologies by bootcamp")))
                .bodyToFlux(TechnologySummary.class)
                ;
    }

    @Override
    public Mono<List<TechnologySummary>> findByBootcampTechnologiesByIds(List<Long> technologyIds) {
        return webClient.post()
                .uri("/findByBootcampTechnologiesByIds")
                .bodyValue(technologyIds)
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> Mono.error(new ExternalServiceException("Error fetching technologies by IDs")))
                .bodyToFlux(TechnologySummary.class)
                .collectList();
    }

    @Override
    public Flux<TechnologySummary> findByIds(List<Long>ids) {
        return webClient.post()
                .uri("/findByIds")
                .bodyValue(ids)
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> Mono.error(new ExternalServiceException("Error fetching technologies by IDs")))
                .bodyToFlux(TechnologySummary.class);
    }
}
*/