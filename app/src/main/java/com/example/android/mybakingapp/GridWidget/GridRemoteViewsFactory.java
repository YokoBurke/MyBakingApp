package com.example.android.mybakingapp.GridWidget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;

    public GridRemoteViewsFactory (Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {

    }

    //called on start and when notifyAppWidgetViewDataChanged is called
    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
