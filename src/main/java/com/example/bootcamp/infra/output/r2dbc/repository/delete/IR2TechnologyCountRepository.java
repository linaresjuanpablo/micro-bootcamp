package com.example.bootcamp.infra.output.r2dbc.repository.delete;

import com.example.bootcamp.infra.output.r2dbc.entity.delete.BootcampTechnologyEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IR2TechnologyCountRepository extends ReactiveCrudRepository<BootcampTechnologyEntity, Long> {

    @Query("SELECT COUNT(*) FROM bootcamp_technology WHERE technology_id = :technologyId")
    Mono<Long> countBootcampsByTechnology(Long technologyId);


}
