package com.example.recyleviewjsonvolleyexample;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecycleViewAdapter.OnItemClickListener {

    public static final String EXTRA_URL="imageUrl";
    public static final String EXTRA_CREATOR="creatorName";
    public static final String EXTRA_LIKE="likeCount";
    private RecyclerView mRecyclerView;
    private RecycleViewAdapter mRecycleViewAdapter;
    private ArrayList<CardViewItem> mCardViewItemList;
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyleView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCardViewItemList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJson();
    }

    private void parseJson(){
        String url="https://pixabay.com/api/?key=12119031-ba8bae2542302b76501ba0307&q=yellow+flowers&image_type=photo&pretty=true";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");

                    for(int i = 0;i<jsonArray.length();i++){
                        JSONObject hit = jsonArray.getJSONObject(i);

                        String creatorName = hit.getString("user");
                        String imageUrl = hit.getString("webformatURL");
                        int likes = hit.getInt("likes");

                        mCardViewItemList.add(new CardViewItem(imageUrl,creatorName,likes));

                    }

                    mRecycleViewAdapter = new RecycleViewAdapter(MainActivity.this,mCardViewItemList);
                    mRecyclerView.setAdapter(mRecycleViewAdapter);
                    mRecycleViewAdapter.setOnItemClickListener(MainActivity.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        mRequestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,DetailActivity.class);
        CardViewItem cardViewItemClicked = mCardViewItemList.get(position);

        intent.putExtra(EXTRA_URL,cardViewItemClicked.getmImageUrl());
        intent.putExtra(EXTRA_CREATOR,cardViewItemClicked.getmCreator());
        intent.putExtra(EXTRA_LIKE,cardViewItemClicked.getLikeCount());

        startActivity(intent);
    }
}
