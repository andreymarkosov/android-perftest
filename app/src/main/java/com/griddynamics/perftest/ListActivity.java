package com.griddynamics.perftest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class ListActivity extends AppCompatActivity {

    public static void startSlowListActivity(Context context) {
        Intent intent = new Intent(context, ListActivity.class);
        intent.putExtra(ListTypes.TAG_LIST_TYPE, ListTypes.SLOW);
        context.startActivity(intent);
    }

    public static void startFastListActivity(Context context) {
        Intent intent = new Intent(context, ListActivity.class);
        intent.putExtra(ListTypes.TAG_LIST_TYPE, ListTypes.FAST);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        String type = intent.getStringExtra(ListTypes.TAG_LIST_TYPE);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new SlowListRecyclerViewAdapter(DummyContent.ITEMS, type));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}
