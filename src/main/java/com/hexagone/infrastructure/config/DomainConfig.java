package com.hexagone.infrastructure.config;

import com.hexagone.architecture.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.hexagone.application",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = UseCase.class)
)
public class DomainConfig {
}