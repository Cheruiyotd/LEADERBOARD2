package com.mcheru.leaderboard;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiUtil {
    private ApiUtil() { }
    public static String BASE_API_URL = "https://gadsapi.herokuapp.com";

    public static URL buildUrl(String title){
        String fullUrl = BASE_API_URL + title;
        URL url = null;
        try {
           return new URL(fullUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFullUrlAsString(String s) {
        return BASE_API_URL+s;
    }
}

