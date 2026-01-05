package com.example.bootcamp.infra.output.r2dbc.repository.list;

import com.example.bootcamp.infra.output.r2dbc.entity.list.R2CapabilityEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository

public interface IR2CapabilityListRepository extends ReactiveCrudRepository<R2CapabilityEntity, Long> {

    @Query("""
                SELECT bc.capability_id
                FROM bootcamp_capability bc
                WHERE bc.bootcamp_id = :bootcampId
            """)
    Flux<Long> findCapabilityIdsByBootcampId(Long bootcampId);
}
