package com.margin.converter;
/*
 *   @author ironman
 *   @since  11/21/18
 */

import com.margin.model.AbstractModel;
import com.margin.parser.GenericParsingSource;

import java.util.Map;

public interface Converter <Model extends AbstractModel, T> {

    T convert(Model model);

}
