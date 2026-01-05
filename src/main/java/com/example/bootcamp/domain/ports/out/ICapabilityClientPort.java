package com.example.bootcamp.domain.ports.out;


import com.example.bootcamp.domain.model.CapabilitySummary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICapabilityClientPort {


    Flux<CapabilitySummary> findByBootcampId(Long bootcampId);

    Flux<CapabilitySummary> findByIds(List<Long> ids);
}
