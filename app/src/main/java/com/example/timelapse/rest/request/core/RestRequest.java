package com.example.timelapse.rest.request.core;

import com.example.timelapse.rest.RestUtils;
import com.example.timelapse.system.util.thread.AsyncCallObject;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public abstract class RestRequest<T> extends AsyncCallObject<T> {

    private final String rootPath = "https://60912b6350c2550017677ba8.mockapi.io/tpp_api/v1";

    public String getSubPath() {
        return "";
    }

    public abstract Class<T> getRestClazz(); //{
    //return (Class) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    // return null;
    //  }

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
