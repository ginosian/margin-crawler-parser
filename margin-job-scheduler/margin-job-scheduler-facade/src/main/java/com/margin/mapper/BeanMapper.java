package com.margin.mapper;


import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BeanMapper extends ConfigurableMapper {
    private MapperFactory factory;

    @PostConstruct
    @Override
    public void init() {
        super.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(MapperFactory factory) {
        this.factory = factory;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureFactoryBuilder(final DefaultMapperFactory.Builder factoryBuilder) {
        // Nothing to configure for now
    }

    /**
     * Constructs and registers a {@link ClassMapBuilder} into the {@link MapperFactory} using a {@link Mapper}.
     *
     * @param mapper
     */
    @SuppressWarnings("rawtypes")
    public void addMapper(Mapper<?, ?> mapper) {
        factory.classMap(mapper.getAType(), mapper.getBType())
                .byDefault()
                .customize((Mapper) mapper)
                .register();
    }

}