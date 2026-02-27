package com.example.bootcamp.infra.input.controller;

import com.example.bootcamp.application.useCase.BootcampService;
import com.example.bootcamp.domain.ports.in.ICreateBootcampUseCase;
import com.example.bootcamp.infra.input.controller.dto.BootcampResponse;
import com.example.bootcamp.infra.input.controller.dto.CreateBootcampRequest;
import com.example.bootcamp.infra.input.mapper.BootcampWebMapper;
import com.example.bootcamp.infra.output.r2dbc.entity.BootcampEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bootcamp")
@RequiredArgsConstructor

public class BootcampController {

    private final ICreateBootcampUseCase iCreateBootcampUseCase;
    private final BootcampWebMapper bootcampWebMapper;
    private final BootcampService service;


    @PostMapping("/create")
    public Mono<ResponseEntity<BootcampResponse>> createBootcamp(@RequestBody CreateBootcampRequest createBootcampRequest){
        return iCreateBootcampUseCase.createBoot(bootcampWebMapper.toDomain(createBootcampRequest))
                .map(bootcampWebMapper::toResponse)
                .map(response-> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<BootcampEntity>> getBootcampById(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .onErrorResume(e-> Mono.just(ResponseEntity.notFound().build()));
    }



}
