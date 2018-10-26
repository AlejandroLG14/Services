package com.alejandrolg.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.net.URL;

public class HelloService extends Service {

    private String serviceTag = "HELLO_SERVICE";
    private Boolean isRunning = false;
    private String AsyncTask =  "ASYNCTASK";

    public HelloService() {
    }
    @Override
    public void onCreate() {
        isRunning = true;
        Log.d(serviceTag, "CreateMSG");
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(serviceTag, "StarMSG");
        new Thread(new Runnable() {
            @Override
            public void run() {
                DownloaderAsync downloaderAsync = new DownloaderAsync();
                downloaderAsync.execute();

                stopSelf();


                //Your logic that service will perform will be placed here
                //In this example we are just looping and waits for 1000 milliseconds in each loop.
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }

                    if(isRunning){
                        Log.i(serviceTag, "Service running");
                    }
                }

                //Stop service once it finishes its task
                stopSelf();
            }
        }).start();

        return Service.START_STICKY;
    }



    @Override
    public void onDestroy() {
        isRunning = false;
        Log.d(serviceTag, "DestroyMSG");
        super.onDestroy();

    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public class DownloaderAsync extends AsyncTask<URL, Integer, String>{

        @Override
        protected String doInBackground(URL... urls) {
            Log.d(AsyncTask, "DO Async");
            return null;
        }

        @Override
        protected void onPreExecute() {
            Log.d(AsyncTask, "PRE Async");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(AsyncTask, "POST Async");
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(AsyncTask, "PRO Async");
            super.onProgressUpdate(values);
        }
    }
}
