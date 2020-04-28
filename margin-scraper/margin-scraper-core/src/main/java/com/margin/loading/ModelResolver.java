package com.margin.loading;


import com.margin.model.AbstractSource;
import org.springframework.stereotype.Component;

@Component
public class ModelResolver {

    public AbstractSource resolve(final String modelClassName){
        final Class<?> clazz;
        try {
            clazz = Class.forName(modelClassName);
            return (AbstractSource) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
