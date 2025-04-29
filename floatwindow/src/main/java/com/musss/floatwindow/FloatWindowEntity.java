package com.musss.floatwindow;

public abstract class FloatWindowEntity {

    public abstract void onClick();
    private String title;
    private FloatWindowListener listener;
    private float value;

    public FloatWindowEntity() {
    }

    public FloatWindowEntity(float value) {
        this.value = value;
    }

    public FloatWindowEntity(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        if(listener != null){
            listener.onNotifyDataChange();
        }
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
        if(listener != null){
            listener.onNotifyDataChange();
        }
    }

    public FloatWindowListener getListener() {
        return listener;
    }

    public void setListener(FloatWindowListener listener) {
        this.listener = listener;
    }
}
