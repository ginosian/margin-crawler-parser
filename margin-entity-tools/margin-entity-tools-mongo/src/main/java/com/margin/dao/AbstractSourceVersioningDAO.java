package com.margin.dao;

import com.margin.model.AbstractModel;

public interface AbstractSourceVersioningDAO {

    <T extends AbstractModel> void create(T request);
}
