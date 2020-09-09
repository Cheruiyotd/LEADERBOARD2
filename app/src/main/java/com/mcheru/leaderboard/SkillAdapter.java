package com.mcheru.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Genius> skillObjects;

    public SkillAdapter(Context ctx, List<Genius> skillObjects){
        this.inflater = LayoutInflater.from(ctx);
        this.skillObjects = skillObjects;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_views,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        // bind the data
        holder.learnerNameTV.setText(skillObjects.get(position).getName());
        holder.learnerStatsTV.setText(skillObjects.get(position).getScore()+ " skill IQ Score, "+skillObjects.get(position).getCountry());
        Picasso.get().load(skillObjects.get(position).getBadgeUrl()).into(holder.badgeImage);

    }

    @Override
    public int getItemCount() {
        return skillObjects.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView learnerNameTV, learnerStatsTV;
        ImageView badgeImage;

        public ViewHolder( View itemView) {
            super(itemView);

            learnerNameTV = itemView.findViewById(R.id.learner_name);
            learnerStatsTV = itemView.findViewById(R.id.learner_stats);
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
