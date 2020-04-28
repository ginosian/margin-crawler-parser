package com.margin.config;

import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class SchedulerConfig {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Bean
    public JobFactory jobFactory(final ApplicationContext applicationContext) {
        final AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean(name = "com.margin_scheduler")
    public Scheduler schedulerFactoryBean(final DataSource dataSource, final JobFactory jobFactory, ApplicationContext applicationContext) throws Exception {
        final SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // this allows to update triggers in DB when updating settings in config file
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        // use specify jobFactory to create jobDetail
        factory.setJobFactory(jobFactory);

        factory.setAutoStartup(true);
        factory.setApplicationContext(applicationContext);
        factory.setWaitForJobsToCompleteOnShutdown(true);

        factory.setQuartzProperties(properties());
        factory.afterPropertiesSet();

        final Scheduler scheduler = factory.getScheduler();
        scheduler.setJobFactory(jobFactory);
        scheduler.start();
        return scheduler;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        final PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }


    //TODO make it nice
    private Properties properties(){
        Properties props = new Properties();
        props.put("org.quartz.threadPool.class", org.quartz.simpl.SimpleThreadPool.class.getName());
        props.put("org.quartz.threadPool.threadCount", "25");
        props.put("org.quartz.threadPool.threadPriority", "5");
        // JobStore
        props.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        props.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");
        props.put("org.quartz.jobStore.useProperties", false);
        props.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        props.put("org.quartz.jobStore.isClustered", "true");
        props.put("org.quartz.jobStore.clusterCheckinInterval", "20000");
        props.put("org.quartz.jobStore.misfireThreshold", "60000");
        props.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "20");
        // DB
        String jdbcUrl = "jdbc:postgresql://localhost/job_scheduler_detail?currentSchema=public";
        props.put("org.quartz.dataSource.sched.driver", org.postgresql.Driver.class.getName());
        props.put("org.quartz.dataSource.sched.URL", jdbcUrl);
        props.put("org.quartz.dataSource.sched.user", "postgres");
        props.put("org.quartz.dataSource.sched.password", "postgres");
        props.put("org.quartz.dataSource.sched.validationQuery", "SELECT 1");
//        props.put("org.quartz.dataSource.sched.validateOnCheckout", false);
        return props;
    }


    @Bean
    public RabbitAdmin rabbitAdmin(){
        return new RabbitAdmin(rabbitTemplate);
    }

}
