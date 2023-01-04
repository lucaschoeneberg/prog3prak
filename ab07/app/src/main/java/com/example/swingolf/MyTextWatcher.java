package com.example.swingolf;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class MyTextWatcher implements TextWatcher {

    private int id;

    private MyTextWatcher(int id) {
        this.id = id;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //access view
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //access view
    }

    @Override
    public void afterTextChanged(Editable s) {
        //access view
    }
}
