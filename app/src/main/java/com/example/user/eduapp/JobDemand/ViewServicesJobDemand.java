package com.example.user.eduapp.JobDemand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.eduapp.JobDemand.BarChartStrategy.BarChartConcrete;
import com.example.user.eduapp.JobDemand.BarChartStrategy.ChartStrategyInterface;

import com.example.user.eduapp.R;
import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;

public class ViewServicesJobDemand extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_services_job_demand);

        ArrayList data = JobDemandActivity.databaseManager.getData("services");

        BarChart chart = (BarChart) findViewById(R.id.chart);
        ChartStrategyInterface barChartStrategy = new BarChartConcrete();
        chart = (BarChart) barChartStrategy.createChart(data, chart);

        if(chart == null)
            return;
        else
            chart.invalidate();
    }
}
