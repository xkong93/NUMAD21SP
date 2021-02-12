package edu.neu.madcourse.numad21sp_xuankong;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    List<String> links;
    Context context;

    public RecycleViewAdapter(List<String> links, Context context) {
        this.links = links;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.link.setText(links.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkClicked = links.get(position);
                if (!linkClicked.startsWith("https://") && !linkClicked.startsWith("http://"))
                    linkClicked = "https://" + linkClicked;
                Uri uri = Uri.parse(linkClicked);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView link;
        ConstraintLayout parentLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            link = itemView.findViewById(R.id.linkcard_link);
            parentLayout = itemView.findViewById(R.id.linkCard);
        }
    }
}
