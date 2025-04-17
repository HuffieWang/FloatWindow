package com.musss.floatwindow;

public abstract class FloatWindowEntity {

    public abstract void onClick();

    private String title;

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
    }
}
