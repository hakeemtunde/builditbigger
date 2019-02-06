package com.corebyte.mob.jokeui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String JOKE_TEXT_KEY = "JOKE_TEXT_KEY";
    TextView jokeDisplayTv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        jokeDisplayTv = (TextView)findViewById(R.id.joke_display_tv);

        if (getIntent().hasExtra(JOKE_TEXT_KEY)) {
            String jokeText = getIntent().getExtras().getString(JOKE_TEXT_KEY);
            jokeDisplayTv.setText(jokeText);

        }

    }



}
