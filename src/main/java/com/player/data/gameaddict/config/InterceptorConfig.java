package com.player.data.gameaddict.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements  WebMvcConfigurer {
    private final  TestInterceptor interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // this interceptor will be applied to all URLs
        registry.addInterceptor(interceptor);
    }
}
