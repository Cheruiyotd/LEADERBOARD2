package com.mcheru.leaderboard;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LearningLeaders extends Fragment {

    //public  Context context = null;
    private LearningLeadersViewModel mViewModel;
    private static String JSON_URL = "https://gadsapi.herokuapp.com/api/hours";
    private Context mcontext;
    private String mParam1;
    private String mParam2;
    List<Marathoner> marathoners = null;
    RecyclerView recyclerView = null;
    private SkillAdapter2 adapter;

    public LearningLeaders(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learning_leaders_fragment, container, false);

        //View view = inflater.inflate(R.layout.fragment_learning_leaders2, container, false);

        mcontext = container.getContext();
        recyclerView = view.findViewById(R.id.hour_recyclerview);
        marathoners = new ArrayList<>();
        extractHourRanks();

        return view;
    }


    private void extractHourRanks() {
        RequestQueue queue = Volley.newRequestQueue(mcontext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);

                    Marathoner marathoner = new Marathoner();
                    marathoner.setName(jsonObject.getString("name"));
                    marathoner.setHours(jsonObject.getInt("hours"));
                    marathoner.setCountry(jsonObject.getString("country"));
                    marathoner.setBadgeUrl(jsonObject.getString("badgeUrl"));
                    marathoners.add(marathoner);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
            adapter = new SkillAdapter2(mcontext, marathoners);
            recyclerView.setAdapter(adapter);
        }, error -> Log.d("tag", "onErrorResponse: " + error.getMessage()));

        queue.add(jsonArrayRequest);

    }
}