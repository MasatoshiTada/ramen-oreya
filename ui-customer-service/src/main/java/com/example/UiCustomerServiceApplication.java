package com.example;

import org.apache.catalina.filters.RequestDumperFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.*;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class UiCustomerServiceApplication {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(UiCustomerServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.detectRequestFactory(true)
                .interceptors(new ClientHttpRequestInterceptor() {
                    @Override
                    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
                                                        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
                        logger.info(httpRequest.getURI().toString() + "にリクエストします");
                        return clientHttpRequestExecution.execute(httpRequest, bytes);
                    }
                })
                .build();
    }

    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }
}
