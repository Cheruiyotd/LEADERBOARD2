package com.mcheru.leaderboard;

import android.content.Context;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mcheru.leaderboard.rankingutyls.Genius;
import com.mcheru.leaderboard.rankingutyls.SkillAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SkillIQLeaders extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Genius> geniuses = null;
    private static String JSON_URL = "https://gadsapi.herokuapp.com/api/skilliq";
    RecyclerView recyclerView = null;
    private Context mcontext;
    private SkillAdapter adapter;

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

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);
        mcontext = container.getContext();
        recyclerView = view.findViewById(R.id.skill_recycler);
        geniuses = new ArrayList<>();
        extractSongs();

        return view;
    }


    private void extractSongs() {
        RequestQueue queue = Volley.newRequestQueue(mcontext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject songObject = response.getJSONObject(i);

                    Genius skillObject = new Genius();
                    skillObject.setName(songObject.getString("name"));
                    skillObject.setScore(songObject.getInt("score"));
                    skillObject.setCountry(songObject.getString("country"));
                    skillObject.setBadgeUrl(songObject.getString("badgeUrl"));
                    geniuses.add(skillObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
            adapter = new SkillAdapter(mcontext, geniuses);
            recyclerView.setAdapter(adapter);
        }, error -> Log.d("tag", "onErrorResponse: " + error.getMessage()));

        queue.add(jsonArrayRequest);

    }
}

