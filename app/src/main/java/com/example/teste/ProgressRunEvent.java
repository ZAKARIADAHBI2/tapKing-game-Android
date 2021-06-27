package com.example.teste;

import java.util.TimerTask;

public class ProgressRunEvent extends TimerTask {
    private MainActivity mainActivity;
    @Override
    public void run() {
    mainActivity.addValueProgressBar(-1);
    }
    public ProgressRunEvent(MainActivity activity)
    {
        this.mainActivity=activity;
    }
}
