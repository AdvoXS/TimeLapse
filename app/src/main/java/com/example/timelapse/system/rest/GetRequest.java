package com.example.timelapse.system.rest;

import android.util.Log;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;


public abstract class GetRequest<T> extends RestRequest<T> {
    private HttpStatus statusCode;

    @Override
    protected T run() {
        try {
            final String url = getPath();
            RestTemplate restTemplate = getRestTemplate(500);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            statusCode = restTemplate.getForEntity(url, getRestClazz()).getStatusCode();
            return restTemplate.getForObject(url, getRestClazz());
        } catch (ResourceAccessException e) {
            Log.e("ServerAccess", "Ошибка соединения с сервером", e);
        } catch (Exception e) {
            Log.e("RestAccess", e.getMessage(), e);
            Log.d("RestAccess", "Ошибка получения данных json " + getRestClazz().getSimpleName());
        }
        return null;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
