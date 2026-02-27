package com.example.bootcamp.infra.input.controller;

import com.example.bootcamp.domain.model.delete.BootcampDeleteModel;
import com.example.bootcamp.domain.ports.in.delete.IDeleteBootcampUseCase;
import com.example.bootcamp.infra.input.controller.dto.delete.BootcampDeleteResponseDto;
import com.example.bootcamp.infra.input.mapper.delete.BootcampDeleteWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bootcamp")
@RequiredArgsConstructor

public class BootcampDeleteController {

    private final IDeleteBootcampUseCase iDeleteBootcampUseCase;
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<BootcampDeleteResponseDto> deleteBootcamp(@PathVariable("id") Long bootcampId){
        return  iDeleteBootcampUseCase.deleteBootcamp(bootcampId)
                .map(BootcampDeleteWebMapper::toDto);
    }
}
