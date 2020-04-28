package com.margin.repository;

import com.margin.entity.NLPPerson;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NLPPersonRepository extends MongoRepository<NLPPerson, String> {
}
