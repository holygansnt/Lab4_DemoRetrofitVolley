package com.example.demoretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.widget.Button;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.demoretrofit.adapter.AdapterRCV;
import com.example.demoretrofit.model.Example;
import com.example.demoretrofit.model.Title;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class VolleyActivity extends AppCompatActivity {
    private Button btnvolley;
    private List<Example> exampleList = new ArrayList<>();
    private RecyclerView rcvVolley;
    private AdapterRCV adapterRCV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        rcvVolley = findViewById(R.id.rcv_volley);

        adapterRCV = new AdapterRCV(exampleList);
        rcvVolley.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcvVolley.setAdapter(adapterRCV);
        loadDatawithVolley();
    }

    public void loadDatawithVolley() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://asian.dotplays.com/wp-json/wp/v2/posts?category=18&per_page=20&paging=1";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("data1", response);
                        JsonParser jsonParser = new JsonParser();
                        JsonArray root = jsonParser.parse(response).getAsJsonArray();
                        for (int i = 0; i < root.size(); i++) {
                            JsonObject object = root.get(i).getAsJsonObject();
                            Log.d("object1", object.toString());


                            JsonObject objectTitle = object.getAsJsonObject("title");
                            String title = objectTitle.get("rendered").getAsString();

                            Example example = new Example();

                            example.setId(object.get("id").getAsInt());
                            Title title1 = new Title();
                            title1.setRendered(title);
                            example.setTitle(title1);
                            example.setLink(object.get("link").getAsString());
                            example.setStatus(object.get("status").getAsString());
                            example.setType(object.get("type").getAsString());
                            example.setDate(object.get("date").getAsString());
                            example.setDateGmt(object.get("date_gmt").getAsString());
                            example.setAuthor(object.get("author").getAsInt());
                            example.setSticky(object.get("sticky").getAsBoolean());
                            example.setFeaturedMedia(object.get("featured_media").getAsInt());
                            exampleList.add(example);
                        }
                        adapterRCV.updateData(exampleList);
                        Log.d("listvolley", exampleList.size() + "");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
