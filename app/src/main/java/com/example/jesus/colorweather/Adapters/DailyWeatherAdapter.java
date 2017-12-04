package com.example.jesus.colorweather.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jesus.colorweather.Day;
import com.example.jesus.colorweather.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jesus on 01/12/2017.
 */

public class DailyWeatherAdapter extends BaseAdapter {

    public static final String TAG = DailyWeatherAdapter.class.getSimpleName();

    ArrayList<Day> days;
    Context context;

    public DailyWeatherAdapter(Context context, ArrayList<Day> days){

        this.context = context;
        this.days = days;

    }

    @Override
    public int getCount() {
        return days.size();//Este es el que le va a decir al adapter cuantos elementos hay en los datos, 7 dias d ela semana, 24 horas etc


    }

    @Override
    public Object getItem(int position) {


        return days.get(position);//Item que vamos a renderizar
    }

    @Override
    public long getItemId(int position) {
        return 0;//No se utilizara
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Day day = days.get(position);

        if (view == null){
            Log.d(TAG,"Creando vistas y buscando vistas");
            view = LayoutInflater.from(context).inflate(R.layout.daily_list_item, viewGroup,false); /*Estamos inflando(convirtiendo)
        un layout, osea un xmla codigo JAVA, y despues buscanmos las views, osea los elementos, como el titulo, la descriptcion
         , la probabilidad de lluvia, eso se hace  abajo en las lineas*/

            viewHolder = new ViewHolder();

            viewHolder.dayTitle = view.findViewById(R.id.dailyListTitle);
            viewHolder.dayDescription = view.findViewById(R.id.dailyListDescription);
            viewHolder.dayRainProbability = view.findViewById(R.id.dailyListProbability);

            view.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        /*
        TextView dayTitle = view.findViewById(R.id.dailyListTitle);
            TextView dayDescription =  view.findViewById(R.id.dailyListDescription);
            TextView rainProbability = view.findViewById(R.id.dailyListProbability);

            Cada vez que scrolleamos estamos mandando a llamar la vista por el id, como se ve en ejemplo de arriba, lo que
            * desperdicia memoria y no son buenas practicas, esto se soluciona con el View Holder Patern, para evitar procesamiento innecesario*/



            viewHolder.dayTitle.setText(day.getDayname());
            viewHolder.dayDescription.setText(day.getWeatherDescription());
            viewHolder.dayRainProbability.setText(day.getRainProbability());





        return view;//Como vamos a renderizar y a mapear los datos
    }

    static class ViewHolder{

        TextView dayTitle;
        TextView dayDescription;
        TextView dayRainProbability;

    }

}
