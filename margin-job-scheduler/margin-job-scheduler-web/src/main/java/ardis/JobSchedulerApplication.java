package com.margin;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:com.margin/job-scheduler-context-web.xml")
public class JobSchedulerApplication {

    protected JobSchedulerApplication() {
        super();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(JobSchedulerApplication.class)
                .logStartupInfo(true)
                .bannerMode(Banner.Mode.LOG)
                .run(args);
    }

    @Bean
    public ObjectMapper scmsObjectMapper() {
        com.fasterxml.jackson.databind.ObjectMapper responseMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        return responseMapper;
    }
}