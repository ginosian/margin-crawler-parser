package com.margin.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "organization")
@Getter
@Setter
public class NLPOrganization extends NLPEntity{
}
