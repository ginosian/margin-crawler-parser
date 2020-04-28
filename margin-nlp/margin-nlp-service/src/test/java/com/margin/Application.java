package com.margin;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {
    protected Application() {
        super();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .logStartupInfo(true)
                .bannerMode(Banner.Mode.LOG)
                .run(args);
    }
}
