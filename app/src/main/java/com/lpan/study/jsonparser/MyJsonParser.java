package com.lpan.study.jsonparser;

import android.text.TextUtils;

import com.bluelinelabs.logansquare.LoganSquare;
import com.bluelinelabs.logansquare.ParameterizedType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by lpan on 2017/11/16.
 */

public class MyJsonParser implements JsonParserI{

    private static final MyJsonParser sMyJsonParser = new MyJsonParser();

    private MyJsonParser() {

    }

    public static MyJsonParser getInstance() {
        return sMyJsonParser;
    }


    @Override
    public <T> T readValue(String content, Class<T> valueType) throws IOException {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        return LoganSquare.parse(content, valueType);
    }

    @Override
    public <T> T readValue(String content, ParameterizedType<T> jsonObjectTypee) throws IOException {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        return LoganSquare.parse(content, jsonObjectTypee);
    }

    @Override
    public String writeValueAsString(Object value) throws IOException {
        return LoganSquare.serialize(value);
    }

    @Override
    public <T> List<T> readArrayList(String content, Class<T> jsonObjectClass) throws IOException {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        return LoganSquare.parseList(content, jsonObjectClass);
    }

    @Override
    public <T> T readValue(InputStream inputStream, Class<T> valueType) throws IOException {
        if (inputStream == null) {
            return null;
        }
        return LoganSquare.parse(inputStream,valueType);
    }

}
