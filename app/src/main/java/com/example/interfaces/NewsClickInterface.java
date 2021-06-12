package com.example.interfaces;

import android.view.View;

import com.example.newsfresh.News;

public interface NewsClickInterface {
    void onItemClick(int position, View v, News item);
    void onLongItemClick(int position, View v,News item);
}
