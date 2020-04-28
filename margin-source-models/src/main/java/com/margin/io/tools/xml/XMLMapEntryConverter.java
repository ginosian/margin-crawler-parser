package com.margin.io.tools.xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Component
public class XMLMapEntryConverter  implements Converter {
    public boolean canConvert(Class clazz) {
        return AbstractMap.class.isAssignableFrom(clazz);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        AbstractMap<String,String> map = (AbstractMap<String,String>) value;
        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String key, String value) {
                writer.startNode(key);
                writer.setValue(value);
                writer.endNode();
            }
        });
        System.out.println();
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Map<String, String> map = new HashMap<>();

        while (reader.hasMoreChildren()) {
            reader.moveDown();
            map.put(reader.getNodeName(), reader.getValue());
            reader.moveUp();
        }
        return map;
    }
}
