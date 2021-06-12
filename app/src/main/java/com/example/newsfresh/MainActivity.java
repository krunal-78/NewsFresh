package com.example.newsfresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.interfaces.NewsClickInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsClickInterface {
    RecyclerView recyclerView;
    private NewsListAdapter newsListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchData();
        newsListAdapter = new NewsListAdapter(this);
//        newsListAdapter.setOnItemClickListener(new NewsListAdapter.ClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
////                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
////                CustomTabsIntent customTabsIntent = builder.build();
////                customTabsIntent.launchUrl(MainActivity.this,Uri.parse(item.url));
////                Log.d("Onclick","Item Clicked "+position);
//                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onItemLongClick(int position, View v) {
////                Toast.makeText(MainActivity.this, "OnLongitemclick : " + position, Toast.LENGTH_SHORT).show();
//            }
//        });

        recyclerView.setAdapter(newsListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }


    private void fetchData() {
        String url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";


// Formulate the request and handle the response.

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //parsing of data;
                            JSONArray newsJsonArray = response.getJSONArray("articles");
                            ArrayList<News> newsArrayList = new ArrayList<News>();
                            for (int i = 0; i < newsJsonArray.length(); i++) {
                                JSONObject newsJsonObject = newsJsonArray.getJSONObject(i);
                                String Title = newsJsonObject.getString("title");
                                String Author = newsJsonObject.getString("author");
                                String Url = newsJsonObject.getString("url");
                                String ImageUrl = newsJsonObject.getString("urlToImage");
                                News news = new News(Title, Author, Url, ImageUrl);
                                newsArrayList.add(news);
                            }
                            newsListAdapter.updatenews(newsArrayList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onItemClick(int position, View v, News item) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(MainActivity.this,Uri.parse(item.url));
//                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                Log.d("Onclick","Item Clicked "+position);
    }

    @Override
    public void onLongItemClick(int position, View v, News item) {

    }
}