package com.globemaster.com.messageapp;

import java.util.ArrayList;

public class Sms {
    public ArrayList<String> sms_list;
    private Sms()
    {

    }
    private static Sms sms=new Sms();
    public  static  Sms getInstance()
    {
        return sms;
    }
    public void add(String sms)
    {
        if (sms_list==null)
        {
            sms_list=new ArrayList<>();
        }
        sms_list.add(sms);
    }
    public String get(int position)
    {
        return sms_list.get(position);
    }
    public int getListLength()
    {
        return sms_list.size();
    }

}
