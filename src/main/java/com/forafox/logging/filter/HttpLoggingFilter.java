package com.forafox.logging.filter;

import com.forafox.logging.HttpLoggingProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class HttpLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(HttpLoggingFilter.class);
    private final HttpLoggingProperties properties;

    public HttpLoggingFilter(HttpLoggingProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {
        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logger.info("HTTP {} - {} - {} - {}ms", request.getMethod(), request.getRequestURI(), response.getStatus(), duration);
            if (properties.isLogHeaders()) {
                logger.info("Request Headers: {}", request.getHeaderNames());
                logger.info("Response Headers: {}", response.getHeaderNames());
            }
        }
    }
}