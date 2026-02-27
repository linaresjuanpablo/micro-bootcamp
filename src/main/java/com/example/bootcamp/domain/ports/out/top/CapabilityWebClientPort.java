package com.example.bootcamp.domain.ports.out.top;

import com.example.bootcamp.domain.dto.top.CapabilityResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapabilityWebClientPort {

    Flux<CapabilityResponse> getCapabilitiesByIds(List<Long> ids);


}
