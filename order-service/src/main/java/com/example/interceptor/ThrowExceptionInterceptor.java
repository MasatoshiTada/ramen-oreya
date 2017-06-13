package com.example.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@RefreshScope
@Component
public class ThrowExceptionInterceptor extends HandlerInterceptorAdapter {

    private final int errorRate;

    private Random random;

    public ThrowExceptionInterceptor(@Value("${error.rate}") int errorRate) {
        this.errorRate = errorRate;
        if (errorRate > 0) {
            random = new Random();
            random.nextInt(errorRate);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (random != null && random.nextInt(errorRate) == 0) {
            throw new RuntimeException("Error in goods-service!!");
        }
        return true;
    }
}
