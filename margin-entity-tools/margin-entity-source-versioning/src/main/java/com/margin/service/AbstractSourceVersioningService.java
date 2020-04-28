package com.margin.service;

import com.margin.model.AbstractModel;

import java.util.Optional;
import java.util.Set;

public interface AbstractSourceVersioningService {

    Optional<AbstractModel> find(String id);

    AbstractModel get(String id);

    Set<AbstractModel> findAll(AbstractModel request);

    <T extends AbstractModel> AbstractModel create(T request);
}
