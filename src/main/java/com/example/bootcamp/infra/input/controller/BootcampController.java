package com.example.bootcamp.infra.input.controller;

import com.example.bootcamp.domain.ports.in.ICreateBootcampUseCase;
import com.example.bootcamp.infra.input.controller.dto.BootcampResponse;
import com.example.bootcamp.infra.input.controller.dto.CreateBootcampRequest;
import com.example.bootcamp.infra.input.mapper.BootcampWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bootcamp")
@RequiredArgsConstructor

public class BootcampController {

    private final ICreateBootcampUseCase iCreateBootcampUseCase;
    private final BootcampWebMapper bootcampWebMapper;


    @PostMapping("/create")
    public Mono<ResponseEntity<BootcampResponse>> createBootcamp(@RequestBody CreateBootcampRequest createBootcampRequest){
        return iCreateBootcampUseCase.createBoot(bootcampWebMapper.toDomain(createBootcampRequest))
                .map(bootcampWebMapper::toResponse)
                .map(response-> ResponseEntity.status(HttpStatus.CREATED).body(response));

    }
    /*
    *@PostMapping("/create")
public Mono<ResponseEntity<BootcampResponse>> createBootcamp(
        @RequestBody BootcampModel bootcampModel) {

    return iCreateBootcampUseCase.createBoot(bootcampModel)
            .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
}

    * */
}
