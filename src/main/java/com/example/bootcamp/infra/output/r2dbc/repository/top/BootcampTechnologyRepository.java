package com.example.bootcamp.infra.output.r2dbc.repository.top;

import com.example.bootcamp.infra.output.r2dbc.entity.delete.BootcampTechnologyEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BootcampTechnologyRepository extends ReactiveCrudRepository<BootcampTechnologyEntity, Long> {

    @Query("SELECT technology_id FROM bootcamp_technology WHERE bootcamp_id = $1")
    Flux<Long> findTechnologyIdsByBootcampId(Long bootcampId);
}
