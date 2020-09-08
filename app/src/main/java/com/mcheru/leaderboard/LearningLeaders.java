package com.mcheru.leaderboard;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LearningLeaders extends Fragment {

    public  Context context = null;
    private LearningLeadersViewModel mViewModel;
    //private static String JSON_URL = "https://gadsapi.herokuapp.com/api/hours";
    RecyclerView recyclerView;
    private ArrayList<Marathoner> marathoners;
    private static String JSON_URL = ApiUtil.getFullUrlAsString("/api/hours");
    private HourAdapter adapter;
    private Context mcontext;

    public LearningLeaders(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learning_leaders_fragment, container, false);

        recyclerView = view.findViewById(R.id.hour_recyclerview);

        mcontext = container.getContext();
        recyclerView = view.findViewById(R.id.skill_recycler);
        marathoners = new ArrayList<>();
        //extractSongs();

        return view;
    }


//    private void extractSongs() {
//        RequestQueue queue = Volley.newRequestQueue(mcontext);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject songObject = response.getJSONObject(i);
//
//                        Marathoner skillObject = new Marathoner();
//                        skillObject.setName(songObject.getString("name"));
//                        skillObject.setHours(songObject.getInt("hours"));
//                        skillObject.setCountry(songObject.getString("country"));
//                        skillObject.setBadgeUrl(songObject.getString("badgeUrl"));
//                        marathoners.add(skillObject);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
//                adapter = new HourAdapter(mcontext, marathoners);
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
}