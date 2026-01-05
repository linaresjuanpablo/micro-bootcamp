package com.example.bootcamp.infra.output.r2dbc.repository.list;

import com.example.bootcamp.infra.output.r2dbc.entity.list.R2BootcampListCapabilityEntity;
import com.example.bootcamp.infra.output.r2dbc.entity.list.R2BootcampListEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository

public interface IR2BootcampListRepository extends ReactiveCrudRepository<R2BootcampListEntity, Long> {

    @Query("""
        SELECT
            b.id,
            b.name,
            b.description,
            b.launchdate,
            b.duration,
            ARRAY_AGG(bc.capability_id) AS capability_ids,
            COUNT(bc.capability_id) AS capacity_count
        FROM bootcamp b
        LEFT JOIN bootcamp_capability bc ON bc.bootcamp_id = b.id
        GROUP BY b.id
        ORDER BY 
            CASE WHEN :sortBy = 'name' AND :direction = 'ASC' THEN b.name END ASC,
            CASE WHEN :sortBy = 'name' AND :direction = 'DESC' THEN b.name END DESC,
            CASE WHEN :sortBy = 'capacityCount' AND :direction = 'ASC' THEN COUNT(bc.capability_id) END ASC,
            CASE WHEN :sortBy = 'capacityCount' AND :direction = 'DESC' THEN COUNT(bc.capability_id) END DESC
        LIMIT :size OFFSET :offset
        """)
            Flux<R2BootcampListEntity> findAllPage(
            int size,
            long offset,
            String sortBy,
            String direction
    );


}
