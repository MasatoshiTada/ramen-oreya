package com.example;

import com.example.interceptor.ThrowExceptionInterceptor;
import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@EnableDiscoveryClient
public class GoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsServiceApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(name = "request.dump.enable", havingValue = "true")
    public RequestDumperFilter requestDumperFilter() {
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
