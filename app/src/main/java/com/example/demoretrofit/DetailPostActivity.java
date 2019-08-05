package com.example.demoretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailPostActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("title"));
        textView4.setText("Status: " + intent.getStringExtra("status"));
        textView3.setText("Date GMT: " + intent.getStringExtra("dateGmt"));
        textView2.setText("Date: " + intent.getStringExtra("date"));
        textView5.setText("Author: " + intent.getStringExtra("author"));
        textView6.setText("Feature media: " + intent.getStringExtra("featured_media"));
        textView7.setText("Sticky: " + intent.getStringExtra("sticky"));

    }
}
