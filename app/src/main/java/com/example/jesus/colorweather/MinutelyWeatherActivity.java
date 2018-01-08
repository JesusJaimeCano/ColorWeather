package com.example.jesus.colorweather;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.example.jesus.colorweather.Adapters.MinutelyWeatherAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MinutelyWeatherActivity extends Activity {

    @BindView(R.id.minutelyRecyclerView)
    RecyclerView minutelyRecyclerView;
    @BindView(R.id.minutelyNoDataTextView)
    TextView minutelyNoDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minutely_weather);

        ButterKnife.bind(this);

        ArrayList<Minute> minutes;

        minutes = getIntent().getParcelableArrayListExtra("minutes");


        if (minutes != null && !minutes.isEmpty()){

            MinutelyWeatherAdapter minutelyWeatherAdapter = new MinutelyWeatherAdapter(this, minutes );

            minutelyRecyclerView.setAdapter(minutelyWeatherAdapter);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

            minutelyRecyclerView.setLayoutManager(layoutManager);
            minutelyRecyclerView.setHasFixedSize(true);
            minutelyNoDataTextView.setVisibility(View.GONE);

        }else{

            minutelyRecyclerView.setVisibility(View.GONE);
            minutelyNoDataTextView.setVisibility(View.VISIBLE);

        }





    }
}
