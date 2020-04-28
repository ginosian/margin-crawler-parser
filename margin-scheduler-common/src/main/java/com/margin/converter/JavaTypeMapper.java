package com.margin.converter;

import com.fasterxml.jackson.databind.JavaType;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.ClassMapper;

public interface JavaTypeMapper extends ClassMapper {

    void fromJavaType(JavaType javaType, MessageProperties properties);

    JavaType toJavaType(MessageProperties properties);

}
