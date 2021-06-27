package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button tapButton;
    private int state;
    private ProgressRunEvent timerTask;
    private Timer timer;
    private TextView decision;
    private Spinner difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setProgress(50);
        MyContext c =(MyContext) getApplicationContext();

        decision=findViewById(R.id.decision);

        tapButton=findViewById(R.id.button);
        tapButton.setOnClickListener(new EventTapButton(this));

        difficulty=findViewById(R.id.spinner);
        decision.setText(c.x+"");


        state=0;
    }
    public void addValueProgressBar(int value){
        this.progressBar.setProgress(this.progressBar.getProgress()+value);
        if(!verify())
            end();

    }

    public boolean verify()
    {
        if(this.progressBar.getProgress()==100)
        {
            decision.setText("You won !");
            decision.setTextColor(getResources().getColor(R.color.win));
            return false;
        }
        else if(this.progressBar.getProgress()==0)
        {
            decision.setText("You lost !");
            decision.setTextColor(getResources().getColor(R.color.lost));
            return false;
        }
        return true;
    }
    public int getState()
    {
        return this.state;
    }
    public void start()
    {
        progressBar.setProgress(50);
        tapButton.setText("Tap");
        state=1;
        timer=new Timer();
        decision.setText("");
        timerTask=new ProgressRunEvent(this);
        timer.scheduleAtFixedRate(timerTask,0,2000/Integer.valueOf(difficulty.getSelectedItem().toString()));
        this.difficulty.setEnabled(false);
    }
    public void end()
    {
        Vibrator v= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            v = (Vibrator) getSystemService(MyContext.VIBRATOR_SERVICE);
            v.vibrate(400);
        }

        timer.cancel();
        timer.purge();
        state=2;
        runOnUiThread(() -> { tapButton.setText(""); tapButton.setEnabled(false); } );
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                state=0;
                runOnUiThread(() -> { tapButton.setText("Start"); difficulty.setEnabled(true);tapButton.setEnabled(true);} );
                timer.cancel();
                timer.purge();
            }
        }, 3000);

    }
}