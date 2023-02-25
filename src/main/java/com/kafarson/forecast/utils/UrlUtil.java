package com.kafarson.forecast.utils;

import java.util.Map;

public class UrlUtil {
    private UrlUtil() {
    }

    public static String createQueryParam(Map<String, String> params) {
        StringBuilder qp = new StringBuilder();
        params.forEach((key, value) -> {
            if (qp.isEmpty()) {
                qp.append("?");
            } else {
                qp.append("&");
            }
            qp.append(key).append("=").append(value);
        });
        return qp.toString();
    }
}
