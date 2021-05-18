package com.example.timelapse.rest.get;

import com.example.timelapse.object.template.Group;
import com.example.timelapse.system.rest.GetRequest;

public class GroupRequestGet extends GetRequest<Group[]> {
    @Override
    public String getSubPath() {
        return "group";
    }
}
