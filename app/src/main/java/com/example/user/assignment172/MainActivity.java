package com.example.user.assignment172;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//MainActivity class extending AppCompatActivity
public class MainActivity extends AppCompatActivity
{
    //creating reference of UI component
    TextView textView;
    Button service;

    //creating object of BoundServices class
    BoundServices boundServices;

    //object to check service status
    boolean isBound = false;

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //setting ContentView
        setContentView(R.layout.activity_main);

        //setting reference with their ID's
        textView = findViewById(R.id.text);
        service = findViewById(R.id.start);

        //making service connection
        ServiceConnection serviceConnection = new ServiceConnection()
        {
            //onServiceConnected Method
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

                //making object of Local binder
                BoundServices.MyLocalBinder binder = (BoundServices.MyLocalBinder) iBinder;

                //calling method getServices
                boundServices = binder.getService();

                isBound = true;
            }

            //onServiceDisconnected method
            @Override
            public void onServiceDisconnected(ComponentName componentName)
            {
                isBound = false;
            }
        };

        //making intent to start service
        Intent intent = new Intent(MainActivity.this,BoundServices.class);

        //bind service to intent call
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);

        //on click of Start Service button
        service.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //getting current time in string format
                String currentTime = boundServices.getCurrentTime();

                //setting current time in textview
                textView.setText(currentTime);
            }
        });
    }
}
