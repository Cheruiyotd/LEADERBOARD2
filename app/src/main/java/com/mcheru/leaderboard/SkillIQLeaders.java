package com.mcheru.leaderboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

public class SkillIQLeaders extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Genius> geniuses;
    private static String JSON_URL = "https://gadsapi.herokuapp.com/api/skilliq";

    public SkillIQLeaders() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        geniuses = new ArrayList<>();
        parseGenius("/api/skilliq");
    }
    public void parseGenius(String JSON_URL){
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //getting the whole json object from the response
                            ArrayList<Genius> geniuses = null;
                            JSONArray geniusArray = new JSONArray(response);

                            //now looping through all the elements of the json array
                            for (int i = 0; i < geniusArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject geniusObject = geniusArray.getJSONObject(i);

                                //creating a marathoner object and giving them the values from json object
                                Genius genius = new Genius(geniusObject.getString("name"),
                                        geniusObject.getInt("score"),geniusObject.getString("country"),
                                        geniusObject.getString("badgeUrl"));

                                //adding the marathoner to herolist
                                geniuses.add(genius);
                            }
                            RecyclerView recyclerView = getView().findViewById(R.id.hour_recyclerview);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                                    HORIZONTAL, false));
                            AdapterSkillIQ adapter = new AdapterSkillIQ(getContext(),geniuses);
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

//    private void extractSongs() {
//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject songObject = response.getJSONObject(i);
//
//                        Genius genius = new Genius();
//                        genius.setName(songObject.getString("name"));
//                        genius.setScore(songObject.getInt("score"));
//                        genius.setCountry(songObject.getString("country"));
//                        genius.setBadgeUrl(songObject.getString("badgeUrl"));
//                        geniuses.add(genius);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
//                adapterSkillIQ = new AdapterSkillIQ(getActivity().getApplicationContext(), geniuses);
//                recyclerView.setAdapter(adapterSkillIQ);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);
    }
}