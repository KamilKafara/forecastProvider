package com.kafarson.forecast.api;

import java.io.IOException;

public interface FetchApi {
    String fetchData(String url) throws IOException;

    <T> T getDataByType(String url, Class<T> valueType) throws IOException;
}
