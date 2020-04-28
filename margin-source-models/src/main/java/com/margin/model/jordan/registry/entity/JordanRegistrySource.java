package com.margin.model.jordan.registry.entity;

import com.margin.model.AbstractSource;
import com.margin.model.jordan.registry.JordanRegistryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jo_registry_source")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JordanRegistrySource extends AbstractSource {
    private JordanRegistryModel source;
}
