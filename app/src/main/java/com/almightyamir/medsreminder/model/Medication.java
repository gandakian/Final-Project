package com.almightyamir.medsreminder.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Almighty Amir on 15-Dec-15.
 */
public class Medication {

    private int id;
    private String medName;
    private int medUnits;
    private long startDate;
    private long endDate;
    private int timesPerDay;

    public Medication() {
    }

    public Medication(String medName, int medUnits, long startDate, long endDate, int timesPerDay) {
        //this.id = id;
        this.medName = medName;
        this.medUnits = medUnits;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timesPerDay = timesPerDay;
    }

    public Medication(int id, String medName, int medUnits, long startDate, long endDate, int timesPerDay) {
        this.id = id;
        this.medName = medName;
        this.medUnits = medUnits;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timesPerDay = timesPerDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getMedUnits() {
        return medUnits;
    }

    public void setMedUnits(int medUnits) {
        this.medUnits = medUnits;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public int getTimesPerDay() {
        return timesPerDay;
    }

    public void setTimesPerDay(int timesPerDay) {
        this.timesPerDay = timesPerDay;
    }

    public static long changeDates(String date){
        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        try {
            return df.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", medName='" + medName + '\'' +
                ", medUnits=" + medUnits +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", timesPerDay=" + timesPerDay +
                '}';
    }
}
