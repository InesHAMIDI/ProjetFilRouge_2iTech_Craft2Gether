package fr.crafttogether.security;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("")
                .allowedOrigins("")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(86400);
    }
}

