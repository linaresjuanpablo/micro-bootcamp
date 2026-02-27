package com.example.bootcamp.infra.input.controller;


import com.example.bootcamp.domain.dto.list.BootcampPageResponse;
import com.example.bootcamp.domain.ports.in.list.IListBootcampUseCase;
import com.example.bootcamp.infra.input.controller.dto.list.BootcampListResponse;
import com.example.bootcamp.infra.input.mapper.list.BootcampListWebMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bootcamp")
@RequiredArgsConstructor
public class BootcampListController {

    private final IListBootcampUseCase iListBootcampUseCase;
    private final BootcampListWebMapper bootcampListWebMapper;

    @GetMapping("/list")
    public Mono<BootcampPageResponse> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        return iListBootcampUseCase.listBootcampsPage(page, size, sortBy, direction);

    }
}
