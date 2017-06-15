package com.example;

import com.example.interceptor.ThrowExceptionInterceptor;
import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.entity", "org.springframework.data.jpa.convert.threeten"})
@EnableDiscoveryClient
@EnableBinding({Sink.class, Sink2.class})
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(name = "request.dump.enable", havingValue = "true")
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }

    @Configuration
    static class HandlerInterceptorConfig extends WebMvcConfigurerAdapter {

        @Autowired
        ThrowExceptionInterceptor throwExceptionInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(throwExceptionInterceptor)
                    .addPathPatterns("/api/**");
        }

    }
}
