package com.example.user.assignment172;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 24-12-2017.
 */

//BoundServices class extending Service
public class BoundServices extends Service
{

    //making object of IBinder
    private IBinder iBinder = new MyLocalBinder();

    //IBinder method
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return iBinder;
    }

    //getCurrentTime method to fetch current date & time
    public String getCurrentTime()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    //MyLocalBinder class
    public class MyLocalBinder extends Binder
    {
        BoundServices getService()
        {
            return BoundServices.this;
        }
    }
}
