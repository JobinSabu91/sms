package com.globemaster.com.messageapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> user_value_list;
    FrameLayout container;
    Sms sms;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.sendSms:
                    FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right);
                    transaction.replace(R.id.fragcontainer,new SendSmsFragment());
                    transaction.commit();
                    return true;
                case R.id.receiveSms:
                    FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();
                    transaction1.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
                    transaction1.replace(R.id.fragcontainer,new ReceiveSmsFragment());
                    transaction1.commit();
                    return true;

            }
            return false;
        }
    };
    private BroadcastReceiver smsreceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_value_list=new ArrayList<>();
        sms=Sms.getInstance();

        container=findViewById(R.id.fragcontainer);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragcontainer,new SendSmsFragment());
        transaction.commit();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //
        smsreceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                sms.add(intent.getStringExtra("sms"));

            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(smsreceiver,new IntentFilter("send"));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (smsreceiver!=null)
        {
            LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(smsreceiver);
            smsreceiver=null;
        }
    }
}
