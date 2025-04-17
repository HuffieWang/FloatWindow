package com.musss.floatwindow;

import android.app.Activity;
import android.content.Intent;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class FloatWindowManager {

    private static final FloatWindowManager instance = new FloatWindowManager();

    private final List<FloatWindowEntity> data = new ArrayList<>();

    public static FloatWindowManager getInstance() {
        return instance;
    }

    public FloatWindowManager() {
    }

    public void setData(List<FloatWindowEntity> list){
        this.data.clear();
        this.data.addAll(list);
    }

    public void addData(FloatWindowEntity item){
        data.add(item);
    }

    public List<FloatWindowEntity> getData() {
        return data;
    }

    public void start(Activity activity){
        Intent intent = new Intent(activity, FloatWindowService.class);
        ContextCompat.startForegroundService(activity, intent);
    }

}
