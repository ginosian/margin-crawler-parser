package com.margin.dao.impl;

import com.margin.dao.AbstractSourceVersioningDAO;
import com.margin.model.AbstractModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AbstractSourceVersioningImplDAO implements AbstractSourceVersioningDAO {

    @Autowired(required = true)
    private MongoTemplate mongoTemplate;

    public <T extends AbstractModel> void create(T request){
        mongoTemplate.save(request, request.getChannel().name());
    }

}
