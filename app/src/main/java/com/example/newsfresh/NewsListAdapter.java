package com.example.newsfresh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.interfaces.NewsClickInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>{
    private static final ArrayList<News> items = new ArrayList<News>();
    private static NewsClickInterface newsClickInterface = null;
    public NewsListAdapter(NewsClickInterface newsClickInterface){
        this.newsClickInterface = newsClickInterface;
    }
    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        public final TextView titleView;
        public final TextView authorName;
        public final ImageView image;

        public NewsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.titleView);
            authorName = (TextView) itemView.findViewById(R.id.authorName);
            image = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newsClickInterface.onItemClick(getAdapterPosition(),v,items.get(getAdapterPosition()));
                }
            });
        }

        public TextView getTitleView() {
            return titleView;
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getAuthorName() {
            return authorName;
        }
    }


    @NonNull
    @Override
    public  NewsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {  //returns Type of NewsViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new NewsViewHolder(view); //needs itemview; --> have to convert xml to view (itemview)--> layoutInflater;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NewsListAdapter.NewsViewHolder holder, int position) {
        holder.getTitleView().setText(items.get(position).title);
        holder.getAuthorName().setText(items.get(position).author);
        Glide.with(holder.itemView.getContext()).load(items.get(position).imageUrl).into(holder.image);
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updatenews(ArrayList<News> updatedNews){
        items.clear();
        items.addAll(updatedNews);
        //to notify adapter that data has been changed;
        notifyDataSetChanged();
    }
}

