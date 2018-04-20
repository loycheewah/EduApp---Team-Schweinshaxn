package com.example.user.eduapp.JobDemand.BarChartStrategy;

import com.github.mikephil.charting.charts.Chart;

import java.util.ArrayList;

public interface ChartStrategyInterface {
        Chart createChart(ArrayList data, Chart chart);
}
