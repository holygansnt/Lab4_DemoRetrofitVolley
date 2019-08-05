package com.example.demoretrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.demoretrofit.adapter.AdapterRCV;
import com.example.demoretrofit.model.Example;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Example> exampleList = new ArrayList<>();
    private RecyclerView rcvExample;
    private AdapterRCV adapterRCV;
    int curentPage;
    int lastVisibleItem, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        curentPage = 1;
        adapterRCV = new AdapterRCV(exampleList);
        rcvExample = (RecyclerView) findViewById(R.id.rcv_example);
        rcvExample.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcvExample.setAdapter(adapterRCV);
        demoRetrofit(curentPage);

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rcvExample.getLayoutManager();
        rcvExample.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (linearLayoutManager != null) {
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                }
                totalItemCount = Integer.parseInt(String.valueOf(rcvExample.getAdapter().getItemCount()));
                if (!rcvExample.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    curentPage++;
                    demoRetrofit(curentPage);

                }
            }
        });

    }

    public void demoRetrofit(int curentPage) {
        PolyRetrofit.getInstance().getPostOfCategory("18", "10", String.valueOf(curentPage))
                .enqueue(new Callback<List<Example>>() {
                    @Override
                    public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                        if (response.code() == 200 && response.body() != null) {
                            adapterRCV.updateData(response.body());
                        } else {
                            Toast.makeText(MainActivity.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Example>> call, Throwable t) {
                        Log.e("err", t.getMessage());
                    }
                });
    }
}
