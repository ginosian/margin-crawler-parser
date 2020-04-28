package com.margin;


import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:com.margin/location-tools-context-web.xml")
public class LocationToolsApplication {

    protected LocationToolsApplication() {
        super();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(LocationToolsApplication.class)
                .logStartupInfo(true)
                .bannerMode(Banner.Mode.LOG)
                .run(args);
    }
}