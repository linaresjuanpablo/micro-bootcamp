package com.example.bootcamp.infra.output.r2dbc.repository.list;

import com.example.bootcamp.infra.output.r2dbc.entity.list.R2TechnologyEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository

public interface IR2TechnologyListRepository extends ReactiveCrudRepository<R2TechnologyEntity, Long> {

    @Query("""
                SELECT bt.technology_id
                FROM bootcamp_technology bt
                WHERE bt.bootcamp_id = :bootcampId
            """)
    Flux<Long> findTechnologyIdsByBootcampId(Long capabilityId);


}
