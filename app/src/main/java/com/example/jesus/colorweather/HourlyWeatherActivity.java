package com.example.jesus.colorweather;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jesus.colorweather.Adapters.HourlyWeatherAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

    public class HourlyWeatherActivity extends Activity {

        @BindView(R.id.hourlyTitleTextView)
        ListView hourlyListView;

        @BindView(R.id.hourlyNoDataTextView)
        TextView hourlyNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_weather);

        ButterKnife.bind(this);

        ArrayList<Hour> hours = getIntent().getParcelableArrayListExtra("hours");

        HourlyWeatherAdapter hourlyWeatherAdapter = new HourlyWeatherAdapter(this,hours);

        hourlyListView.setAdapter(hourlyWeatherAdapter);

        hourlyListView.setEmptyView(hourlyNoData);

    }
}
