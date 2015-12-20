package com.almightyamir.medsreminder.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.almightyamir.medsreminder.model.Medication;
import com.almightyamir.medsreminder.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Almighty Amir on 18-Dec-15.
 */
public class HistoryAdapter extends ArrayAdapter<Medication> {

    TextView history_name, history_units, history_start, history_end, history_times;

    Context historyContext;
    List<Medication> medicationHistoryList;

    public HistoryAdapter(Context context, List<Medication> medicationHistoryList) {
        super(context, R.layout.list_history);

        this.historyContext = context;
        this.medicationHistoryList = medicationHistoryList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return medicationHistoryList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) historyContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_history, null);

        history_name = (TextView) convertView.findViewById(R.id.history_list_meds_name);
        history_units = (TextView) convertView.findViewById(R.id.history_list_units);
        history_start = (TextView) convertView.findViewById(R.id.history_list_start);
        history_end = (TextView) convertView.findViewById(R.id.history_list_end);
        history_times = (TextView) convertView.findViewById(R.id.history_list_per_day);

        Medication meds = medicationHistoryList.get(position);

        Log.d("Name", "" + meds.getMedName());
        Log.d("Units",""+meds.getMedUnits());
        Log.d("Start",""+meds.getStartDate());
        Log.d("End", "" + meds.getEndDate());
        Log.d("Times", "" + meds.getTimesPerDay());

        history_name.setText("Medicine's Name:" + meds.getMedName());
        history_units.setText("Total Units to be Consumed: "+meds.getMedUnits());
        history_start.setText("Was Started on: "+getDate(meds.getStartDate()));
        history_end.setText("Will Go Up To: "+getDate(meds.getEndDate()));
        history_times.setText("Times Per Day: "+meds.getTimesPerDay());

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
