package com.example;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;

@EnableBinding(Processor.class)
@EnableZuulProxy
@SpringBootApplication
public class StaffApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaffApiGatewayApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(name = "request.dump.enable", havingValue = "true")
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }
}
