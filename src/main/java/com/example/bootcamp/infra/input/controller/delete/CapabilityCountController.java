package com.example.bootcamp.infra.input.controller.delete;

import com.example.bootcamp.application.useCase.BootcampService;
import com.example.bootcamp.domain.ports.in.delete.ICountBootcampsByCapabilityUseCase;
import com.example.bootcamp.domain.ports.in.delete.ICountBootcampsByTechnologyUseCase;
import com.example.bootcamp.infra.output.r2dbc.repository.BootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bootcamp")
@RequiredArgsConstructor

public class CapabilityCountController {

    private final ICountBootcampsByCapabilityUseCase countBootcampsByCapabilityUseCase;
    private final ICountBootcampsByTechnologyUseCase countBootcampsByTechnologyUseCase;

    @GetMapping("/{id}/capabilities/count")
    public Mono<Integer> countBootcampsByCapability(@PathVariable("id") Long bootcampId){
        return countBootcampsByCapabilityUseCase.exceute(bootcampId)
                .map(Long::intValue);
    }
    @GetMapping("/{id}/technologies/count")
    public Mono<Integer> countBootcampsByTechnology(@PathVariable("id") Long bootcampId){
        return countBootcampsByTechnologyUseCase.execute(bootcampId)
                .map(Long::intValue);
    }


}
