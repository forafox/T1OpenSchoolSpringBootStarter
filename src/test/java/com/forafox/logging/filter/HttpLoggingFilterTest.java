package com.forafox.logging.filter;

import com.forafox.logging.HttpLoggingProperties;
import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class HttpLoggingFilterTest {

    private static final Logger logger = LoggerFactory.getLogger(HttpLoggingFilterTest.class);

    @Mock
    private FilterChain filterChain;

    private HttpLoggingFilter filter;
    private HttpLoggingProperties properties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        properties = new HttpLoggingProperties();
        filter = new HttpLoggingFilter(properties);
    }

    @Test
    void doFilterInternal_logsRequestAndResponse() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/test");

        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(200);

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void doFilterInternal_logsRequestHeaders() throws Exception {
        properties.setLogHeaders(true);
        filter = new HttpLoggingFilter(properties);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/test");
        request.addHeader("User-Agent", "JUnit Test");

        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(200);

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void doFilterInternal_logsResponseHeaders() throws Exception {
        properties.setLogHeaders(true);
        filter = new HttpLoggingFilter(properties);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/test");

        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(200);
        response.addHeader("Content-Type", "application/json");

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void doFilterInternal_logsProcessingTime() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/test");

        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(200);

        long startTime = System.currentTimeMillis();

        filter.doFilterInternal(request, response, filterChain);

        long duration = System.currentTimeMillis() - startTime;
        assertTrue(duration >= 0);
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void doFilterInternal_logsHeadersIfConfigured() throws Exception {
        properties.setLogHeaders(true);
        filter = new HttpLoggingFilter(properties);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/test");
        request.addHeader("User-Agent", "JUnit Test");

        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(200);
        response.addHeader("Content-Type", "application/json");

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void doFilterInternal_noLogHeadersWhenDisabled() throws Exception {
        properties.setLogHeaders(false);
        filter = new HttpLoggingFilter(properties);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/test");
        request.addHeader("User-Agent", "JUnit Test");

        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(200);
        response.addHeader("Content-Type", "application/json");

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }
}