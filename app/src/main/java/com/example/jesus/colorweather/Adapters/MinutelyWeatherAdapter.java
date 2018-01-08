package com.example.jesus.colorweather.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jesus.colorweather.Minute;
import com.example.jesus.colorweather.R;

import java.util.ArrayList;

/**
 * Created by Jesus on 05/12/2017.
 */

public class MinutelyWeatherAdapter extends RecyclerView.Adapter{

    Context context;
    ArrayList<Minute> minutes;

    public MinutelyWeatherAdapter(Context context, ArrayList<Minute> minutes){
        this.context =  context;
        this.minutes =  minutes;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.minutely_list_item,parent,false);

        MinuteViewHolder minuteViewHolder = new MinuteViewHolder(view);

        return minuteViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MinuteViewHolder)holder).onBind(position);
    }

    @Override
    public int getItemCount() {

        if (minutes == null){
            return 0;
        }
        return minutes.size();
    }

    class MinuteViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        TextView rainProbabilityTextView;

        public MinuteViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.minutelyTitleTextView);
            rainProbabilityTextView = itemView.findViewById(R.id.rainProbabilityTextView);
        }

        public void onBind(int position){
            //Con base en esta posicion es como vamos a updatear las vistas

            Minute minute = minutes.get(position);

            titleTextView.setText(minute.getTitle());
            rainProbabilityTextView.setText("Rain Probability: " + minute.getRainProbability() + "%") ;
        }
    }
}
