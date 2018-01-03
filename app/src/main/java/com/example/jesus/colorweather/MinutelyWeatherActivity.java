package com.example.jesus.colorweather;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jesus.colorweather.Adapters.MinutelyWeatherAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MinutelyWeatherActivity extends Activity {

    @BindView(R.id.minutelyRecyclerView)
    RecyclerView minutelyRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minutely_weather);

        ButterKnife.bind(this);

        ArrayList<Minute> minutes = new ArrayList<>();

        Minute minute = new Minute();

        minute.setTitle("19.55");
        minute.setRainProbability("99%");

        minutes.add(minute);

        Minute minute2 = new Minute();

        minute2.setTitle("19.55");
        minute2.setRainProbability("99%");

        minutes.add(minute2);

        MinutelyWeatherAdapter minutelyWeatherAdapter = new MinutelyWeatherAdapter(this, minutes );

        minutelyRecyclerView.setAdapter(minutelyWeatherAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        minutelyRecyclerView.setLayoutManager(layoutManager);
        minutelyRecyclerView.setHasFixedSize(true);


    }
}
