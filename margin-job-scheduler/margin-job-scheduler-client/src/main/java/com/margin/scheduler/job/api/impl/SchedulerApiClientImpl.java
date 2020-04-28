package com.margin.scheduler.job.api.impl;

import com.margin.AbstractApiClient;
import com.margin.scheduler.job.api.SchedulerApiClient;
import com.margin.scheduler.job.api.SchedulerResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SchedulerApiClientImpl extends AbstractApiClient implements SchedulerApiClient {

    //TODO replace this to config files
    private static String BASE_URL = "http://localhost:8080/scheduler/v1";

    private SchedulerResource schedulerResource;

    public SchedulerApiClientImpl() {
        super(BASE_URL);
        this.schedulerResource = new SchedulerResource(getClient(), getWebTarget());
    }

    @Override
    public SchedulerResource scheduler() {
        return schedulerResource;
    }

    @Override
    public void close() throws IOException {

    }
}
