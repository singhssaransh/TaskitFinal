package com.example.mydell.taskit;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;


public class PhoneStateChangeListener extends PhoneStateListener {
    public static boolean wasRinging;
    int prevstate=0;
    boolean ring=false;
    boolean callreceived=false;
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        switch(state){

            case TelephonyManager.CALL_STATE_RINGING:
                Log.i("paras", "RINGING "+incomingNumber);

                ring=true;
                wasRinging = true;
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.i("paras", "pickup call");

                prevstate=state;
                callreceived=true;
                //open and then cut the phone




                // this should be the last piece of code before the break
                wasRinging = true;

                break;
            case TelephonyManager.CALL_STATE_IDLE:
                if(prevstate == TelephonyManager.CALL_STATE_OFFHOOK)
                {
                    Log.i("paras","call ended");
                }
                else if (ring==true&&callreceived==false)
                {
                    Log.i("paras","Missedcall");
                }

                // this should be the last piece of code before the break
                wasRinging = false;
                break;
        }
    }
}