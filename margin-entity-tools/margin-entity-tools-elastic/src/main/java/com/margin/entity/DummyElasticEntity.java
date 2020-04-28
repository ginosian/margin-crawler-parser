package com.margin.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "dummy_index", type = "dummy")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DummyElasticEntity {

    @Id
    private String id;

    @Field
    private String title;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<String> props;
}

