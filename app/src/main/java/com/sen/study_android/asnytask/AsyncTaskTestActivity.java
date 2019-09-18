package com.sen.study_android.asnytask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.sen.study_android.R;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncTaskTestActivity extends AppCompatActivity {
    private String tag=getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_test);
        startAsyncTask();;
    }


    private void startAsyncTask(){
        AsyncTaskTest asyncTaskTest=  new AsyncTaskTest();
        asyncTaskTest.execute(1,2,3);
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

    }

    private class AsyncTaskTest extends AsyncTask<Integer ,Integer,String>{

        @Override
        protected String doInBackground(Integer... integers) {


            Log.e(tag,"AsyncTaskTest-->doInBackground");
            publishProgress(2);
            return null;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(tag,"AsyncTaskTest-->onPreExecute");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e(tag,"AsyncTaskTest-->onPostExecute");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.e(tag,"AsyncTaskTest-->onProgressUpdate-->");
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            Log.e(tag,"AsyncTaskTest-->onCancelled-->"+s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.e(tag,"AsyncTaskTest-->onCancelled");
        }
    }
}
