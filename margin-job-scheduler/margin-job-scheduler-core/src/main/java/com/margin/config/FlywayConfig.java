package com.margin.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;


@Configuration
public class FlywayConfig {

    private DataSource dataSource;

    @Autowired
    public FlywayConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println();
    }


    //    @Bean
//    public FlywayMigrationStrategy cleanMigrateStrategy() {
//        FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
//            @Override
//            public void migrate(Flyway flyway) {
//                flyway.setIgnoreMissingMigrations(true);
//                flyway.setValidateOnMigrate(false);
//                flyway.repair();
//                flyway.migrate();
//            }
//        };
//
//        return strategy;
//    }
//
    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.repair();
        flyway.setIgnoreMissingMigrations(true);
        flyway.setValidateOnMigrate(false);
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations(String.format("classpath:/db/migration"));
        return flyway;
    }

    @Bean
    FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway, (f) -> {
        });
    }

    @Bean
    @DependsOn("entityManagerFactory")
    FlywayMigrationInitializer delayedFlywayInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway);
    }
}
