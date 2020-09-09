package com.mcheru.leaderboard;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HourAdapter  extends RecyclerView.Adapter<HourAdapter.ViewHolder> {
    /**
     * UNIDENTIFIED BUG!!!
     */

    LayoutInflater inflater;
    List<Marathoner> marathoners;

    public HourAdapter(Context ctx, List<Marathoner> marathoners){
        this.inflater = LayoutInflater.from(ctx);
        this.marathoners = marathoners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_views2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.learnerNameTV.setText(marathoners.get(position).getName());
        String statsTxt = marathoners.get(position).getHours()+ " learning hours, "+ marathoners.get(position).getCountry();
        holder.learnerStatsTV.setText(statsTxt);
        Picasso.get().load(marathoners.get(position).getBadgeUrl()).into(holder.badgeImage);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder<learnerNameTV> extends RecyclerView.ViewHolder {


        TextView learnerNameTV, learnerStatsTV;
        ImageView badgeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            learnerNameTV = itemView.findViewById(R.id.learner_name2);
            learnerStatsTV = itemView.findViewById(R.id.learner_stats2);
            badgeImage = itemView.findViewById(R.id.imageView2);

            // handle onClick

            itemView.setOnClickListener(v -> Toast.makeText(v.getContext(), "Do Something With this Click", Toast.LENGTH_SHORT).show());
        }
    }

}


