package com.sen.study_android.yishutansuo;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyAppWidgetPrivider extends AppWidgetProvider {
    private String tag=getClass().getSimpleName();
    public MyAppWidgetPrivider() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.e(tag,"---onReceive-------");

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.e(tag,"---onUpdate-------");
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Log.e(tag,"---onAppWidgetOptionsChanged-------");
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.e(tag,"---onDeleted-------");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.e(tag,"---onEnabled-------");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.e(tag,"---onDisabled-------");
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
        Log.e(tag,"---onRestored-------");
    }
}


