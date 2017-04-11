package com.example.mydell.taskit;

import android.*;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.CallLog;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main3Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Switch service;
    Boolean backPressed = false;
    TextView t3;
    int counter1=0;
    int count5,trig1,even1,trig2,even2,trig3,even3,trig4,even4,trig5,even5,ev1,ev2,ev3,ev4,ev5;
    TextView loginemailview;
    FirebaseUser user;
    FirebaseAuth auth;

    String func8(int a,int b,int c)
    {
        String name,aname="",bname="",cname = "",name1;
        SharedPreferences spf;
        name1="";
        if(a==0)
        {
            aname="Airplane mode";
            if(c==1)
                cname="enabled";
            else if(c==0)
                cname="disabled";
        }

        if(a==1)
        {
            spf=getSharedPreferences("zsk",Context.MODE_PRIVATE);
            name1= spf.getString("zsk",String.valueOf(-1));
           aname=""+name1;
            aname+=" launch";

        }

        if(a==2)
        {
            aname="Battery Level";
            if(c==1) {
                cname = "increased to ";
                spf = getSharedPreferences("z123", Context.MODE_PRIVATE);
                name1 = spf.getString("z123", String.valueOf(-1));
                cname+=name1+"%";
            }
            else if(c==0){
                cname="decreased to ";
                spf = getSharedPreferences("z12", Context.MODE_PRIVATE);
                name1 = spf.getString("z12", String.valueOf(-1));
                 cname+=name1+"%";
            }

        }


        if(a==3)
        {
            aname="Bluetooth";
            if(c==1)
                cname="turned on";
            else if(c==0)
                cname="turn off";
        }
        if(a==8)
        {
            aname="Call Outgoing to ";
            spf=getSharedPreferences("z18",Context.MODE_PRIVATE);
            String z18 = spf.getString("z18", String.valueOf(-1));
            aname+=z18;

        }

        if(a==9)
        {
            aname="Data";
            if(c==1)
                cname="available";
            else
                cname="connection not detected";
        }


        if(a==10)
        {
            aname="Fliping the device";
        }

        if(a==11)
        {
            aname="GPS";
            if(c==1)
                cname="enabled";
            else if(c==0)
                cname="disabled";
        }

        if(a==12)
        {
            aname="Head Phones";
            if(c==1)
                cname="connected";
            else if(c==0)
                cname="removed";
        }


        if(a==13)
        {
            aname="Hotspot";
            if(c==1)
                cname="enabled";
            else if(c==0)
                cname="disabled";
        }


        if(a==14)
        {
            aname="Light Sensor value ="+c;


        }

        if(a==23)
        {
            SharedPreferences sharedPreferences = getSharedPreferences("TaskIt",MODE_PRIVATE);
            int h = sharedPreferences.getInt("hour",0);
            int m = sharedPreferences.getInt("minute",0);

            aname="time "+h+":"+m;

        }

        if(a==15)
        {
            aname="Power";
            if(c==1)
                cname="connected";
            else if(c==0)
                cname="disconnected";
        }


        if(a==16)
        {
            if(c==1)
                cname="Single Wave";
            else if(c==0)
                cname="Placing Phone near";
        }


        if(a==17)
        {
            aname="Screen";
            if(c==1)
                cname="On";
            else if(c==0)
                cname="Off";
        }

        if(a==18)
        {
            aname="Screen Unlocked";

        }


        if(a==19)
        {
            aname="Shake Device";
        }


        if(a==20)
        {
            aname="Silent Mode";
            if(c==1)
                cname="enabled";
            else if(c==0)
                cname="disabled";
        }


        if(a==21)
        {
            aname="SMS received";
        }
        if(a==22)
        {
            aname="Wifi";
            if(c==1)
                cname="enabled";
            else if(c==0)
                cname="disabled";
        }
        if (b == 0) {


           spf=getSharedPreferences("z22",Context.MODE_PRIVATE);
            String abc= spf.getString("z22", String.valueOf(-1));

            if(abc.equals(String.valueOf(1))) {
                bname="Bluetooth enable";
            } else if(abc.equals(String.valueOf(0))){
                bname="Bluetooth disable";

            }
        }
        if(b==1)
        {
            bname="Set Auto-Brightness ON";
        }
        if(b==2) {

            bname="Adjust Brightness";
        }

        if(b==3)
        {
         bname="Display Notification";
        }
        if(b==4)
        {
            spf=getSharedPreferences("z26",Context.MODE_PRIVATE);
            String abc= spf.getString("z26", String.valueOf(-1));

            if(abc.equals(String.valueOf(1)))

                bname="Hotspot enable";

            if(abc.equals(String.valueOf(0)))

            bname="Hotspot Disable";



        }
        if(b==5) {

            bname="Close Background Apps";

        }
        if(b==6)
        {
            spf=getSharedPreferences("zsk2",Context.MODE_PRIVATE);
            String abc= spf.getString("zsk2", String.valueOf(-1));
            bname="Launch "+abc;




        }
        if(b==7)
        {bname="Launch home screen";


        }
        if(b==8)
        { bname="DND mode enabled";



        }
        if(b==9) {
            bname="Open Call Log";
        }
        else if(b==11){
                      bname="Open URL";
        }
        else if(b==10){

            bname="Open File";
        }
        if(b==12)
        {}
        if(b==13)
        { bname="Open screen";
        }
        if(b==14)
        {    bname="Send Email";

        }
        if(b==15)
        { bname="Send Email";

        }
        if(b==16)
        {
            bname="Speaker phone on";
        }
        if(b==17)
        { bname="Flashlight On";
        }
        if(b==18)
        {

            bname="Phone Vibrate";
        }
        if(b==19)
        {   spf=getSharedPreferences("z221",Context.MODE_PRIVATE);
            String abc= spf.getString("z221", String.valueOf(-1));
            bname="Change Volume to "+abc;
        }
        if(b==20)
        {

             spf=getSharedPreferences("z222",Context.MODE_PRIVATE);
            String abc= spf.getString("z222", String.valueOf(-1));

            if(abc.equals(String.valueOf(1)))
                bname="WiFi enable";
            if(abc.equals(String.valueOf(0)))
            {
                bname="Wifi Disable";}


        }
        if(b==21)
        {     bname="Vibrate Mode Enable";
        }
        if(b==22)
        {    bname="Ringer Volume Full";
        }
        if(b==23)
        {
            bname="Mute volume";
        }





        name="On "+aname+" "+" "+cname+" "+bname;
        return name;
    }


    void func()
    {
        ListView mainList ;
        ArrayAdapter<String> listAdapter ;
        SharedPreferences preference = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);

        boolean[] switches = {preference.getBoolean("item0", false),
                preference.getBoolean("item1", false),
                preference.getBoolean("item2", false),
                preference.getBoolean("item3", false),
                preference.getBoolean("item4", false),
                preference.getBoolean("item5", false),
                preference.getBoolean("item6", false),
                preference.getBoolean("item7", false)};
        String[] details = {"Wake screen using proximity",
                "Security", "Flip the device to silent any media",
                "Turn on/off Torch ", "Headset Plugged in",
                "On wave of your hand", "Notification of battery",
                "Item eight details"};
        ArrayList<String> planetList = new ArrayList<String>();

        listAdapter = new ArrayAdapter<String>(this,R.layout.simple, planetList);
        listAdapter.clear();


        counter1=0;
        mainList=(ListView)findViewById(R.id.listView1);
        mainList.setAdapter( listAdapter );
        SharedPreferences spf=getSharedPreferences("trig1",Context.MODE_PRIVATE);
        trig1= Integer.parseInt(spf.getString("trig1",String.valueOf(-1)));
        spf=getSharedPreferences("evn1",Context.MODE_PRIVATE);
        even1= Integer.parseInt(spf.getString("evn1", String.valueOf(-1)));

        spf=getSharedPreferences("counter",Context.MODE_PRIVATE);
        count5= Integer.parseInt(spf.getString("counter", String.valueOf(0)));
        Log.i("paras",""+count5);
        spf=getSharedPreferences("trig2",Context.MODE_PRIVATE);
        trig2= Integer.parseInt(spf.getString("trig2",String.valueOf(-1)));
        spf=getSharedPreferences("evn2",Context.MODE_PRIVATE);
        even2= Integer.parseInt(spf.getString("evn2",String.valueOf(-1)));

        spf=getSharedPreferences("trig3",Context.MODE_PRIVATE);
        trig3= Integer.parseInt(spf.getString("trig3",String.valueOf(-1)));
        spf=getSharedPreferences("evn3",Context.MODE_PRIVATE);
        even3= Integer.parseInt(spf.getString("evn3",String.valueOf(-1)));

        spf=getSharedPreferences("trig4",Context.MODE_PRIVATE);
        trig4= Integer.parseInt(spf.getString("trig4",String.valueOf(-1)));
        spf=getSharedPreferences("evn4",Context.MODE_PRIVATE);
        even4= Integer.parseInt(spf.getString("evn4",String.valueOf(-1)));

        spf=getSharedPreferences("trig5",Context.MODE_PRIVATE);
        trig5= Integer.parseInt(spf.getString("trig5",String.valueOf(-1)));
        spf=getSharedPreferences("evn5",Context.MODE_PRIVATE);
        even5= Integer.parseInt(spf.getString("evn5",String.valueOf(-1)));

        spf=getSharedPreferences("ev1",Context.MODE_PRIVATE);
        ev1= Integer.parseInt(spf.getString("ev1",String.valueOf(-1)));
        spf=getSharedPreferences("ev2",Context.MODE_PRIVATE);
        ev2= Integer.parseInt(spf.getString("ev2",String.valueOf(-1)));
        spf=getSharedPreferences("ev3",Context.MODE_PRIVATE);
        ev3= Integer.parseInt(spf.getString("ev3",String.valueOf(-1)));
        spf=getSharedPreferences("ev4",Context.MODE_PRIVATE);
        ev4= Integer.parseInt(spf.getString("ev4",String.valueOf(-1)));
        spf=getSharedPreferences("ev5",Context.MODE_PRIVATE);
        ev5= Integer.parseInt(spf.getString("ev5",String.valueOf(-1)));

        if(count5==1)
        {  listAdapter.add( func8(trig1,even1,ev1)+"");


        }
        else if(count5==2)
        {
            listAdapter.add(func8(trig1,even1,ev1)+"");
            listAdapter.add(func8(trig2,even2,ev2)+"");
        }
        else if(count5==3)
        {
            listAdapter.add( func8(trig1,even1,ev1)+"");
            listAdapter.add(func8(trig2,even2,ev2)+"");
            listAdapter.add(func8(trig3,even3,ev3)+"");

        }
        else if(count5==4)
        {
            listAdapter.add(func8(trig1,even1,ev1)+"");
            listAdapter.add(func8(trig2,even2,ev2)+"");
            listAdapter.add(func8(trig3,even3,ev3)+"");
            listAdapter.add( func8(trig4,even4,ev4)+"");

        }
        else if(count5==5)
        {
            listAdapter.add(func8(trig1,even1,ev1)+"");
            listAdapter.add(func8(trig2,even2,ev2)+"");
            listAdapter.add( func8(trig3,even3,ev3)+"");
            listAdapter.add( func8(trig4,even4,ev4)+"");
            listAdapter.add(func8(trig5,even5,ev5)+"");

        }



        if(switches[0])
        {
            listAdapter.add( details[0] );
            counter1++;
        }
        if(switches[1])
        {
            listAdapter.add( details[1] );
            counter1++;
        }
        if(switches[2])
        {
            listAdapter.add( details[2] );
            counter1++;
        }
        if(switches[3])
        {
            listAdapter.add( details[3] );
            counter1++;
        }
        if(switches[4])
        {counter1++;
            listAdapter.add( details[4] );

        }
        if(switches[5])
        {counter1++;
            listAdapter.add( details[5] );

        }
        if(switches[6])
        {counter1++;
            listAdapter.add( details[6] );

        }
        t3.setText(""+(counter1+count5));
        listAdapter.notifyDataSetChanged();

    }
    @Override
    protected void onResume() {
        super.onResume();
        func();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        t3=(TextView)findViewById(R.id.t3);
        final Context context = null;
        final ActivityManager activityManager  =  (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();


    //startService(new Intent(getApplicationContext(),FloatingViewService.class));




        final ListView mainList ;
        final ArrayAdapter<String> listAdapter ;
        SharedPreferences preference = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);

        final boolean[] switches = {preference.getBoolean("item0", false),
                preference.getBoolean("item1", false),
                preference.getBoolean("item2", false),
                preference.getBoolean("item3", false),
                preference.getBoolean("item4", false),
                preference.getBoolean("item5", false),
                preference.getBoolean("item6", false),
                preference.getBoolean("item7", false)};
        final String[] details = {"Wake screen using proximity",
                "Block Incoming Calls", "Flip the device to silent any media",
                "Turn on/off Torch ", "Headset Plugged in",
                "On wave of your hand", "Notification of battery",
                "Item eight details"};
        ArrayList<String> planetList = new ArrayList<String>();

        listAdapter = new ArrayAdapter<String>(this,R.layout.simple, planetList);
        listAdapter.clear();

        /*SharedPreferences spf=getSharedPreferences("trig1",Context.MODE_PRIVATE);
        trig1= Integer.parseInt(spf.getString("trig1",String.valueOf(-1)));
        spf=getSharedPreferences("evn1",Context.MODE_PRIVATE);
        even1= Integer.parseInt(spf.getString("evn1", String.valueOf(-1)));

        spf=getSharedPreferences("counter",Context.MODE_PRIVATE);
        count5= Integer.parseInt(spf.getString("counter", String.valueOf(0)));

        spf=getSharedPreferences("trig2",Context.MODE_PRIVATE);
        trig2= Integer.parseInt(spf.getString("trig2",String.valueOf(-1)));
        spf=getSharedPreferences("evn2",Context.MODE_PRIVATE);
        even2= Integer.parseInt(spf.getString("evn2",String.valueOf(-1)));

        spf=getSharedPreferences("trig3",Context.MODE_PRIVATE);
        trig3= Integer.parseInt(spf.getString("trig3",String.valueOf(-1)));
        spf=getSharedPreferences("evn3",Context.MODE_PRIVATE);
        even3= Integer.parseInt(spf.getString("evn3",String.valueOf(-1)));

        spf=getSharedPreferences("trig4",Context.MODE_PRIVATE);
        trig4= Integer.parseInt(spf.getString("trig4",String.valueOf(-1)));
        spf=getSharedPreferences("evn4",Context.MODE_PRIVATE);
        even4= Integer.parseInt(spf.getString("evn4",String.valueOf(-1)));

        spf=getSharedPreferences("trig5",Context.MODE_PRIVATE);
        trig5= Integer.parseInt(spf.getString("trig5",String.valueOf(-1)));
        spf=getSharedPreferences("evn5",Context.MODE_PRIVATE);
        even5= Integer.parseInt(spf.getString("evn5",String.valueOf(-1)));
       *//* Log.i("paras",""+count5);
        Log.i("paras",""+trig1);
        Log.i("paras",""+trig2);
        Log.i("paras",""+trig3);
        Log.i("paras",""+trig4);
        Log.i("paras",""+trig5);
        Log.i("paras",""+even1);
        Log.i("paras",""+even2);
        Log.i("paras",""+even3);
        Log.i("paras",""+even4);
        Log.i("paras",""+even5);
       *//* if(count5==1)
        {  listAdapter.add( String.valueOf(trig1)+""+String.valueOf(even1)+"");


        }
        else if(count5==2)
        {
          listAdapter.add( trig1+""+even1+"");
            listAdapter.add( trig2+""+even2+"");
        }
        else if(count5==3)
        {
            listAdapter.add( trig1+""+even1+"");
            listAdapter.add( trig2+""+even2+"");
            listAdapter.add( trig3+""+even3+"");

        }
        else if(count5==4)
        {
            listAdapter.add( trig1+""+even1+"");
            listAdapter.add( trig2+""+even2+"");
            listAdapter.add( trig3+""+even3+"");
            listAdapter.add( trig4+""+even4+"");

        }
        else if(count5==5)
        {
            listAdapter.add( trig1+""+even1+"");
            listAdapter.add(  String.valueOf(trig2)+""+String.valueOf(even2)+"");
            listAdapter.add( trig3+""+even3+"");
            listAdapter.add( trig4+""+even4+"");
            listAdapter.add( trig5+""+even5+"");

        }



        if(switches[0])
        {
            listAdapter.add( details[0] );
           counter1++;
        }
        if(switches[1])
        {
            listAdapter.add( details[1] );
            counter1++;
        }
        if(switches[2])
        {
            listAdapter.add( details[2] );
            counter1++;
        }
        if(switches[3])
        {
            listAdapter.add( details[3] );
            counter1++;
        }
        if(switches[4])
        {counter1++;
            listAdapter.add( details[4] );

        }
        if(switches[5])
        {counter1++;
            listAdapter.add( details[5] );

        }
        if(switches[6])
        {counter1++;
            listAdapter.add( details[6] );

        }
      t3.setText(""+(counter1+count5));
        *//*
listAdapter.add( details[0] );
        listAdapter.add( "Pluto" );
        listAdapter.add( "Haumea" );
        listAdapter.add( "Makemake" );
        listAdapter.add( "Eris" );
listAdapter.remove(details[0]);
        listAdapter.add(details[1]);
*/
        func();
        mainList=(ListView)findViewById(R.id.listView1);
        mainList.setAdapter( listAdapter );
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {





            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {





                final int i1=i;
                final Dialog dialog=new Dialog(Main3Activity.this);

                dialog.setTitle("Select Option");
                dialog.setContentView(R.layout.delete);
                dialog.show();

                Button b1=(Button)dialog.findViewById(R.id.delete1);
                Button b2=(Button)dialog.findViewById(R.id.delete2);
                RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                b2.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              dialog.cancel();
                                          }
                                      }
                );
                b1.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              dialog.cancel();
                                              if (i1 > count5 - 1) {

                                                  SharedPreferences preference = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);
                                                  final boolean[] switches = {preference.getBoolean("item0", false),
                                                          preference.getBoolean("item1", false),
                                                          preference.getBoolean("item2", false),
                                                          preference.getBoolean("item3", false),
                                                          preference.getBoolean("item4", false),
                                                          preference.getBoolean("item5", false),
                                                          preference.getBoolean("item6", false),
                                                          preference.getBoolean("item7", false)};
                                                  SharedPreferences.Editor editor = preference.edit();
                                                  int p1 = -1;
                                                  for (int k = 0; k < 7; k++) {
                                                    //  Log.i("paras", "" + k);

                                                      if (switches[k]) {
                                                          p1++;
                                                      }
                                                      if (p1 == i1 - count5) {
                                                          editor.putBoolean("item" + k, false);
                                                          editor.apply();
                                                          Log.i("paras", "item" + k);
                                                          func();
                                                          break;

                                                      }
                                                  }
                                              } else {
                                                  for (int k = i1; k < count5 - 1; k++) {
                                                      if (k == 0) {
                                                          trig1 = trig2;
                                                          even1 = even2;
                                                          ev1=ev2;
                                                      }
                                                      if (k == 1) {
                                                          trig2 = trig3;
                                                          even2 = even3;
                                                          ev2=ev3;
                                                      }
                                                      if (k == 2) {
                                                          trig3 = trig4;
                                                          even3 = even4;
                                                          ev3=ev4;
                                                      }
                                                      if (k == 3) {
                                                          trig4 = trig5;
                                                          even4 = even5;
                                                          ev4=ev5;
                                                      }


                                                  }
                                                  if (count5 == 1) {
                                                      trig1 = -1;
                                                      even1 = -1;
                                                      ev1=-1;
                                                  }
                                                  if (count5 == 2) {
                                                      trig2 = -1;
                                                      even2 = -1;
                                                      ev2=-1;
                                                  }
                                                  if (count5 == 3) {
                                                      trig3 = -1;
                                                      even3 = -1;
                                                      ev3=-1;
                                                  }
                                                  if (count5 == 4) {
                                                      trig4 = -1;
                                                      even4 = -1;
                                                      ev4=-1;
                                                  }
                                                  if (count5 == 5) {
                                                      trig5 = -1;
                                                      even5 = -1;
                                                      ev5=-1;
                                                  }

                                                  count5 = count5 - 1;


                                                  SharedPreferences spf1 = getApplicationContext().getSharedPreferences("trig1", Context.MODE_PRIVATE);
                                                  SharedPreferences.Editor editor1 = spf1.edit();
                                                  editor1.putString("trig1", trig1 + "");
                                                  editor1.apply();


                                                  SharedPreferences spf2 = getApplicationContext().getSharedPreferences("evn1", Context.MODE_PRIVATE);
                                                  SharedPreferences.Editor editor2 = spf2.edit();
                                                  editor2.putString("evn1", String.valueOf(even1));
                                                  editor2.apply();


                                                  spf1 = getApplicationContext().getSharedPreferences("trig2", Context.MODE_PRIVATE);
                                                  editor1 = spf1.edit();
                                                  editor1.putString("trig2", String.valueOf(trig2));
                                                  editor1.apply();


                                                  spf2 = getApplicationContext().getSharedPreferences("evn2", Context.MODE_PRIVATE);
                                                  editor2 = spf2.edit();
                                                  editor2.putString("evn2", String.valueOf(even2));
                                                  editor2.apply();

                                                  spf1 = getApplicationContext().getSharedPreferences("trig3", Context.MODE_PRIVATE);
                                                  editor1 = spf1.edit();
                                                  editor1.putString("trig3", String.valueOf(trig3));
                                                  editor1.apply();


                                                  spf2 = getApplicationContext().getSharedPreferences("evn3", Context.MODE_PRIVATE);
                                                  editor2 = spf2.edit();
                                                  editor2.putString("evn3", String.valueOf(even3));
                                                  editor2.apply();

                                                  spf1 = getApplicationContext().getSharedPreferences("trig4", Context.MODE_PRIVATE);
                                                  editor1 = spf1.edit();
                                                  editor1.putString("trig4", String.valueOf(trig4));
                                                  editor1.apply();


                                                  spf2 = getApplicationContext().getSharedPreferences("evn4", Context.MODE_PRIVATE);
                                                  editor2 = spf2.edit();
                                                  editor2.putString("evn4", String.valueOf(even4));
                                                  editor2.apply();

                                                  spf1 = getApplicationContext().getSharedPreferences("trig5", Context.MODE_PRIVATE);
                                                  editor1 = spf1.edit();
                                                  editor1.putString("trig5", String.valueOf(trig5));
                                                  editor1.apply();

                                                  spf2 = getApplicationContext().getSharedPreferences("counter", Context.MODE_PRIVATE);
                                                  editor2 = spf2.edit();
                                                  editor2.putString("counter", count5 + "");
                                                  editor2.apply();

                                                  spf2 = getApplicationContext().getSharedPreferences("evn5", Context.MODE_PRIVATE);
                                                  editor2 = spf2.edit();
                                                  editor2.putString("evn5", String.valueOf(even5));
                                                  editor2.apply();
                                                  spf1=  getApplicationContext().getSharedPreferences("ev2", Context.MODE_PRIVATE);
                                                  editor1= spf1.edit();
                                                  editor1.putString("ev2", String.valueOf(ev2));
                                                  editor1.apply();
                                                  spf1=  getApplicationContext().getSharedPreferences("ev1", Context.MODE_PRIVATE);
                                                  editor1= spf1.edit();
                                                  editor1.putString("ev1", String.valueOf(ev1));
                                                  editor1.apply();
                                                  spf1=  getApplicationContext().getSharedPreferences("ev3", Context.MODE_PRIVATE);
                                                  editor1= spf1.edit();
                                                  editor1.putString("ev3", String.valueOf(ev3));
                                                  editor1.apply();
                                                  spf1=  getApplicationContext().getSharedPreferences("ev4", Context.MODE_PRIVATE);
                                                  editor1= spf1.edit();
                                                  editor1.putString("ev4", String.valueOf(ev4));
                                                  editor1.apply();
                                                  spf1=  getApplicationContext().getSharedPreferences("ev5", Context.MODE_PRIVATE);
                                                  editor1= spf1.edit();
                                                  editor1.putString("ev5", String.valueOf(ev5));
                                                  editor1.apply();


     /*               Log.i("paras",""+count5);
                    Log.i("paras",""+trig1);
                    Log.i("paras",""+trig2);
                    Log.i("paras",""+trig3);
                    Log.i("paras",""+trig4);
                    Log.i("paras",""+trig5);
                    Log.i("paras",""+even1);
                    Log.i("paras",""+even2);
                    Log.i("paras",""+even3);
                    Log.i("paras",""+even4);
                    Log.i("paras",""+even5);
*/
                                                  func();


                                              }
                                          }
                                      }
                );






            }
        });

        service = (Switch) findViewById(R.id.serviceButton);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        SharedPreferences preferences = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);
        if (preferences.getBoolean("ServiceOn", false) && isMyServiceRunning(MyService.class)) {
            service.setChecked(true);
        } /*else if(preferences.getBoolean("ServiceOn", false)){
            startService(intent);
            service.setChecked(true);
        }*/else{
            service.setChecked(false);
        }

        service.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Intent intent = new Intent(getApplicationContext(), MyService.class);

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Toast.makeText(getApplicationContext(),"service on ",Toast.LENGTH_LONG).show();
                    SharedPreferences preferences = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("ServiceOn", true);
                    editor.apply();
                    //Toast.makeText(getApplicationContext(),"service on "+preferences.getBoolean("TaskIt",false),Toast.LENGTH_LONG).show();
                    startService(intent);
                    Intent intent1 = new Intent(getApplicationContext(), TriggerService.class);
                    startService(intent1);
                    Intent intent2 = new Intent(getApplicationContext(),FloatingViewService.class);
                    startService(intent2);

                } else {
                    //Toast.makeText(getApplicationContext(),"service off ",Toast.LENGTH_LONG).show();

                    SharedPreferences preferences = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("ServiceOn", false);
                    editor.apply();
                    //Toast.makeText(getApplicationContext(),"service off "+preferences.getBoolean("TaskIt",false),Toast.LENGTH_LONG).show();

                    stopService(intent);
                    Intent intent1 = new Intent(getApplicationContext(), TriggerService.class);
                    stopService(intent1);
                    Intent intent2 = new Intent(getApplicationContext(), FloatingViewService.class);
                    stopService(intent2);
                }
            }
        });
        auth= FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!= null)
        {
            NavigationView navigationView1=(NavigationView)findViewById(R.id.nav_view) ;
            View header=navigationView.getHeaderView(0);
            loginemailview = (TextView) header.findViewById(R.id.t1);
            user=auth.getCurrentUser();
            loginemailview.setText(user.getEmail());
        }

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (backPressed) {
                super.onBackPressed();
                return;
            }

            this.backPressed = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    backPressed=false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent ourintent=new Intent("com.example.mydell.taskit12");
            startActivity(ourintent);
        } else if (id == R.id.nav_gallery) {
            Intent ourintent=new Intent("com.example.mydell.template");
            startActivity(ourintent);

        } else if (id == R.id.nav_slideshow) {

            Intent nextScree = new Intent(this , Login.class);
            startActivity(nextScree);
        } else if (id == R.id.nav_manage) {
            Intent ourintent=new Intent(this,Guide.class);
            startActivity(ourintent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
