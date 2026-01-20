package com.example.bootcamp.infra.input.controller.delete;

import com.example.bootcamp.domain.ports.in.delete.ICountBootcampsByCapabilityUseCase;
import com.example.bootcamp.domain.ports.in.delete.ICountBootcampsByTechnologyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/delete")
@RequiredArgsConstructor

public class CapabilityCountController {

    private final ICountBootcampsByCapabilityUseCase countBootcampsByCapabilityUseCase;
    private final ICountBootcampsByTechnologyUseCase countBootcampsByTechnologyUseCase;

    @GetMapping("/capabilities/{id}/bootcamps/count")
    public Mono<Long> countBootcampsByCapability(@PathVariable("id") Long capabilityId){
        return countBootcampsByCapabilityUseCase.exceute(capabilityId);
    }

    @GetMapping("/technologies/{id}/bootcamps/count")
    public Mono<Long> countBootcampsByTechnology(@PathVariable("id") Long technologyId){
        return countBootcampsByTechnologyUseCase.execute(technologyId);
    }

}
