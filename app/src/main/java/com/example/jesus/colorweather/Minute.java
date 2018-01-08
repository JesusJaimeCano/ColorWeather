package com.example.jesus.colorweather;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jesus on 05/12/2017.
 */

public class Minute implements Parcelable{

    private String Title;
    private String rainProbability;

    public Minute(){}

    protected Minute(Parcel in) {
        Title = in.readString();
        rainProbability = in.readString();
    }

    public static final Creator<Minute> CREATOR = new Creator<Minute>() {
        @Override
        public Minute createFromParcel(Parcel in) {
            return new Minute(in);
        }

        @Override
        public Minute[] newArray(int size) {
            return new Minute[size];
        }
    };

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(String rainProbability) {
        this.rainProbability = rainProbability;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(rainProbability);
    }
}
