package com.almightyamir.medsreminder.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.almightyamir.medsreminder.R;
import com.almightyamir.medsreminder.model.Medication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Almighty Amir on 18-Dec-15.
 */
public class TodayAdapter extends ArrayAdapter<Medication> {

    TextView today_name, today_units, today_start, today_end, today_times;

    Context todayContext;
    List<Medication> medicationTodayList;

    public TodayAdapter(Context context, List<Medication> medicationTodayList) {
        super(context, R.layout.list_today);

        this.todayContext = context;
        this.medicationTodayList = medicationTodayList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return medicationTodayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) todayContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_today, null);

        today_name = (TextView) convertView.findViewById(R.id.today_list_meds_name);
        today_units = (TextView) convertView.findViewById(R.id.today_list_units);
        today_start = (TextView) convertView.findViewById(R.id.today_list_start);
        today_end = (TextView) convertView.findViewById(R.id.today_list_end);
        today_times = (TextView) convertView.findViewById(R.id.today_list_per_day);

        Medication meds = medicationTodayList.get(position);

        Log.d("Name", "" + meds.getMedName());
        Log.d("Units",""+meds.getMedUnits());
        Log.d("Start",""+meds.getStartDate());
        Log.d("End", "" + meds.getEndDate());
        Log.d("Times", "" + meds.getTimesPerDay());

        today_name.setText("Medicine's Name:" + meds.getMedName());
        today_units.setText("Total Units to be Consumed: "+meds.getMedUnits());
        today_start.setText("Was Started on: "+getDate(meds.getStartDate()));
        today_end.setText("Will Go Up To: "+getDate(meds.getEndDate()));
        today_times.setText("Times Per Day: "+meds.getTimesPerDay());

        return convertView;
    }

    private String getDate(long timeStamp){

        try{
            DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }
}
