package com.example.timelapse.rest.request;

import com.example.timelapse.object.template.Group;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static java.lang.System.currentTimeMillis;

public class GroupRequestGetTest {
    @Test
    public void test() {

        new GroupRequestGet() {
            private long startTime;

            @Override
            public Group[] execute() {
                startTime = currentTimeMillis();
                return super.execute();
            }

            @Override
            protected void postExecute(Group[] object) {
                long endTime = currentTimeMillis();
                String expectedJson = "[{\"id\":\"1\",\"name\":\"name 1\",\"stage\":11}]";
                String json = new Gson().toJson(object);
                Assert.assertTrue((endTime - startTime) < 5000);
                Assert.assertEquals(getStatusCode(), HttpStatus.OK);
                Assert.assertEquals(expectedJson, json);
                super.postExecute(object);
            }
        }.execute();
    }
}