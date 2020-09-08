package com.mcheru.leaderboard;

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

public class MarathonerAdapter extends RecyclerView.Adapter<MarathonerAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Marathoner> mMarathoners;

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView learnerName, learnerStats;
        ImageView badgeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            learnerName = itemView.findViewById(R.id.learner_name);
            learnerStats = itemView.findViewById(R.id.learner_stats);
            badgeImage = itemView.findViewById(R.id.imageView);
            // handle onClick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Do Something With this Click", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = inflater.inflate(R.layout.recycler_views, viewGroup,false);
            return new ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.learnerName.setText(mMarathoners.get(position).getName());
        holder.learnerStats.setText(mMarathoners.get(position).getHours()+ " learning hours, "+ mMarathoners.get(position).getCountry());
        Picasso.get().load(mMarathoners.get(position).getBadgeUrl()).into(holder.badgeImage);

    }

    @Override
    public int getItemCount() {
        return mMarathoners.size();
    }
}



