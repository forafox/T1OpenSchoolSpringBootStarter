package com.forafox.logging.config;


import com.forafox.logging.filter.HttpLoggingFilter;
import com.forafox.logging.HttpLoggingProperties;
import jakarta.servlet.Filter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Filter.class)
@EnableConfigurationProperties(HttpLoggingProperties.class)
public class HttpLoggingAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "http.logging.enabled", havingValue = "true", matchIfMissing = true)
    public HttpLoggingFilter httpLoggingFilter(HttpLoggingProperties properties) {
        return new HttpLoggingFilter(properties);
    }
}