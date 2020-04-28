package com.margin.loader.support;

import com.margin.dto.tmp.support.DatabaseDTO;
import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class OldDatabaseSupport {
    private final String DB_PREFIX = "entity_";
    private final String DEFAULT_IP_ADDRESS = "localhost";
    private final int DEFAULT_PORT = 27017;

    public List<DatabaseDTO> getDatabaseInfos(DatabaseDTO request) {
        MongoClient mongoClient = connect(request);
        // get dbNames and sort them
        List<DatabaseDTO> databases = new LinkedList<>();
        for (Document databaseDoc : mongoClient.listDatabases()) {
            String name = databaseDoc.getString("name");
            final DatabaseDTO databaseDTO = new DatabaseDTO();
            databaseDTO.setName(name);
            final MongoDatabase database = mongoClient.getDatabase(name);
            final MongoIterable<String> strings = database.listCollectionNames();
            final ArrayList<String> list = Lists.newArrayList(strings);
            databaseDTO.setCollections(list);
            databases.add(databaseDTO);
        }
        mongoClient.close();
        return databases;
    }

    public DatabaseDTO getFieldsFrom(DatabaseDTO request) {
        Assert.notNull(request.getName(), "database name can not be null");
        final MongoClient mongoClient = connect(request);
        final MongoDatabase database = mongoClient.getDatabase(request.getName());
        //@FIXME return right value
        return null;
    }

    private MongoClient connect(DatabaseDTO request) {
        Assert.notNull(request, "databaseDTO can not be null");
        if(request.getIpAddress() == null) request.setIpAddress(DEFAULT_IP_ADDRESS);
        if(request.getPort() == null) request.setPort(DEFAULT_PORT);
        String mongoUrl;
        if (request.getUserName() == null) {
            mongoUrl = String.format("mongodb://%s:%d",
                    request.getIpAddress(),
                    request.getPort());
        } else {
            Assert.notNull(request.getPassword(),"password can not be null");
            mongoUrl = String.format("mongodb://%s:%s@%s:%d",
                    request.getUserName(),
                    request.getPassword().toString(),
                    request.getIpAddress(),
                    request.getPort());
        }
        return new MongoClient(new MongoClientURI(mongoUrl));
    }



}
