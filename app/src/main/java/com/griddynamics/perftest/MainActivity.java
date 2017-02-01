package com.griddynamics.perftest;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.buttonFastList).setOnClickListener(this);
        findViewById(R.id.buttonSlowList).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSlowList) {
            ListActivity.startSlowListActivity(this);
        } else if (v.getId() == R.id.buttonFastList) {
            ListActivity.startFastListActivity(this);
        }
    }
}
