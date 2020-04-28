package com.margin.concerns;

import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConcernFactory {

    @Bean
    public WriteConcern writeConcern() {
        return WriteConcern.MAJORITY.withJournal(true);
    }

    @Bean
    public ReadPreference readPreference() {
        return ReadPreference.secondaryPreferred();
    }
}
