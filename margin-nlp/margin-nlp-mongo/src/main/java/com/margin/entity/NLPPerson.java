package com.margin.entity;

import com.margin.enums.Gender;
import com.margin.enums.NameType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "person")
@Getter
@Setter
public class NLPPerson extends NLPEntity{

    @Field(value = "gender")
    private Gender gender;

    @Field(value = "name_type")
    private NameType nameType;
}
