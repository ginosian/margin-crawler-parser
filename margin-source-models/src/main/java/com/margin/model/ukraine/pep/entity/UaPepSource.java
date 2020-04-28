package com.margin.model.ukraine.pep.entity;

import com.margin.model.AbstractSource;
import com.margin.model.ukraine.pep.UaPepPersonModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entity_ua_pep")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UaPepSource extends AbstractSource {
    private UaPepPersonModel source;
}
