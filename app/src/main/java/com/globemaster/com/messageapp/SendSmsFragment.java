package com.globemaster.com.messageapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class SendSmsFragment extends Fragment {
    View view;
    String input = null;
    TextInputEditText user_input;
    Button send;
    Context context;
    private BroadcastReceiver smsreceiver;
    Sms sms;
    Intent intent;
    Bundle bundle;
     ReceiveSmsFragment receiveSmsFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.send_sms_frag, container, false);
        user_input = view.findViewById(R.id.input);
        send = view.findViewById(R.id.send);
        sms = Sms.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = user_input.getText().toString();
                if (input.trim().length() != 0 && !TextUtils.isEmpty(input)) {
                    Log.e("data",input);
                    intent = new Intent("send");
                    intent.putExtra("sms", input);
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                    user_input.getText().clear();

                } else {

                    Toast.makeText(getContext(), "Please enter a valid input", Toast.LENGTH_SHORT).show();
                }
                input = null;
            }
        });

 return view;
        }

}