package com.example.bootcamp.infra.output.webclient.adapter;

import com.example.bootcamp.domain.exception.ExternalServiceException;
import com.example.bootcamp.domain.ports.out.delete.IDeleteTechnologyClientPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class TechnologyDeleteWebClientAdapter implements IDeleteTechnologyClientPort {

    @Qualifier("technologydeletewebclient")
    private final WebClient webClient;

    public TechnologyDeleteWebClientAdapter(@Qualifier("technologydeletewebclient") WebClient webClient){
        this.webClient = webClient;
    }

    @Override
    public Mono<Long> countBootcampsByTechnology(Long techologyId) {
        return webClient
                .get()
                .uri("/api/technologies/{id}/bootcamps/count", techologyId)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> Mono.error(new ExternalServiceException("Technology service returned 4xx")))
                .onStatus(status -> status.is5xxServerError(),
                        response-> Mono.error(new ExternalServiceException("Technology service returned 5xx")))
                .bodyToMono(Long.class);

    }
    @Override
    public Mono<Void> deleteTechnology(Long technologyId) {
        return webClient
                .delete()
                .uri("/api/technologies/{id}", technologyId)
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> Mono.error(new ExternalServiceException("Error deleting technology")))
                .bodyToMono(Void.class);

    }
}
