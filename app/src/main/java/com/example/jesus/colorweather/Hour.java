package com.example.jesus.colorweather;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 04/12/2017.
 */

public class Hour  implements Parcelable{

    private String Title;
    private String weatherDescription;

    public Hour(){}

    protected Hour(Parcel in) {
        Title = in.readString();
        weatherDescription = in.readString();
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(weatherDescription);
    }
}
