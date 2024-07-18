package com.rightcode.mtc.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "cors")
public class CorsProps {
    private List<String> allowedOrigins;
    private List<String> allowedMethods;
    private String allowedHeaders;
    private boolean allowedCredentials;
    private String corsConfiguration;
    private long maxAge;
}
