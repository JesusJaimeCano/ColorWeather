package com.example.jesus.colorweather;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {


    public static final String DATA = "data";
    public static final String SUMMARY = "summary";
    public static final String TIME = "time";
    public static final String PRECIP_PROBABILITY = "precipProbability";
    public static final String CURRENTLY = "currently";
    public static final String DAILY = "daily";
    public static final String ICON = "icon";
    public static final String TEMPERATURE = "temperature";
    public static final String TEMPERATURE_MAX = "temperatureMax";
    public static final String TEMPERATURE_MIN = "temperatureMin";
    public static final String HOURLY = "hourly";
    public static final String TIMEZONE = "timezone";
    @BindView(R.id.iconImageView)
    ImageView iconImageView;
    @BindView(R.id.descriptionTextView)
    TextView descriptionTextView;
    @BindView(R.id.currentTempTextView)
    TextView currentTempTextView;
    @BindView(R.id.highestTempTextView)
    TextView highestTempTextView;
    @BindView(R.id.lowestTempTextView)
    TextView lowestTempTextView;

    @BindDrawable(R.drawable.clear_night)
    Drawable clearnight;

    private static final String TAG = "MyActivity";
    ArrayList<Day> days;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        /*CurrentWeather currentWeather = new CurrentWeather(MainActivity.this);

        currentWeather.setIconImage("sunny");
        currentWeather.setDescription("SunnyDay");
        currentWeather.setCurrentTemperature("24");
        currentWeather.setHighestTemperature("H: 25");
        currentWeather.setLowestTemperature("L: 10");

        iconImageView.setImageDrawable(currentWeather.getIconDrawableResource());
        descriptionTextView.setText(currentWeather.getDescription());
        currentTempTextView.setText(currentWeather.getCurrentTemperature());
        highestTempTextView.setText(currentWeather.getHighestTemperature());
        lowestTempTextView.setText(currentWeather.getLowestTemperature());
        */


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.darksky.net/forecast/ceb0ced205a989736a845dcdb873d4d1/37.8267,-122.4233?units=si";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            CurrentWeather currentWeather = getCurrentWeatherFromJson(response);
///////////////////// DailyWeather/*
                            days = getDailyWeatherFromJson(response);

                            for (Day day : days) {

                                /*Log.d("DayName" , day.getDayname());
                                Log.d("Summary" , day.getWeatherDescription());
                                Log.d("RainProbability" , day.getRainProbability());*/

                            }
////////////////////

////////////////////Hourly Weather

                            ArrayList<Hour> hours = getHourlyWeatherFromJson(response);

                            /*for (Hour hour : hours) {

                                Log.d("HourTitle" , hour.getTitle());
                                Log.d("HourlyDescription" , hour.getWeatherDescription());

                            }*/


//////////////////////

////////////////////Minutely Weather

                            ArrayList<Minute> minutes = getMinutelyWeatherFromJson(response);

                            for (Minute minute : minutes) {
                                Log.d("Minute" , minute.getTitle());
                                Log.d("Rain Probability" , minute.getRainProbability());
                            }
//////////////////////
                            iconImageView.setImageDrawable(currentWeather.getIconDrawableResource());
                            descriptionTextView.setText(currentWeather.getDescription());
                            currentTempTextView.setText(currentWeather.getCurrentTemperature());
                            highestTempTextView.setText(String.format("H: %s",currentWeather.getHighestTemperature()));
                            lowestTempTextView.setText(String.format("L: %s",currentWeather.getLowestTemperature()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    @OnClick(R.id.dailyWeatherTextView)
    public void dailyWeatherClick() {

        Intent dailyActivityIntent = new Intent(MainActivity.this, DailyWeatherActivity.class);
        dailyActivityIntent.putParcelableArrayListExtra("days", days);
        startActivity(dailyActivityIntent);
    }

    @OnClick(R.id.hourlyWeatherTextView)
    public void hourlyWeatherClick() {
        Intent hourlyActivityIntent = new Intent(MainActivity.this, HourlyWeatherActivity.class);

        startActivity(hourlyActivityIntent);
    }

    @OnClick(R.id.minutelyWeatherTextView)
    public void minutelyWeatherClick() {
        Intent minutelyActivityIntent = new Intent(MainActivity.this, MinutelyWeatherActivity.class);

        startActivity(minutelyActivityIntent);
    }

    private CurrentWeather getCurrentWeatherFromJson(String json)throws JSONException{

        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonWithCurrentWeather = jsonObject.getJSONObject(CURRENTLY);

        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject(DAILY);

        JSONArray jsonWithDailyWeatherData = jsonWithDailyWeather.getJSONArray(DATA);

        JSONObject jsonWithTodayData = jsonWithDailyWeatherData.getJSONObject(0);

        String summary =  jsonWithCurrentWeather.getString(SUMMARY);
        String icon = jsonWithCurrentWeather.getString(ICON);
        String temperature = Math.round(jsonWithCurrentWeather.getDouble(TEMPERATURE)) + "";

        String  maxTemperature = Math.round(jsonWithTodayData.getDouble(TEMPERATURE_MAX)) +"";
        String minTemperature = Math.round(jsonWithTodayData.getDouble(TEMPERATURE_MIN))+"";


        CurrentWeather currentWeather = new CurrentWeather(MainActivity.this);
        currentWeather.setDescription(summary);
        currentWeather.setIconImage(icon);
        currentWeather.setCurrentTemperature(temperature);
        currentWeather.setHighestTemperature(maxTemperature);
        currentWeather.setLowestTemperature(minTemperature);

        return currentWeather;
    }

    private ArrayList<Day> getDailyWeatherFromJson(String json) throws JSONException{

        DateFormat dateFormat = new SimpleDateFormat("EEEE");

        ArrayList<Day> days = new ArrayList<>();


        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject(DAILY);

        JSONArray jsonWithDailyWeatherData = jsonWithDailyWeather.getJSONArray(DATA);

        for (int i = 0; i< jsonWithDailyWeatherData.length(); i++){

            Day day = new Day();

            JSONObject jsonObjectActual = jsonWithDailyWeatherData.getJSONObject(i);

            String rainProbability = jsonObjectActual.getDouble(PRECIP_PROBABILITY) + "";
            String weatherDescription = jsonObjectActual.getString(SUMMARY);
            String dayName = dateFormat.format(jsonObjectActual.getLong(TIME)*1000);

            day.setDayname(dayName);
            day.setRainProbability(rainProbability);
            day.setWeatherDescription(weatherDescription);

            days.add(day);


        }


        return days;
    }


    private ArrayList<Hour> getHourlyWeatherFromJson(String json) throws JSONException{



        ArrayList<Hour> hours = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);

        String timezone = jsonObject.getString(TIMEZONE);

        JSONObject jsonObjectData = jsonObject.getJSONObject(HOURLY);

        JSONArray jsonArrayHoras = jsonObjectData.getJSONArray(DATA);

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));

        for (int i = 0; i < jsonArrayHoras.length(); i++){

            Hour hour = new Hour();

            JSONObject jsonObjectActual = jsonArrayHoras.getJSONObject(i);

            String title = dateFormat.format(jsonObjectActual.getLong(TIME)*1000);
            String description = jsonObjectActual.getString(SUMMARY);

            hour.setTitle(title);
            hour.setWeatherDescription(description);

            hours.add(hour);

        }

        return hours;
    }

    private ArrayList<Minute> getMinutelyWeatherFromJson(String json) throws JSONException{

        ArrayList<Minute> minutes = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonMinutelyWeather = jsonObject.getJSONObject("minutely");

        JSONArray jsonWithMinutelyWeatherData = jsonMinutelyWeather.getJSONArray(DATA);

        for (int i = 0; i<jsonWithMinutelyWeatherData.length(); i++){

            Minute minute = new Minute();

            JSONObject jsonObjectAntual = jsonWithMinutelyWeatherData.getJSONObject(i);

            String title = dateFormat.format(jsonObjectAntual.getDouble(TIME)*1000);
            String rainProbability = jsonObjectAntual.getDouble(PRECIP_PROBABILITY) + "";

            minute.setTitle(title);
            minute.setRainProbability(rainProbability);

            minutes.add(minute);

        }

        return minutes;
    }

}
