package com.silverrailtech.ninja.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.silverrailtech.ninja.entity.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Alexander Latysh
 * @version 1.0
 * @since 2015-08-26
 */

public class TestUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
    }

    private TestUtil() {
    }

    public static <T> T readValue(String jsonString, Class<T> tClass) throws IOException {
        return mapper.readValue(jsonString, tClass);
    }

    public static <T> T readResponseValue(String jsonString, Class<?> target) throws IOException, ClassNotFoundException {
        return mapper.readValue(jsonString, mapper.getTypeFactory().constructParametricType(Response.class, Class.forName(target.getName())));
    }

    public static String getJsonStringFrom(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static byte[] convertToJsonBytes(Object object) throws JsonProcessingException {
        return mapper.writeValueAsBytes(object);
    }

    public static <T> List<T> readListValue(String jsonString, Class<T> tClass) throws IOException {
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, tClass);

        return mapper.readValue(jsonString, collectionType);
    }
}
