package com.example.jesus.colorweather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private TextView dailyWeatherTextView;
    private TextView hourlyWeatherTextView;
    private TextView minutelyWeatherTextView;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dailyWeatherTextView = findViewById(R.id.dailyWeatherTextView);
        hourlyWeatherTextView =  findViewById(R.id.hourlyWeatherTextView);
        minutelyWeatherTextView = findViewById(R.id.minutelyWeatherTextView);

        dailyWeatherTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dailyActivityIntent =  new Intent(MainActivity.this, DailyWeatherActivity.class);

                startActivity(dailyActivityIntent);

            }
        });



    }
}
