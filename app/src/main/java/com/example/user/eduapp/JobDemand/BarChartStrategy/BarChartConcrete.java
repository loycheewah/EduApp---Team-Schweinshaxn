package com.example.user.eduapp.JobDemand.BarChartStrategy;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BarChartConcrete implements com.example.user.eduapp.JobDemand.BarChartStrategy.ChartStrategyInterface {
    @Override
    public BarChart createChart(ArrayList data, Chart chart) {
        BarChart barchart = (BarChart) chart;
        if (data.size() == 0)
            return null; //nothing to show

        else if (data.size() == 1) {
            List<BarEntry> data2 = new ArrayList<>();

            for (int i = 1; i < ((ArrayList) data.get(0)).size(); i++) {
                data2.add(new BarEntry((int) Calendar.getInstance().get(Calendar.YEAR) - 8 + i - 1, (float) ((Double) ((ArrayList) data.get(0)).get(i)).floatValue()));
            }
            BarDataSet set = new BarDataSet(data2, (String) ((ArrayList) data.get(0)).get(0));
            set.setColor(ColorTemplate.JOYFUL_COLORS[1]);

            BarData data1 = new BarData(set);
            data1.setBarWidth(0.9f);
            data1.setValueFormatter(new PercentFormatter());
            data1.setValueTextColor(Color.WHITE);
            data1.setValueTextSize(14);
            barchart.setData(data1);
            barchart.setFitBars(true);
            XAxis xAxis = barchart.getXAxis();
            class MyFormat implements IAxisValueFormatter {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return String.valueOf((int) value);
                }
            }
            xAxis.setValueFormatter(new MyFormat());
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setTextColor(Color.WHITE);
            xAxis.setGridColor(Color.WHITE);
            xAxis.setAxisLineColor(Color.WHITE);
            xAxis.setTextSize(14);
            barchart.getAxisRight().setEnabled(false);
            barchart.getAxisLeft().setValueFormatter(new PercentFormatter());
            barchart.getAxisLeft().setTextColor(Color.WHITE);
            barchart.getAxisLeft().setGridColor(Color.WHITE);
            barchart.getAxisLeft().setAxisLineColor(Color.WHITE);
            barchart.getAxisLeft().setTextSize(14);
            barchart.getDescription().setEnabled(false);
            Legend legend = barchart.getLegend();
            legend.setTextColor(Color.WHITE);
            legend.setTextSize(12);
            legend.setWordWrapEnabled(true);
            barchart.invalidate();
            return barchart;
        } else {
            ArrayList data1 = new ArrayList();
            ArrayList data1label = new ArrayList();
            ArrayList datafinal = new ArrayList();

            for (int i = 0; i < data.size(); i++) {
                List<BarEntry> data2 = new ArrayList<>();
                for (int j = 1; j < ((ArrayList) data.get(i)).size(); j++) {
                    data2.add(new BarEntry((int) Calendar.getInstance().get(Calendar.YEAR) - 8 + j - 1, (float) ((Double) ((ArrayList) data.get(i)).get(j)).floatValue()));
                }
                data1.add(data2);
                data1label.add((String) ((ArrayList) data.get(i)).get(0));
            }
            ArrayList color = new ArrayList();
            for (int index = 0; index < ColorTemplate.COLORFUL_COLORS.length; index++)
                color.add(ColorTemplate.COLORFUL_COLORS[index]);
            for (int index = 0; index < ColorTemplate.JOYFUL_COLORS.length; index++)
                color.add(ColorTemplate.JOYFUL_COLORS[index]);
            for (int index = 0; index < ColorTemplate.MATERIAL_COLORS.length; index++)
                color.add(ColorTemplate.MATERIAL_COLORS[index]);
            for (int index = 0; index < ColorTemplate.PASTEL_COLORS.length; index++)
                color.add(ColorTemplate.PASTEL_COLORS[index]);
            for (int index = 0; index < ColorTemplate.VORDIPLOM_COLORS.length; index++)
                color.add(ColorTemplate.VORDIPLOM_COLORS[index]);
            for (int k = 0; k < data1.size(); k++) {
                BarDataSet data3 = new BarDataSet((List<BarEntry>) data1.get(k), (String) data1label.get(k));
                data3.setColor((int) color.get(k % color.size()));
                datafinal.add(data3);
            }

            float groupSpace = (float) 0.1;
            float barSpace = (float) 0.02;
            float barWidth = (float) ((1.00 - groupSpace) / datafinal.size()) - barSpace;

            BarData barData = new BarData();
            for (int count = 0; count < datafinal.size(); count++)
                barData.addDataSet((BarDataSet) datafinal.get(count));
            barData.setBarWidth(barWidth);
            barData.setValueFormatter(new PercentFormatter());
            barData.setValueTextColor(Color.WHITE);
            barchart.setData(barData);
            XAxis xAxis = barchart.getXAxis();
            class MyFormat implements IAxisValueFormatter {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return String.valueOf((int) value);
                }
            }
            xAxis.setValueFormatter(new MyFormat());
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setCenterAxisLabels(true);
            xAxis.setAxisMinimum(Calendar.getInstance().get(Calendar.YEAR) - 8);
            xAxis.setAxisMaximum(Calendar.getInstance().get(Calendar.YEAR));
            xAxis.setTextColor(Color.WHITE);
            xAxis.setGridColor(Color.WHITE);
            xAxis.setAxisLineColor(Color.WHITE);
            xAxis.setTextSize(14);
            barchart.groupBars((float) Calendar.getInstance().get(Calendar.YEAR) - 8, groupSpace, barSpace);
            barchart.getAxisRight().setEnabled(false);
            barchart.getAxisLeft().setValueFormatter(new PercentFormatter());
            barchart.getAxisLeft().setTextColor(Color.WHITE);
            barchart.getAxisLeft().setGridColor(Color.WHITE);
            barchart.getAxisLeft().setAxisLineColor(Color.WHITE);
            barchart.getAxisLeft().setTextSize(14);
            barchart.getDescription().setEnabled(false);
            Legend legend = barchart.getLegend();
            legend.setWordWrapEnabled(true);
            legend.setTextColor(Color.WHITE);
            legend.setTextSize(12);
            return barchart;
        }
    }
}
