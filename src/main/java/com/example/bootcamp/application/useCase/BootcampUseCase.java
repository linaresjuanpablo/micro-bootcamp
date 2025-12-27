package com.example.bootcamp.application.useCase;

import com.example.bootcamp.domain.exception.DataAccessException;
import com.example.bootcamp.domain.exception.DuplicateResourceException;
import com.example.bootcamp.domain.exception.ValidationException;
import com.example.bootcamp.domain.model.BootcampModel;
import com.example.bootcamp.domain.ports.in.ICreateBootcampUseCase;
import com.example.bootcamp.domain.ports.out.IBootcampRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
@Slf4j

public class BootcampUseCase implements ICreateBootcampUseCase {

    private final IBootcampRepositoryPort iBootcampRepositoryPort;

    private static final int MIN_CAPA = 1;
    private static final int MAX_CAPA = 4;

    private static final String ERR_NAME_INVALID = "ERR_NAME_INVALID";
    private static final String ERR_DESCRI_INVALID = "ERR_DESCRI_INVALID";
    private static final String ERR_BOOTCAMP_EXISTS = "ERR_BOOTCAMP_EXISTS";
    private static final String ERR_TECNOLOGIA_MINIMO = "ERR_BOOTCAMP_MINIMO";
    private static final String ERR_TECNOLOGIA_MAXIMO = "ERR_BOOTCAMP_MAXIMO";
    private static final String ERR_DUPLICATE = "ERR_DUPLICATE";
    private static final String ERR_BOOTCAMP_NOT_FOUND = "ERR_BOOTCAMP_NOT_FOUND";


    @Override
    public Mono<BootcampModel> createBoot(BootcampModel bootcampModel){
        return validateBoot(bootcampModel)
                .map(this::normalize)
                .flatMap(iBootcampRepositoryPort::save)

                .onErrorResume(e->{
                    if (e instanceof ValidationException || e instanceof DuplicateResourceException){
                        return Mono.error(e);
                    }
                    log.error("Error guardando bootcamp", e);
                    return Mono.error(new DataAccessException(
                            ERR_BOOTCAMP_EXISTS,
                            "Error registrando bootcamp"
                    ));
                });

    }

    private BootcampModel normalize (BootcampModel bootcampModel){
        return bootcampModel.toBuilder()
                .name(bootcampModel.getName().toUpperCase())
                .description(bootcampModel.getDescription().toUpperCase())
                .build();
    }

    public Mono<BootcampModel> validateBoot (BootcampModel bootcampModel){

        if (bootcampModel.getName() == null || bootcampModel.getName().isBlank()) {
            return Mono.error(new ValidationException(
                    ERR_NAME_INVALID, "El nombre del bootcamp es inválido"));
        }
        if (bootcampModel.getDescription() == null || bootcampModel.getDescription().isBlank()) {
            return Mono.error(new ValidationException(
                    ERR_DESCRI_INVALID, "La descripción es inválida"));
        }
        if (bootcampModel.getCapabilityIds()== null ||
                bootcampModel.getCapabilityIds().size() < MIN_CAPA) {
            return Mono.error(new ValidationException(
                    ERR_TECNOLOGIA_MINIMO, "El bootcamp debe tener al menos 1 capacidad"));
        }
        if (bootcampModel.getCapabilityIds().size() > MAX_CAPA) {
            return Mono.error(new ValidationException(
                    ERR_TECNOLOGIA_MAXIMO, "El bootcamp no puede tener más de 4 capacidades"));
        }
        return Mono.just(bootcampModel);
    }




















}
