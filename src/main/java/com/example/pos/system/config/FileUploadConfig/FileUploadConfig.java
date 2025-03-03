package com.example.pos.system.config.FileUploadConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class FileUploadConfig implements WebMvcConfigurer {

    @Value("${server.path.image}")
    private String pathImage;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:" + pathImage + "/");  // Ensure the trailing slash is added
    }
}

