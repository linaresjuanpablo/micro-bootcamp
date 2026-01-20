package com.example.bootcamp.infra.output.webclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

      @Bean(name = "capabilitywebclient")
    public WebClient capabilityWebClient(@Value("${services.capacidad.base-url}") String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }



    @Bean(name = "technologywebclient")
    public WebClient technologyWebClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8091") // URL del microservicio de tecnologías
                .build();
    }

    @Bean(name = "capabilitydeletewebclient")
    public WebClient capabilityDeleteWebClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8092") //
                .build();
    }

    @Bean(name = "technologydeletewebclient")
    public WebClient technologyDeleteWebClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8091") //
                .build();
    }




    }
