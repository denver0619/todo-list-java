package com.example.todolistjava;

import android.view.View;
import android.widget.ImageButton;

public class Task {
    private String value;

    public Task(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
