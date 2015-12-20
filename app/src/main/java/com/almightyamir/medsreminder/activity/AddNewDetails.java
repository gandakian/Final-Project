package com.almightyamir.medsreminder.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.almightyamir.medsreminder.R;
import com.almightyamir.medsreminder.dbase.DatabaseHandler;
import com.almightyamir.medsreminder.model.Medication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddNewDetails extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText medsName, medsUnit, startDate, endingDate;
    private Spinner timesPerDay;
    private Button clear, save;

    //DatabaseHandler dbase;

    private DatePickerDialog startDatePickerDialog;
    private DatePickerDialog endingDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    private String medsNameValue, medsUnitValue, startDateValue, endingDateValue, timesPerDayValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewsById();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        endingDate.setOnClickListener(this);
        startDate.setOnClickListener(this);
        clear.setOnClickListener(this);
        save.setOnClickListener(this);
        //addMore.setOnClickListener(this);

        setDateTimeField();

    }

    private void findViewsById() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        medsName = (EditText) findViewById(R.id.meds_name);
        medsUnit = (EditText) findViewById(R.id.meds_unit);

        timesPerDay = (Spinner) findViewById(R.id.meds_times_a_day);

        clear = (Button) findViewById(R.id.btn_clear);
        save = (Button) findViewById(R.id.btn_save);

        /*addMore = (Button) findViewById(R.id.btn_add_more);
        addMore.setEnabled(false);*/

        startDate = (EditText) findViewById(R.id.meds_start_date);
        startDate.setInputType(InputType.TYPE_NULL);
        startDate.requestFocus();

        endingDate = (EditText) findViewById(R.id.meds_ending_date);
        endingDate.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();

        startDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                startDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        endingDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                endingDate.setText(dateFormatter.format(newDate.getTime()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    public void clearFields() {

        medsName.setText("");
        medsUnit.setText("");
        startDate.setText("");
        endingDate.setText("");
        timesPerDay.setSelection(0);

        //addMore.setEnabled(true);
    }

    public void saveData() {

        //addMore.setEnabled(true);

        DatabaseHandler handler = new DatabaseHandler(this);

        //handler.getWritableDatabase();

        medsNameValue = medsName.getText().toString();
        medsUnitValue = medsUnit.getText().toString();
        startDateValue = startDate.getText().toString();
        endingDateValue = endingDate.getText().toString();
        timesPerDayValue = timesPerDay.getSelectedItem().toString();

        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        Log.e("medsNameValue", ""+medsNameValue);
        Log.e("medsUnitValue", ""+medsUnitValue);
        try {
            Log.e("startDateValue", ""+df.parse(startDateValue).getTime());
            Log.e("endingDateValue", ""+df.parse(endingDateValue).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("timesPerDayValue", "" + timesPerDayValue);


        Medication meds = new Medication(medsNameValue, Integer.parseInt(medsUnitValue),
                Medication.changeDates(startDateValue), Medication.changeDates(endingDateValue), Integer.parseInt(timesPerDayValue));

        Log.e("Medication", "" + meds.toString());

        handler.addMedication(meds);
        List<Medication> allMedication = handler.getAllMedication();
        Log.e("Apple", allMedication.toString());

        Toast toast = Toast.makeText(getApplicationContext(), "Data Successfully Saved", Toast.LENGTH_LONG);
        toast.show();

        clearFields();
    }

    @Override
    public void onClick(View view) {

        if (view == startDate) {
            startDatePickerDialog.show();
        } else if (view == endingDate) {
            endingDatePickerDialog.show();
        } else if (view == clear) {
            clearFields();
        } else if (view == save) {
            saveData();
        }
    }
}
