package com.example.bootcamp.infra.config;

import com.example.bootcamp.application.useCase.delete.DeleteBootcampUseCase;
import com.example.bootcamp.domain.ports.in.delete.IDeleteBootcampUseCase;
import com.example.bootcamp.domain.ports.out.delete.IDeleteBootcampRepositoryPort;
import com.example.bootcamp.domain.ports.out.delete.IDeleteCapabilityClientPort;
import com.example.bootcamp.domain.ports.out.delete.IDeleteTechnologyClientPort;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.reactive.TransactionalOperator;

public class UseCaseConfig {

    @Bean
    public IDeleteBootcampUseCase iDeleteBootcampUseCase(
            IDeleteBootcampRepositoryPort iDeleteBootcampRepositoryPort,
            //IDeleteCapabilityClientPort iDeleteCapabilityClientPort,
            IDeleteTechnologyClientPort iDeleteTechnologyClientPort,
            TransactionalOperator transactionalOperator

    ){
        return new DeleteBootcampUseCase(iDeleteBootcampRepositoryPort,
                iDeleteTechnologyClientPort, transactionalOperator);
    }

}
