package com.example.bootcamp.infra.output.r2dbc.repository;

import com.example.bootcamp.domain.ports.out.delete.ICapabilityCountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;


import reactor.core.publisher.Mono;
@Primary
@Repository
@RequiredArgsConstructor
public class CapabilityCountRepository implements ICapabilityCountRepositoryPort {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<Long> countCapabilitiesByBootcamp(Long bootcampId) {
        String sql = "SELECT COUNT(*) AS total FROM bootcamp_capability WHERE bootcamp_id = :bootcampId";

        return template.getDatabaseClient()
                .sql(sql)
                .bind("bootcampId", bootcampId)
                .map(row -> row.get("total", Long.class))
                .one()
                .defaultIfEmpty(0L); // devuelve 0 si no hay registros


    }
}
