package com.margin;


import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:com.margin/scraper-context-web.xml")
public class ScraperApplication {

    protected ScraperApplication() {
        super();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ScraperApplication.class)
                .logStartupInfo(true)
                .bannerMode(Banner.Mode.LOG)
                .run(args);
    }
}