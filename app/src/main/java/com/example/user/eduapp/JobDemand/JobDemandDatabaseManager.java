package com.example.user.eduapp.JobDemand;



import android.content.Context;
import android.database.Cursor;

import com.example.user.eduapp.JobDemand.JobDemandDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class JobDemandDatabaseManager {
    private JobDemandDatabase db = null;

    public JobDemandDatabaseManager(Context context){
        db = new JobDemandDatabase(context);
    }

    public ArrayList getData(String industry1) {
        ArrayList datalist = new ArrayList();
        Cursor datafromdb = db.ReadDatabase(industry1);
        datafromdb.moveToFirst();
        while (!datafromdb.isAfterLast()) {
            boolean found = false;
            for(int i = 0; i < datalist.size(); i++){
                if(((String)((ArrayList) datalist.get(i)).get(0)).equals(datafromdb.getString(datafromdb.getColumnIndex("industry2")))){
                    found = true;
                    break;
                }
            }
            if(!found){
                ArrayList data = new ArrayList();
                data.add((String)datafromdb.getString(datafromdb.getColumnIndex("industry2")));
                for (int j = 0; j < 8; j++){
                    data.add((double) 0);
                }
                datalist.add(data);
            }

            int index = Integer.valueOf(datafromdb.getString(datafromdb.getColumnIndex("year"))) - (Calendar.getInstance().get(Calendar.YEAR) - 9);
            double value = Double.valueOf(datafromdb.getString(datafromdb.getColumnIndex("job_vacancy_rate")));
            for(int i = 0; i < datalist.size(); i++){
                if(((String)((ArrayList)datalist.get(i)).get(0)).equals(datafromdb.getString(datafromdb.getColumnIndex("industry2")))){
                    ((ArrayList)datalist.get(i)).set(index, value);
                    break;
                }
            }
            datafromdb.moveToNext();
        }
        datafromdb.close();
        return datalist;
    }
}
