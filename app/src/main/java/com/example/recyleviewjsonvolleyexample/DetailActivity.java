package com.example.recyleviewjsonvolleyexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String imageUrl = intent.getStringExtra(MainActivity.EXTRA_URL);
        String creatorName = intent.getStringExtra(MainActivity.EXTRA_CREATOR);
        String likeCount=intent.getStringExtra(MainActivity.EXTRA_LIKE);

        ImageView imageViewDetails = findViewById(R.id.imageViewDetails);
        TextView textViewCreatorName = findViewById(R.id.textViewCreatorDetail);
        TextView textViewLikeCount = findViewById(R.id.textViewLikeDetails);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageViewDetails);
        textViewCreatorName.setText(creatorName);
        textViewLikeCount.setText("likes"+likeCount);


    }
}
