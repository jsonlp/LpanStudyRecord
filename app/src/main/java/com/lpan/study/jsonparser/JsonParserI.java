package com.lpan.study.jsonparser;

import com.bluelinelabs.logansquare.ParameterizedType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by lpan on 2017/10/27.
 */

public interface JsonParserI {

    <T> T readValue(String content, Class<T> valueType) throws IOException;

    <T> T readValue(String content, ParameterizedType<T> jsonObjectTypee) throws IOException;

    String writeValueAsString(Object value) throws IOException;

    <T> List<T> readArrayList(String content, Class<T> jsonObjectClass) throws IOException;

    <T> T readValue(InputStream inputStream, Class<T> valueType) throws IOException;

}
