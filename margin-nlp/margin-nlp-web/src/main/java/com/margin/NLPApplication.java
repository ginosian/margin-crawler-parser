package com.margin;


import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:com.margin/nlp-context-web.xml")
public class NLPApplication {

    protected NLPApplication() {
        super();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(NLPApplication.class)
                .logStartupInfo(true)
                .bannerMode(Banner.Mode.LOG)
                .run(args);
    }
}