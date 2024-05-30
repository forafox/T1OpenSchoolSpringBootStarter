package com.forafox.logging;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "http.logging")
public class HttpLoggingProperties {

    private boolean enabled = true;
    private boolean logHeaders = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLogHeaders() {
        return logHeaders;
    }

    public void setLogHeaders(boolean logHeaders) {
        this.logHeaders = logHeaders;
    }
}