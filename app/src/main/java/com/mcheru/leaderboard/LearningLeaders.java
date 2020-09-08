package com.mcheru.leaderboard;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class LearningLeaders extends Fragment {

    private LearningLeadersViewModel mViewModel;
    //private static String JSON_URL = "https://gadsapi.herokuapp.com/api/hours";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learning_leaders_fragment, container, false);
        parseMarathoner(ApiUtil.getFullUrlAsString("/api/hours"));
        return  view;
    }


//    private void extractSongs() {
//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject songObject = response.getJSONObject(i);
//
//                        Marathoner marathoner = new Marathoner();
//                        marathoner.setName(songObject.getString("name"));
//                        marathoner.setHours(songObject.getInt("hours"));
//                        marathoner.setCountry(songObject.getString("country"));
//                        marathoner.setBadgeUrl(songObject.getString("badgeUrl"));
//                        mMarathoners.add(marathoner);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
//                MarathonerAdapter adapter = new MarathonerAdapter();
//                recyclerView.setAdapter(adapter);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("tag", "onErrorResponse: " + error.getMessage());
//            }
//        });
//
//        queue.add(jsonArrayRequest);
//
//    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LearningLeadersViewModel.class);
        // TODO: Use the ViewModel
    }

    public void parseMarathoner(String JSON_URL){
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //getting the whole json object from the response
                            ArrayList<Marathoner> marathoners = null;
                            JSONArray marathonArray = new JSONArray(response);

                            //now looping through all the elements of the json array
                            for (int i = 0; i < marathonArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject hourObject = marathonArray.getJSONObject(i);

                                //creating a marathoner object and giving them the values from json object
                                Marathoner marathoner = new Marathoner(hourObject.getString("name"),
                                        hourObject.getInt("hours"),hourObject.getString("country"),
                                        hourObject.getString("badgeUrl"));

                                //adding the marathoner to herolist
                                marathoners.add(marathoner);
                            }
                            RecyclerView recyclerView = getView().findViewById(R.id.hour_recyclerview);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                                    HORIZONTAL, false));
                            AdapterHours adapter = new AdapterHours(getContext(),marathoners);
                            recyclerView.setAdapter(adapter );
//                            //creating custom adapter object
//                            ListViewAdapter adapter = new ListViewAdapter(heroList, getApplicationContext());
//
//                            //adding the adapter to listview
//                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley Error", "could'nt load json data at parseMarathoner method");
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);

//        marathoners = null;
//        try
//        {
//            JSONArray ja=new JSONArray(jsonData);
//            JSONObject jo;
//            Marathoner marathoner;
//
//            for (int i=0;i<ja.length();i++)
//            {
//                jo=ja.getJSONObject(i);
//
//                String name=jo.getString("name");
//                int hours =jo.getInt("hours");
//                String country =jo.getString("country");
//                String badgeUrl =jo.getString("badgeUrl");
//
//                marathoner =new Marathoner(name, hours, country, badgeUrl);
//
//                marathoners.add(marathoner);
//            }


//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
    }
}