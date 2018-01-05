package com.example.jesus.colorweather;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jesus on 01/12/2017.
 */

public class Day implements Parcelable{

    private String dayname;
    private String weatherDescription;
    private String rainProbability;

    public Day(){}

    protected Day(Parcel in) {
        dayname = in.readString();
        weatherDescription = in.readString();
        rainProbability = in.readString();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public String getDayname() {
        return dayname;
    }
    public void setDayname(String dayname) {
        this.dayname = dayname;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(String rainProbability) {
        this.rainProbability = rainProbability;
    }


    @Override
    public int describeContents() {
        return 0;//We dont need it
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dayname);
        dest.writeString(weatherDescription);
        dest.writeString(rainProbability);
    }
}
