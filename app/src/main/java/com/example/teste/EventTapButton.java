package com.example.teste;

import android.app.Activity;
import android.view.View;

public class EventTapButton implements View.OnClickListener {
    private MainActivity maiActivity;
    @Override
    public void onClick(View v) {

        if(maiActivity.getState()==0)
        {
            this.maiActivity.start();
        }
        else
            this.maiActivity.addValueProgressBar(1);
    }
    public EventTapButton(MainActivity activity)
    {
        this.maiActivity=activity;

    }
}
