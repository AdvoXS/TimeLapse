package com.example.timelapse.rest.request.core;

import com.example.timelapse.rest.RestUtils;
import com.example.timelapse.system.util.thread.AsyncCallObject;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;

public abstract class RestRequest<T> extends AsyncCallObject<T> {

    private final String rootPath = "https://60a291287c6e8b0017e25bce.mockapi.io/calendar";

    public String getSubPath() {
        return "";
    }

    public Class<T> getRestClazz() {
        return ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected String getPath() {
        return rootPath + "/" + RestUtils.getPath(getSubPath());
    }

    protected RestTemplate getRestTemplate(int timeout) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(timeout);
        factory.setReadTimeout(timeout);

        return new RestTemplate(factory);
    }
}
