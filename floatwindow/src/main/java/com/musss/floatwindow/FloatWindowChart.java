package com.musss.floatwindow;

import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class FloatWindowChart {

    private final int MAX_POINTS = FloatWindowManager.getInstance().getChartMaxPoints();
    private final Deque<Entry> dataQueue = new ArrayDeque<>();
    private LineChart lineChart;
    private LineDataSet lineDataSet;
    private int xIndex = 0;

    public FloatWindowChart(LineChart lineChart, FloatWindowEntity entity) {
        this.lineChart = lineChart;
        lineDataSet = new LineDataSet(new ArrayList<>(), "");
        lineDataSet.setDrawCircles(false);
        lineDataSet.setColor(Color.GREEN);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setDrawValues(false);
        // 关闭图例（Legend）
        lineChart.getLegend().setEnabled(false);

        // 关闭描述（右下角的 "Description Label"）
        lineChart.getDescription().setEnabled(false);
        lineDataSet.setHighlightEnabled(false); // 关闭高亮

        // 关闭 X 轴刻度和标签
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);

        // 关闭左侧 Y 轴网格线（可选）
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(FloatWindowManager.getInstance().getChartAxisMinimum()); // 设置 Y 轴最小值
        leftAxis.setAxisMaximum(FloatWindowManager.getInstance().getChartAxisMaximum());  // 设置 Y 轴最大值

        // 右侧 Y 轴直接隐藏
        lineChart.getAxisRight().setEnabled(false);
        for(int i = 0; i < MAX_POINTS; i++){
            dataQueue.add(new Entry(xIndex++, (leftAxis.getAxisMaximum() + leftAxis.getAxisMinimum())/2));
        }
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        entity.setListener(() -> {
            lineChart.post(() -> {
                addDataPoint(entity.getValue());
            });
        });
        lineChart.setVisibility(View.VISIBLE);
    }

    public void addDataPoint(float yValue) {
        if (dataQueue.size() >= MAX_POINTS) {
            dataQueue.pollFirst(); // 移除最老的数据
        }
        dataQueue.addLast(new Entry(xIndex++, yValue));
        // 更新数据集
        lineDataSet.setValues(new ArrayList<>(dataQueue));
        lineChart.getData().notifyDataChanged();
        lineChart.notifyDataSetChanged();
        lineChart.setVisibleXRangeMaximum(MAX_POINTS);
        lineChart.moveViewToX(xIndex); // 滚动到最新点
    }
}
