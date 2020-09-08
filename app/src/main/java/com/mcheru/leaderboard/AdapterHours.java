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

public class AdapterHours  extends RecyclerView.Adapter<AdapterHours.ViewHolder> {
        LayoutInflater inflater;
        List<Marathoner> marathoners;

        public AdapterHours(Context ctx, List<Marathoner> marathoners){
            this.inflater = LayoutInflater.from(ctx);
            this.marathoners = marathoners;
        }




        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.recycler_views,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            // bind the data
            holder.learnerName.setText(marathoners.get(position).getName());
            holder.learnerStats.setText(marathoners.get(position).getHours()+ " learning hours, "+ marathoners.get(position).getCountry());
            Picasso.get().load(marathoners.get(position).getBadgeUrl()).into(holder.badgeImage);

        }

        @Override
        public int getItemCount() {
            return marathoners.size();
        }

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
    }
