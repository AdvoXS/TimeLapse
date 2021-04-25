package com.example.timelapse.rest.request;

import com.example.timelapse.rest.RestUtils;
import com.example.timelapse.system.util.StringUtils;
import com.example.timelapse.system.util.thread.AsyncCallObject;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public abstract class RestRequest<T> extends AsyncCallObject<T> {

    private final String rootPath = "http://192.168.0.103:8083";

    public String getSubPath() {
        return "";
    }

    public abstract Class<T> getRestClazz();

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
