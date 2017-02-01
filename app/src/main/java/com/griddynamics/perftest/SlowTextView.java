package com.griddynamics.perftest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


public class SlowTextView extends TextView {

    public SlowTextView(Context context) {
        super(context);
    }

    public SlowTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlowTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Perform some fake work here
        for (int i = 0 ; i < 300 ; i++) {
            Log.i(SlowTextView.class.getSimpleName(), "Fake work");
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
