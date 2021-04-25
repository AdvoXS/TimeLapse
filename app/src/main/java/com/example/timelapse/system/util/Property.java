package com.example.timelapse.system.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {
    private static final Properties properties = new Properties();

    public static String getProperty(Context con, String name){
        loadProperties(con);
        return properties.getProperty(name);
    }

    private static void loadProperties(Context con){
        try {
            AssetManager assetManager = con.getAssets();
            InputStream inputStream = assetManager.open("app_properties.xml");
            properties.load(inputStream);
        }
        catch (IOException e) {
            Log.e("Properties", "Ошибка при открытии файла настроек");
        }
    }
}
