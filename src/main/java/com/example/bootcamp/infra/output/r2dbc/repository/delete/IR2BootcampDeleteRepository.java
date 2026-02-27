package com.example.bootcamp.infra.output.r2dbc.repository.delete;

import com.example.bootcamp.infra.output.r2dbc.entity.delete.R2BootcampDeleteEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IR2BootcampDeleteRepository extends ReactiveCrudRepository<R2BootcampDeleteEntity, Long> {

    @Query("""
        SELECT capability_id
        FROM bootcamp_capability
        WHERE bootcamp_id = :bootcampId
    """)
    Flux<Long> findCapabilityIdsByBootcampId(Long bootcampId);

    @Query("""
        DELETE FROM bootcamp_capability
        WHERE bootcamp_id = :bootcampId
    """)
    Mono<Void> deleteRelationsByBootcampId(Long bootcampId);

    @Query("""
        DELETE FROM bootcamp_technology
        WHERE bootcamp_id = :bootcampId
    """)
    Mono<Void> deleteTechnologyRelationsByBootcampId(Long bootcampId);

    /*@Query("""
    SELECT bt.technology_id
    FROM bootcamp_technology bt
    JOIN bootcamp_capability bc ON bt.bootcamp_id = bc.bootcamp_id
    WHERE bc.capability_id = :capabilityId
""")
    Flux<Long> findTechnologiesByCapabilityId(Long capabilityId);*/

    @Query("""
        SELECT COUNT(*)
        FROM bootcamp_capability
        WHERE capability_id = :capabilityId
    """)
    Mono<Long> countBootcampsByCapability(Long capabilityId);

   /*@Query("""
        DELETE FROM capability
        WHERE id = :capabilityId
    """)
    Mono<Void> deleteCapabilityById(Long capabilityId);*/



}
