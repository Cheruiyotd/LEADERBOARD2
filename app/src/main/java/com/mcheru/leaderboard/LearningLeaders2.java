package com.mcheru.leaderboard;

import android.content.Context;
import android.os.Bundle;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearningLeaders2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearningLeaders2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Marathoner> marathoners = null;
    private static String JSON_URL = "https://gadsapi.herokuapp.com/api/hours";
    RecyclerView recyclerView = null;
    private Context mcontext;
    private SkillAdapter2 adapter;

    public LearningLeaders2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearningLeaders2.
     */
    // TODO: Rename and change types and number of parameters
    public static LearningLeaders2 newInstance(String param1, String param2) {
        LearningLeaders2 fragment = new LearningLeaders2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_learning_leaders2, container, false);

        mcontext = container.getContext();
        recyclerView = view.findViewById(R.id.skill_recycler2);
        marathoners = new ArrayList<>();
        extractSongs();

        return view;
    }


    private void extractSongs() {
        RequestQueue queue = Volley.newRequestQueue(mcontext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject songObject = response.getJSONObject(i);

                    Marathoner skillObject = new Marathoner();
                    skillObject.setName(songObject.getString("name"));
                    skillObject.setHours(songObject.getInt("hours"));
                    skillObject.setCountry(songObject.getString("country"));
                    skillObject.setBadgeUrl(songObject.getString("badgeUrl"));
                    marathoners.add(skillObject);

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