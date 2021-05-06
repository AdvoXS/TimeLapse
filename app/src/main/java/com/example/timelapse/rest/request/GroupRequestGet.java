package com.example.timelapse.rest.request;

import com.example.timelapse.object.template.Group;
import com.example.timelapse.rest.request.core.GetRequest;

public class GroupRequestGet extends GetRequest<Group[]> {
    @Override
    public Class<Group[]> getRestClazz() {
        return Group[].class;
    }

    @Override
    public String getSubPath() {
        return "/group";
    }
}
