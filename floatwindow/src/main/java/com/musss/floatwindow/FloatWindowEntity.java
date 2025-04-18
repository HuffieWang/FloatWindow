package com.musss.floatwindow;

public abstract class FloatWindowEntity {

    public abstract void onClick();

    private String title;

    private FloatWindowListener listener;

    public FloatWindowEntity() {
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

    public FloatWindowListener getListener() {
        return listener;
    }

    public void setListener(FloatWindowListener listener) {
        this.listener = listener;
    }
}
