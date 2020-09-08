package com.mcheru.leaderboard;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager ourInstance = null;
    ArrayList<Genius> mGeniuses;
    ArrayList<Marathoner> marathoners;


    public static DataManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManager();
        }
        return ourInstance;
    }
    public String getRanking(String title){
        String result = null;
        try {
            URL url = ApiUtil.buildUrl(title);
            if (url != null) {
                result = ApiUtil.getJson(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }
    public String getGeniuses(){
        return getRanking("/api/skilliq");
    }
    public String getMarathoners(){
        return getRanking("/api/hours");
    }


    public ArrayList<Genius> parseGenius(String jsonData) {
        mGeniuses = null;
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo;
            Genius genius;

            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                String name=jo.getString("name");
                int score =jo.getInt("score");
                String country =jo.getString("country");
                String badgeUrl =jo.getString("badgeUrl");

                genius=new Genius();
                genius.setName(name);
                genius.setScore(score);
                genius.setCountry(country);
                genius.setBadgeUrl(badgeUrl);
                mGeniuses.add(genius);
            }

            return mGeniuses;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public ArrayList<Marathoner> parseMarathoner(String JSON_URL){
//        //creating a string request to send request to the url
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            //getting the whole json object from the response
//                            JSONArray marathonArray = new JSONArray(response);
//
//                            //now looping through all the elements of the json array
//                            for (int i = 0; i < marathonArray.length(); i++) {
//                                //getting the json object of the particular index inside the array
//                                JSONObject heroObject = marathonArray.getJSONObject(i);
//
//                                //creating a hero object and giving them the values from json object
//                                Marathoner hero = new Marathoner(heroObject.getString("name"),
//                                        heroObject.getInt("hours"),heroObject.getString("country"),
//                                        heroObject.getString("badgeUrl"));
//
//                                //adding the hero to herolist
//                                marathoners.add(hero);
//                            }
//
////                            //creating custom adapter object
////                            ListViewAdapter adapter = new ListViewAdapter(heroList, getApplicationContext());
////
////                            //adding the adapter to listview
////                            listView.setAdapter(adapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("Volley Error", "could'nt load json data at parseMarathoner method");
//                    }
//                });
//
//        //creating a request queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        //adding the string request to request queue
//        requestQueue.add(stringRequest);
//
////        marathoners = null;
////        try
////        {
////            JSONArray ja=new JSONArray(jsonData);
////            JSONObject jo;
////            Marathoner marathoner;
////
////            for (int i=0;i<ja.length();i++)
////            {
////                jo=ja.getJSONObject(i);
////
////                String name=jo.getString("name");
////                int hours =jo.getInt("hours");
////                String country =jo.getString("country");
////                String badgeUrl =jo.getString("badgeUrl");
////
////                marathoner =new Marathoner(name, hours, country, badgeUrl);
////
////                marathoners.add(marathoner);
////            }
//        return marathoners;
//
////        } catch (JSONException e) {
////            e.printStackTrace();
////            return null;
////        }
//    }

    public ArrayList<Genius> mGeniusArrayList = parseGenius(getGeniuses());
    //private ArrayList<Marathoner> mMarathonerArrayList = parseMarathoner(ApiUtil.getFullUrlAsString("/api/hours"));


}