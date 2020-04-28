package com.margin.mapper;


import com.margin.entity.FailedScrapedUnitInfoEntity;
import com.margin.entity.ScrapedUnitInfoEntity;
import com.margin.service.model.failed_scraped_unit_info.FailedScrapedUnitInfoResponse;
import com.margin.service.model.scraped_unit_info.ScrapedUnitInfoResponse;
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

        factory.classMap(ScrapedUnitInfoEntity.class, ScrapedUnitInfoResponse.class)
                .constructorA()
                .constructorB()
                .exclude("scrapedSourceInfoId")
                .byDefault()
                .register();

        factory.classMap(FailedScrapedUnitInfoEntity.class, FailedScrapedUnitInfoResponse.class)
                .constructorA()
                .constructorB()
                .exclude("sourceMetaData")
                .byDefault()
                .register();
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