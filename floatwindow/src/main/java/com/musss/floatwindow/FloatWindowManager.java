package com.musss.floatwindow;

import android.app.Activity;
import android.content.Intent;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class FloatWindowManager {

    private static final FloatWindowManager instance = new FloatWindowManager();

    private final List<FloatWindowEntity> data = new ArrayList<>();

    private int chartMaxPoints;
    private int chartAxisMaximum;
    private int chartAxisMinimum;

    public static FloatWindowManager getInstance() {
        return instance;
    }

    public FloatWindowManager() {
    }

    public FloatWindowManager setData(List<FloatWindowEntity> list){
        this.data.clear();
        this.data.addAll(list);
        return this;
    }

    public FloatWindowManager addData(FloatWindowEntity item){
        data.add(item);
        return this;
    }

    public List<FloatWindowEntity> getData() {
        return data;
    }

    public void start(Activity activity){
        Intent intent = new Intent(activity, FloatWindowService.class);
        ContextCompat.startForegroundService(activity, intent);
    }

    public int getChartMaxPoints() {
        return chartMaxPoints;
    }

    public FloatWindowManager setChartMaxPoints(int chartMaxPoints) {
        this.chartMaxPoints = chartMaxPoints;
        return this;
    }

    public int getChartAxisMaximum() {
        return chartAxisMaximum;
    }

    public FloatWindowManager setChartAxisMaximum(int chartAxisMaximum) {
        this.chartAxisMaximum = chartAxisMaximum;
        return this;
    }

    public int getChartAxisMinimum() {
        return chartAxisMinimum;
    }

    public FloatWindowManager setChartAxisMinimum(int chartAxisMinimum) {
        this.chartAxisMinimum = chartAxisMinimum;
        return this;
    }
}
