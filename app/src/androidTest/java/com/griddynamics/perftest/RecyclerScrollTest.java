package com.griddynamics.perftest;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.griddynamics.perftest.common.PerfTest;
import com.griddynamics.perftest.testrules.EnableLogcatDump;
import com.griddynamics.perftest.testrules.EnableNetStatsDump;
import com.griddynamics.perftest.testrules.EnablePostTestDumpsys;
import com.griddynamics.perftest.testrules.EnableTestTracing;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;


@RunWith(AndroidJUnit4.class)
@LargeTest
@PerfTest
public class RecyclerScrollTest {
    private static final int SCROLL_TIME_IN_MILLIS = 4000;
    private static final long MAX_ADAPTER_VIEW_PROCESSING_TIME_IN_MILLIS = 500;

    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<ListActivity> mActivityRule = new ActivityTestRule<>(
            ListActivity.class, true, false);

    @Rule
    public Timeout globalTimeout= new Timeout(
        SCROLL_TIME_IN_MILLIS + MAX_ADAPTER_VIEW_PROCESSING_TIME_IN_MILLIS, TimeUnit.MILLISECONDS);

    @Rule
    public EnableTestTracing mEnableTestTracing = new EnableTestTracing();

    @Rule
    public EnablePostTestDumpsys mEnablePostTestDumpsys = new EnablePostTestDumpsys();

    @Rule
    public EnableLogcatDump mEnableLogcatDump = new EnableLogcatDump();

    @Rule
    public EnableNetStatsDump mEnableNetStatsDump = new EnableNetStatsDump();

    @Test
    @PerfTest
    public void scrollSlowRecycler() throws InterruptedException {
        scrollRecycler(ListTypes.SLOW);
    }

    @Test
    @PerfTest
    public void scrollFastRecycler() throws InterruptedException {
        scrollRecycler(ListTypes.FAST);
    }

    private void scrollRecycler(String type) throws InterruptedException {
        Intent intent = new Intent();
        intent.putExtra(ListTypes.TAG_LIST_TYPE, type);
        Activity listActivity = mActivityRule.launchActivity(intent);
        RecyclerView listView = (RecyclerView) listActivity.findViewById(R.id.list);

        int lastPosition = listView.getAdapter().getItemCount() - 1;

        listView.smoothScrollToPosition(lastPosition);

        LinearLayoutManager layoutManager = (LinearLayoutManager) listView.getLayoutManager();
        while (layoutManager.findLastCompletelyVisibleItemPosition() != lastPosition) {
            Thread.sleep(300);
        }
    }
}
