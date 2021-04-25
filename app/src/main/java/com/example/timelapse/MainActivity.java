package com.example.timelapse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.timelapse.rest.object.template.TimeTable;
import com.example.timelapse.rest.request.TimeTablesRequestGet;
import com.example.timelapse.system.util.thread.AsyncCallObject;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new TimeTablesRequestGet().execute();
    }

    /*private class RequestTask extends AsyncCallObject<TimeTable[]> {
        @Override
        protected TimeTable[] run() {
            try {
                final String url = "http://192.168.0.103:8083/";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                TimeTable[] timeTable = restTemplate.getForObject(url, TimeTable[].class);
                return timeTable;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        public void postExecute(TimeTable[] timeTable) {
            TextView greetingIdText = findViewById(R.id.id_value);
            TextView greetingContentText = findViewById(R.id.content_value);
            greetingIdText.setText(timeTable[0].getId().toString());
            greetingContentText.setText(timeTable[0].getStatus());
        }

    }*/
}