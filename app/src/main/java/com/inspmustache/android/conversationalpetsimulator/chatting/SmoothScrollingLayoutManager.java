package com.inspmustache.android.conversationalpetsimulator.chatting;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

/**
 * Created by nicolai on 01.12.17.
 */

public class SmoothScrollingLayoutManager extends LinearLayoutManager {
    private static final float MS_BY_INCH = 125f;
    LinearSmoothScroller smoothScroller;

    SmoothScrollingLayoutManager (Context context) {
        super(context);

        // implement scrolling speed; adapt for different screen densities by dividing by them
        this.smoothScroller = new LinearSmoothScroller(context) {
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return MS_BY_INCH / displayMetrics.densityDpi;
            }
        };
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        this.smoothScroller.setTargetPosition(position);
        startSmoothScroll(this.smoothScroller);
    }
}
