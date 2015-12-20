package com.almightyamir.medsreminder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.almightyamir.medsreminder.R;
import com.almightyamir.medsreminder.adapter.TodayAdapter;
import com.almightyamir.medsreminder.dbase.DatabaseHandler;
import com.almightyamir.medsreminder.model.Medication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class FragmentToday extends Fragment {

    private View view;
    private ListView todayList;

    private List<Medication> medsList;

    public FragmentToday() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_today, container, false);

        todayList = (ListView) view.findViewById(R.id.today_list);

        //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        //String formattedDate = DateFormat.getDateFormat(getActivity()).format(new Date());

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        String formattedDate = df.format(c.getTime());
//        //Date a = new Date();
//        android.text.format.DateFormat dformat = new android.text.format.DateFormat();
//        dformat.format("yyyy-MM-dd", new java.util.Date());
        String selectQuery = "SELECT * FROM medicines WHERE end_date >= "+Medication.changeDates(formattedDate)  ;

        System.out.println("selectQuery = " + selectQuery);
        DatabaseHandler dbHandler = new DatabaseHandler(getActivity());
        medsList = dbHandler.getTodayMedication(selectQuery);

        Log.e("getAllMedication=>", "" + medsList);

        TodayAdapter adapter = new TodayAdapter(getActivity(), medsList);
        todayList.setAdapter(adapter);

        return view;
    }
}
