package com.margin.service.impl;

import com.margin.enums.Channel;
import com.margin.enums.Country;
import com.margin.dao.AbstractSourceVersioningDAO;
import com.margin.model.AbstractModel;
import com.margin.repository.source.AbstractSourceVersioningRepository;
import com.margin.service.AbstractSourceVersioningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

@Service
public class AbstractSourceVersioningServiceImpl implements AbstractSourceVersioningService {
    private static final Logger logger = LoggerFactory.getLogger(AbstractSourceVersioningServiceImpl.class);

    @Autowired
    private AbstractSourceVersioningRepository abstractRepository;

    @Autowired
    private AbstractSourceVersioningDAO abstractDAO;

    @Override
    public Optional<AbstractModel> find(final String id) {
        System.out.println("id = [" + id + "]");
        hasText(id, "Id can not be null or empty");
        logger.trace("Finding AbstractModel by id: '{}'...", id);
        final Optional<AbstractModel> model = abstractRepository.findById(id);
        logger.debug("Done finding AbstractModel by id: '{}'.", id);
        return model;
    }

    @Override
    public AbstractModel get(final String id) {
        hasText(id, "Id can not be null or empty");
        logger.trace("Getting AbstractModel by id: '{}'...", id);
        final AbstractModel model = (AbstractModel) abstractRepository.findById(id).orElse(null);
        logger.debug("Done getting AbstractModel by id: '{}'.", id);
        return model;
    }

    @Override
    public Set<AbstractModel> findAll(final AbstractModel request) {
        notNull(request, "Request can not be null.");
        final Country country = request.getCountry();
        notNull(country, "Country can not be null.");
        final Channel channel = request.getChannel();
        notNull(channel, "Channel can not be null");
        final Integer version = request.getVersion();
        notNull(version, "Version can not be null");
        logger.trace(String.format(
                "Getting AbstractModels by country: '%s', channel: '%s' ordered by version: '%s'...",
                country, channel, version));
        final Set<AbstractModel> allModels = new HashSet<>(abstractRepository
                .findAllByCountryAndChannelOrderByVersionDesc(country, channel));
        logger.debug(String.format(
                "Done getting %d items of AbstractModel by country: '%s', channel: '%s' ordered by version: '%s'.",
                allModels.size(), country, channel, version));
        return allModels;
    }

    @Override
    public <T extends AbstractModel> AbstractModel create(final T request) {
        notNull(request, "Request can not be null.");
        final Country country = request.getCountry();
        notNull(country, "Country can not be null.");
        final Channel channel = request.getChannel();
        notNull(channel, "Channel can not be null");
        final Integer version = request.getVersion();
        notNull(version, "Version can not be null");
        logger.trace(String.format(
                "Storing AbstractModel by country: '%s', channel: '%s', version: '%s'...",
                country, channel, version));
        abstractDAO.create(request);
        logger.trace(String.format(
                "Done storing AbstractModel by country: '%s', channel: '%s', version: '%s' with id: '%s'.",
                country, channel, version, request.get_id()));
        return request;
    }
}
