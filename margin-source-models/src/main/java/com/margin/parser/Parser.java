package com.margin.parser;

import com.margin.model.AbstractModel;

public interface Parser<Source extends GenericParsingSource, Model extends AbstractModel> {
    Model parse(Source source);
}
