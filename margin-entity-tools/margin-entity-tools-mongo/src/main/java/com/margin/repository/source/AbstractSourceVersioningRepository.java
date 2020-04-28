package com.margin.repository.source;

import com.margin.enums.Channel;
import com.margin.enums.Country;
import com.margin.model.AbstractModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface AbstractSourceVersioningRepository<T extends AbstractModel> extends MongoRepository<T, String>  {
    T findByCountryAndChannelOrderByVersionDesc(Country country, Channel channel);

    List<T> findAllByCountryAndChannelOrderByVersionDesc(Country country, Channel channel);

    List<T> findAllByVersion(Short version);

}
