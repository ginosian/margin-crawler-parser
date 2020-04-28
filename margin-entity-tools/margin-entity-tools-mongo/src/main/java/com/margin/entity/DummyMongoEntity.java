package com.margin.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "dummy")
@Getter
@Setter
public class DummyMongoEntity {
    @Id
    private String _id;

    @Field(value = "name")
    private Long name;
}
