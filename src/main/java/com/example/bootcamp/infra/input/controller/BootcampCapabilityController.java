package com.example.bootcamp.infra.input.controller;

import com.example.bootcamp.application.useCase.list.BootcampCapabilitiesService;
import com.example.bootcamp.domain.dto.BootcampWithCapabilitiesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bootcamp")
@RequiredArgsConstructor

public class BootcampCapabilityController {

    private final BootcampCapabilitiesService service;

    @GetMapping("/{id}/with-capabilities")
    public Mono<BootcampWithCapabilitiesResponse> getBootcampWithCapabilities(@PathVariable Long id){
        return service.getBootcampWithCapabilities(id);

    }
}
