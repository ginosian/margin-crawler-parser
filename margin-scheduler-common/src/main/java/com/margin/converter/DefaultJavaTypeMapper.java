package com.margin.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.util.ClassUtils;


/**
 * @author Mark Pollack
 * @author Sam Nelson
 * @author Andreas Asplund
 * @author Artem Bilan
 * @author Gary Russell
 */
public class DefaultJavaTypeMapper extends AbstractJavaTypeMapper implements JavaTypeMapper, ClassMapper {

    @Override
    public JavaType toJavaType(MessageProperties properties) {
        JavaType classType = getClassIdType(retrieveHeader(properties,
                getClassIdFieldName()));
        if (!classType.isContainerType() || classType.isArrayType()) {
            return classType;
        }

        JavaType contentClassType = getClassIdType(retrieveHeader(properties,
                getContentClassIdFieldName()));
        if (classType.getKeyType() == null) {
            return CollectionType.construct(
                    classType.getRawClass(),
                    contentClassType);
        }

        JavaType keyClassType = getClassIdType(retrieveHeader(properties,
                getKeyClassIdFieldName()));
        return MapType.construct(
                classType.getRawClass(), keyClassType,
                contentClassType);

    }

    private JavaType getClassIdType(String classId) {
        if (getIdClassMapping().containsKey(classId)) {
            return TypeFactory.defaultInstance().constructType(getIdClassMapping().get(classId));
        }

        try {
            return TypeFactory.defaultInstance().constructType(ClassUtils.forName(classId, getClassLoader()));
        }
        catch (ClassNotFoundException e) {
            throw new MessageConversionException(
                    "failed to resolve class name. Class not found [" + classId
                            + "]", e);
        }
        catch (LinkageError e) {
            throw new MessageConversionException(
                    "failed to resolve class name. Linkage error [" + classId
                            + "]", e);
        }
    }

    @Override
    public void fromJavaType(JavaType javaType, MessageProperties properties) {
        addHeader(properties, getClassIdFieldName(),
                javaType.getRawClass());

        if (javaType.isContainerType() && !javaType.isArrayType()) {
            addHeader(properties, getContentClassIdFieldName(), javaType
                    .getContentType().getRawClass());
        }

        if (javaType.getKeyType() != null) {
            addHeader(properties, getKeyClassIdFieldName(), javaType
                    .getKeyType().getRawClass());
        }
    }

    @Override
    public void fromClass(Class<?> clazz, MessageProperties properties) {
        fromJavaType(TypeFactory.defaultInstance().constructType(clazz), properties);

    }

    @Override
    public Class<?> toClass(MessageProperties properties) {
        return toJavaType(properties).getRawClass();
    }

}
