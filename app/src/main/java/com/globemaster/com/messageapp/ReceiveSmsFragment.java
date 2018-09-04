package com.globemaster.com.messageapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ReceiveSmsFragment extends Fragment {
    protected TextView sms_notif;
    protected RecyclerView sms_recycler_view;
    public BroadcastReceiver smsreceiver;
    private SmsRecyclerAdapter smsRecyclerAdapter;
    View view;
    Sms sms;
    String data;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    Log.e("OnCreateView","Called");

        if (sms==null)
        {
            sms=Sms.getInstance();
        }
        view = inflater.inflate(R.layout.receive_sms_frag, container, false);
        sms_recycler_view = view.findViewById(R.id.sms_list);
        sms_notif=view.findViewById(R.id.sms_notif);
        if(sms.sms_list!=null&&sms.getListLength()>0)
        {
            sms_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
            smsRecyclerAdapter=new SmsRecyclerAdapter();
            sms_recycler_view.setAdapter(smsRecyclerAdapter);
            if (sms_recycler_view.getVisibility()==View.GONE)
            {
                sms_recycler_view.setVisibility(View.VISIBLE);
            }
            sms_notif.setText("You have "+Integer.toString(sms.getListLength())+" messages");
        }
        else
        {
            sms_notif.setText("You have 0 messages");
        }
        return view;
        }


    public class SmsRecyclerAdapter extends RecyclerView.Adapter<SmsRecyclerAdapter.SmsViewHolder>{

        @NonNull
        @Override
        public SmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_list_item,parent,false);
            return new SmsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SmsViewHolder holder, int position) {
        holder.sms_text.setText(sms.get(position));

        }

        @Override
        public int getItemCount() {
            return Sms.getInstance().getListLength();
        }

        class SmsViewHolder extends RecyclerView.ViewHolder
        {
            TextView sms_text;

            SmsViewHolder(View itemView) {
                super(itemView);
                sms_text=itemView.findViewById(R.id.text);
            }
        }
    }

}
