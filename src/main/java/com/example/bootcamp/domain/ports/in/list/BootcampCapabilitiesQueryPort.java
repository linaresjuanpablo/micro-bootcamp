package com.example.bootcamp.domain.ports.in.list;

import com.example.bootcamp.domain.dto.BootcampWithCapabilitiesResponse;
import reactor.core.publisher.Mono;

public interface BootcampCapabilitiesQueryPort {

    Mono<BootcampWithCapabilitiesResponse> getBootcampWithCapabilities(Long bootcampId);
}


