package com.example.bootcamp.infra.output.webclient.adapter;

import com.example.bootcamp.domain.model.delete.TechnologyDeleteSummary;
import com.example.bootcamp.domain.ports.out.delete.IDeleteCapabilityClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
//@RequiredArgsConstructor

public class DeleteCapabilityWebClientAdapter implements IDeleteCapabilityClientPort {


    //@Qualifier("capabilitywebclient")
    private final WebClient webClient;


    public DeleteCapabilityWebClientAdapter(@Qualifier("capabilitydeletewebclient") WebClient webClient){
        this.webClient = webClient;
    }
        @Override
    public Mono<Long> countBootcampByCapability(Long capabilityId) {
        return null;
    }

    @Override
    public Mono<Void> deleteCapability(Long capabilityId) {
        return webClient.delete()
                .uri("/api/capability/{id}", capabilityId)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Flux<TechnologyDeleteSummary> findTechnologiesByCapabilityId(Long capabilityId) {
        return null;
    }
}
