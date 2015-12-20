package com.almightyamir.medsreminder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.almightyamir.medsreminder.dbase.DatabaseHandler;
import com.almightyamir.medsreminder.adapter.HistoryAdapter;
import com.almightyamir.medsreminder.model.Medication;
import com.almightyamir.medsreminder.R;

import java.util.ArrayList;

public class FragmentHistory extends Fragment {

    private View view;
    private ListView historyList;

    private ArrayList<Medication> medsList;

    public FragmentHistory() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history, container, false);

        historyList = (ListView) view.findViewById(R.id.history_list);

        DatabaseHandler dbHandler = new DatabaseHandler(getActivity());
        medsList = dbHandler.getAllMedication();

        Log.e("getAllMedication", "" + medsList);

        HistoryAdapter adapter = new HistoryAdapter(getActivity(), medsList);
        historyList.setAdapter(adapter);

        return view;
    }
}
