package com.example.mydell.taskit;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import  android.widget.*;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    int b[]=new int[100];

    public static int trigid=-1;
    public static int trigeven=-1;
    int progress= 0;
    String newarray[]=new String[1000];
    int counter=0;
Boolean chec = false;
    int h;
    int m;
    String classes[]={"Airplane Mode Changed","Application Launched","Battery Level","Bluetooth Event",
           "Call Outgoing","Data Connectivity Change"
            ,"Flip Device","GPS Enabled/Disabled","Headphones Insert/Remove",
            "Hotspot Enabled/Disabled","Light Sensor","On Time","Power Connected/Removed","Proximity Sensor"
            ,"Screen On/Off","Screen Unlocked","Shake Device","Silent Mode Enabled/Disabled","SMS Received"
            ,"Wifi State Change"};

    String classes5[]={"Airplane Mode Changed","Application Launched","Battery Level","Bluetooth Event",
            "Call Active","Call Ended","Call Incoming","Call Missed","Call Outgoing","Data Connectivity Change"
            ,"Flip Device","GPS Enabled/Disabled","Headphones Insert/Remove",
            "Hotspot Enabled/Disabled","Light Sensor","Power Connected/Removed","Proximity Sensor"
            ,"Screen On/Off","Screen Unlocked","Shake Device","Silent Mode Enabled/Disabled","SMS Received"
            ,"Wifi State Change","On Time"};

    int[] images={R.drawable.a, R.drawable.c, R.drawable.d,R.drawable.e,
           R.drawable.k,R.drawable.l,
            R.drawable.q,R.drawable.r,R.drawable.s,R.drawable.t,R.drawable.u,R.drawable.a2,
            R.drawable.w,R.drawable.x,R.drawable.aa,R.drawable.ab,R.drawable.ac,R.drawable.ad,
            R.drawable.ae,R.drawable.ai};
    ArrayList<String> dummyClass = new ArrayList<String>();
    ArrayList<Integer> dummyImages = new ArrayList<Integer>();
     TextView     txt;

    SeekBar sb;
    ArrayList<String> s = new ArrayList<String>();


        int check(int i)
        {
            int counter1;
            int count5,trig1,trig2,trig3,trig4,trig5,even1,even2,even3,even4,even5,ev1,ev2,ev3,ev4,ev5;
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
            {  if(i==trig1)
                    {return 0;}

            }
            else if(count5==2)
            {   if(i==trig1)
                    {return 0;}
                if(i==trig2)
                {return 0;}


            }
            else if(count5==3)
            {
                if(i==trig1)
                {return 0;}
                if(i==trig2)
                {return 0;}
                if(i==trig3)
                {return 0;}

            }
            else if(count5==4)
            {   if(i==trig1)
            {return 0;}
                if(i==trig2)
                {return 0;}
                if(i==trig3)
                {return 0;}
                if(i==trig4)
                {return 0;}


            }
            else if(count5==5)
            {
                if(i==trig1)
                {return 0;}

                if(i==trig2)
                {return 0;}
                if(i==trig3)
                {return 0;}
                if(i==trig4)
                {return 0;}
                if(i==trig5)
                {return 0;}


            }


return 1;
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().hasExtra("list")){
            s = getIntent().getStringArrayListExtra("list");
            Toast.makeText(this,s.get(0),Toast.LENGTH_LONG).show();
            chec = true;
        }else{
            setContentView(R.layout.activity_main);
            chec = false;
        }

        ListView lv=(ListView)findViewById(R.id.lv1);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent searchIntent = getIntent();
        if(Intent.ACTION_SEARCH.equals(searchIntent.getAction()) || chec){
            int i = 0;
            if(chec){
                for (String check : classes) {
                    if (check.toLowerCase().contains(s.get(0).toLowerCase())) {
                        dummyClass.add(check);
                        dummyImages.add(images[i]);
                    }
                    i++;
                }
            }

            i=0;

            if(Intent.ACTION_SEARCH.equals(searchIntent.getAction())){
                String query = searchIntent.getStringExtra(SearchManager.QUERY);
                for (String check : classes) {
                    if (check.toLowerCase().contains(query.toLowerCase())) {
                        dummyClass.add(check);
                        dummyImages.add(images[i]);
                    }
                    i++;
                }
            }
            String[] dummyClass1 = new String[dummyClass.size()];
            int[] dummyImages1 = new int[dummyImages.size()];
            for(int j=0;j<dummyClass.size();j++){
                dummyClass1[j] = dummyClass.get(j);
                dummyImages1[j] = dummyImages.get(j);
            }
            //Toast.makeText(MainActivity.this,query,Toast.LENGTH_LONG).show();
            CustomAdapter listadap=new CustomAdapter(this, dummyClass1,dummyImages1);
            lv.setAdapter(listadap);
        }else{
            CustomAdapter listadap=new CustomAdapter(this, classes,images);
            lv.setAdapter(listadap);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lv.setOnItemClickListener(

                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Log.i("SDADSA",""+parent.getItemAtPosition((int)id));
                        //trigid=(int)id;
                        if(parent.getItemAtPosition((int)id).equals(classes5[0]))
                        { trigid=0;

                            final Dialog dialog=new Dialog(MainActivity.this);

                            dialog.setTitle("Select Option");
                            dialog.setContentView(R.layout.z10);
                            dialog.show();

                            Button b1=(Button)dialog.findViewById(R.id.b1);
                            Button b2=(Button)dialog.findViewById(R.id.b2);
                            RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                            RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                            b1.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                          dialog.cancel();
                                                      }
                                                  }
                            );
                            b2.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                                                          RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                                                          dialog.cancel();

                                                          if(r1.isChecked())
                                                          {
                                                              SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z101", Context.MODE_PRIVATE);
                                                              SharedPreferences.Editor editor = spf1.edit();
                                                              editor.putString("z101", String.valueOf(1));
                                                              editor.apply();
                                                              trigeven=0;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[0]);
                                                              startActivity(nextScree);

                                                          }
                                                          else if(r2.isChecked())
                                                          {
                                                              SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z102", Context.MODE_PRIVATE);
                                                              SharedPreferences.Editor editor = spf1.edit();
                                                              editor.putString("z102", String.valueOf(0));
                                                              editor.apply();
                                                              trigeven=1;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[0]);
                                                              startActivity(nextScree);

                                                          }

                                                      }
                                                  }
                            );



                        }
                        if(parent.getItemAtPosition((int)id).equals(classes5[1])) {

                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                                trigid = 1;
                                if (check(1) == 1) {

                                    PopupMenu popMenu = new PopupMenu(MainActivity.this, view, Gravity.CENTER);
                                    PackageManager pm = getApplicationContext().getPackageManager();
                                    List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

                                    for (ApplicationInfo packageInfo : packages)

                                    {
                                        if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null &&

                                                !pm.getLaunchIntentForPackage(packageInfo.packageName).equals(""))


                                        {

                                            newarray[counter] = packageInfo.packageName;

                                            popMenu.getMenu().add(Menu.NONE, ++counter, counter, pm.getApplicationLabel(packageInfo));
                                        }
                                    }

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        popMenu.setGravity(Gravity.CENTER);
                                    }
                                    popMenu.show();

                                    popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                        @Override
                                        public boolean onMenuItemClick(MenuItem item) {

                                            String a;
                                            a = newarray[item.getItemId() - 1];
                                            SharedPreferences spf1 = MainActivity.this.getSharedPreferences("zs", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = spf1.edit();
                                            editor.putString("zs", a);
                                            editor.apply();

                                            //Log.i("paras123",a+"");

                                            spf1 = MainActivity.this.getSharedPreferences("zsk", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor3 = spf1.edit();
                                            editor3.putString("zsk", String.valueOf(item));
                                            editor3.apply();


                                            trigeven = 1;

                                            Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                            nextScree.putExtra("triggerName", classes5[1]);
                                            startActivity(nextScree);

                                            return true;
                                        }
                                    });

                                } else {
                                    Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                                }
                            }else {
                                Toast.makeText(getApplicationContext(), "Your Android version does not support this feature", Toast.LENGTH_LONG).show();
                            }


                        }






                        if(parent.getItemAtPosition((int)id).equals(classes5[2])) {

                            trigid = 2;
                            if (check(2) == 1) {
                                final int[] prog = new int[1];
                                final int[] k = {0};
                                final Dialog dialog = new Dialog(MainActivity.this);
                                prog[0] = 0;
                                dialog.setTitle("Select Option");
                                dialog.setContentView(R.layout.z12);
                                dialog.show();
                                SeekBar skb = (SeekBar) dialog.findViewById(R.id.skb);
                                final TextView t1 = (TextView) dialog.findViewById(R.id.t1);
                                skb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                                    /*prog[0] =progress;*/
                                        k[0] = progress;
                                        t1.setText("" + progress);

                                    }

                                    public void onStartTrackingTouch(SeekBar arg0) {
                                        // TODO Auto-generated method stub

                                    }

                                    public void onStopTrackingTouch(SeekBar seekBar) {
                                        // TODO Auto-generated method stub

                                    }

                                });
                     /*       TextView t1=(TextView)dialog.findViewById(R.id.t1);
                            t1.setText(prog[0]);
*/
                                Button b1 = (Button) dialog.findViewById(R.id.b1);
                                Button b2 = (Button) dialog.findViewById(R.id.b2);
                                RadioButton r1 = (RadioButton) dialog.findViewById(R.id.r1);
                                RadioButton r2 = (RadioButton) dialog.findViewById(R.id.r2);


                                b1.setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {

                                                              dialog.cancel();
                                                          }
                                                      }
                                );
                                b2.setOnClickListener(new View.OnClickListener() {

                                                          @Override
                                                          public void onClick(View v) {
                                                              RadioButton r1 = (RadioButton) dialog.findViewById(R.id.r1);
                                                              RadioButton r2 = (RadioButton) dialog.findViewById(R.id.r2);
                                                              dialog.cancel();

                                                              if (r1.isChecked()) {
                                                                  SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z12", Context.MODE_PRIVATE);
                                                                  SharedPreferences.Editor editor = spf1.edit();
                                                                  editor.putString("z12", String.valueOf(k[0]));
                                                                  editor.apply();
                                                                  trigeven = 0;

                                                                  Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                                  nextScree.putExtra("triggerName", classes5[2]);
                                                                  startActivity(nextScree);
                                                              } else if (r2.isChecked()) {
                                                                  SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z123", Context.MODE_PRIVATE);
                                                                  SharedPreferences.Editor editor = spf1.edit();
                                                                  editor.putString("z123", String.valueOf(k[0]));
                                                                  editor.apply();
                                                                  trigeven = 1;
                                                                  Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                                  nextScree.putExtra("triggerName", classes5[2]);
                                                                  startActivity(nextScree);
                                                              }

                                                          }
                                                      }
                                );


                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }
                        }
                            if(parent.getItemAtPosition((int)id).equals(classes5[3]))
                        { trigid=3;

                            final Dialog dialog=new Dialog(MainActivity.this);

                            dialog.setTitle("Select Option");
                            dialog.setContentView(R.layout.z13);
                            dialog.show();

                            Button b1=(Button)dialog.findViewById(R.id.b1);
                            Button b2=(Button)dialog.findViewById(R.id.b2);
                            RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                            RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                            b1.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                          dialog.cancel();
                                                      }
                                                  }
                            );
                            b2.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                                                          RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                                                          dialog.cancel();

                                                          if(r1.isChecked())
                                                          {
                                                              SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z131", Context.MODE_PRIVATE);
                                                              SharedPreferences.Editor editor = spf1.edit();
                                                              editor.putString("z131", String.valueOf(1));
                                                              editor.apply();
                                                              trigeven=0;

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[3]);
                                                              startActivity(nextScree);
                                                                                                                    }
                                                          else if(r2.isChecked())
                                                          {
                                                              SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z132", Context.MODE_PRIVATE);
                                                              SharedPreferences.Editor editor = spf1.edit();
                                                              editor.putString("z132", String.valueOf(0));
                                                                 editor.apply();trigeven=1;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[3]);
                                                              startActivity(nextScree);
                                                          }

                                                      }
                                                  }
                            );


                        }


/*

                        if(parent.getItemAtPosition((int)id).equals(classes5[6]))
                        { trigid=6;

                            final Dialog dialog=new Dialog(MainActivity.this);

                            dialog.setTitle("Select Option");
                            dialog.setContentView(R.layout.z14);
                            dialog.show();

                            Button b1=(Button)dialog.findViewById(R.id.b1);
                            Button b2=(Button)dialog.findViewById(R.id.b2);

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

                                                          EditText et1=(EditText)dialog.findViewById(R.id.et1);

                                                          dialog.cancel();


                                                              SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z161", Context.MODE_PRIVATE);
                                                              SharedPreferences.Editor editor = spf1.edit();
                                                              editor.putString("z161", String.valueOf(et1.getText()));
                                                              editor.apply();

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[6]);
                                                              startActivity(nextScree);


                                                      }
                                                  }
                            );


                        }
*/
                        if(parent.getItemAtPosition((int)id).equals(classes5[8])) {
                            trigid = 8;

                            if (check(8) == 1) {
                                final Dialog dialog = new Dialog(MainActivity.this);

                                dialog.setTitle("Select Option");
                                dialog.setContentView(R.layout.z18);
                                dialog.show();

                                Button b1 = (Button) dialog.findViewById(R.id.b1);
                                Button b2 = (Button) dialog.findViewById(R.id.b2);

                                b1.setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {

                                                              dialog.cancel();
                                                          }
                                                      }
                                );
                                b2.setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {

                                                              EditText et1 = (EditText) dialog.findViewById(R.id.et1);

                                                              dialog.cancel();


                                                              SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z18", Context.MODE_PRIVATE);
                                                              SharedPreferences.Editor editor = spf1.edit();
                                                              editor.putString("z18", String.valueOf(et1.getText()));
                                                              editor.apply();

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName", classes5[8]);
                                                              startActivity(nextScree);


                                                          }
                                                      }
                                );


                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }
                        }


                            if(parent.getItemAtPosition((int)id).equals(classes5[9]))
                        { trigid=9;

                            final Dialog dialog=new Dialog(MainActivity.this);

                            dialog.setTitle("Select Option");
                            dialog.setContentView(R.layout.z19);
                            dialog.show();

                            Button b1=(Button)dialog.findViewById(R.id.b1);
                            Button b2=(Button)dialog.findViewById(R.id.b2);
                            RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                            RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                            b1.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                          dialog.cancel();
                                                      }
                                                  }
                            );
                            b2.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                                                          RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                                                          dialog.cancel();

                                                          if(r1.isChecked())
                                                          {
                                                              trigeven=1;

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[9]);
                                                              startActivity(nextScree);
                                                          }
                                                          else if(r2.isChecked())
                                                          {
                                                              trigeven=0;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[9]);
                                                              startActivity(nextScree);
                                                          }

                                                      }
                                                  }
                            );




                        }


                        if(parent.getItemAtPosition((int)id).equals(classes5[10]))
                        { trigid=10;
                            trigeven=-1;

                            Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                            nextScree.putExtra("triggerName",classes5[10]);
                            startActivity(nextScree);

                        }


                        if(parent.getItemAtPosition((int)id).equals(classes5[11]))
                        { trigid=11;
                            final Dialog dialog=new Dialog(MainActivity.this);

                            dialog.setTitle("Select Option");
                            dialog.setContentView(R.layout.z111);
                            dialog.show();

                            Button b1=(Button)dialog.findViewById(R.id.b1);
                            Button b2=(Button)dialog.findViewById(R.id.b2);
                            RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                            RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                            b1.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                          dialog.cancel();
                                                      }
                                                  }
                            );
                            b2.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                                                          RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                                                          dialog.cancel();

                                                          if(r1.isChecked())
                                                          {
                                                              trigeven=1;

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[11]);
                                                              startActivity(nextScree);
                                                          }
                                                          else if(r2.isChecked())
                                                          {
                                                              trigeven=0;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[11]);
                                                              startActivity(nextScree);
                                                          }

                                                      }
                                                  }
                            );




                        }

                        if(parent.getItemAtPosition((int)id).equals(classes5[12]))
                        { trigid=12;

                            final Dialog dialog=new Dialog(MainActivity.this);

                            dialog.setTitle("Select Option");
                            dialog.setContentView(R.layout.z112);
                            dialog.show();

                            Button b1=(Button)dialog.findViewById(R.id.b1);
                            Button b2=(Button)dialog.findViewById(R.id.b2);
                            RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                            RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                            b1.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                          dialog.cancel();
                                                      }
                                                  }
                            );
                            b2.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                                                          RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                                                          dialog.cancel();

                                                          if(r1.isChecked())
                                                          {
                                                              trigeven=1;

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[12]);
                                                              startActivity(nextScree);
                                                          }
                                                          else if(r2.isChecked())
                                                          {
                                                              trigeven=0;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[12]);
                                                              startActivity(nextScree);
                                                          }

                                                      }
                                                  }
                            );

                        }




                        if(parent.getItemAtPosition((int)id).equals(classes5[13]))
                        { trigid=13;

                            final Dialog dialog=new Dialog(MainActivity.this);

                            dialog.setTitle("Select Option");
                            dialog.setContentView(R.layout.z113);
                            dialog.show();

                            Button b1=(Button)dialog.findViewById(R.id.b1);
                            Button b2=(Button)dialog.findViewById(R.id.b2);
                            RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                            RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                            b1.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                          dialog.cancel();
                                                      }
                                                  }
                            );
                            b2.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                                                          RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                                                          dialog.cancel();

                                                          if(r1.isChecked())
                                                          {
                                                              trigeven=1;

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[13]);
                                                              startActivity(nextScree);
                                                          }
                                                          else if(r2.isChecked())
                                                          {
                                                              trigeven=0;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[13]);
                                                              startActivity(nextScree);
                                                          }

                                                      }
                                                  }
                            );

                        }

                        if(parent.getItemAtPosition((int)id).equals(classes5[14]))
                        { trigid=14;
                           final Dialog dialog = new Dialog(MainActivity.this);
                            dialog.setContentView(R.layout.z114);
                            dialog.show();


                            Button b1=(Button)dialog.findViewById(R.id.buttonc);
                            Button b2=(Button)dialog.findViewById(R.id.buttono);
                           final SensorManager sensorManager
                                    = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
                           final Sensor  lightSensor
                                    = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

                            if (lightSensor == null){
                                Toast.makeText(MainActivity.this,
                                        "No Light Sensor! quit-",
                                        Toast.LENGTH_LONG).show();
                            }else{

                                sensorManager.registerListener(lightSensorEventListener,
                                        lightSensor,
                                        SensorManager.SENSOR_DELAY_NORMAL);

                            }
                            txt=(TextView)dialog.findViewById(R.id.textView);
                            sb = (SeekBar)dialog.findViewById(R.id.seekBar);
                            final TextView  txt1=(TextView)dialog.findViewById(R.id.textView2l);


                             if(lightSensor.getMaximumRange()>2000)
                            sb.setMax((int)lightSensor.getMaximumRange()/25);
                            else
                                 sb.setMax((int)lightSensor.getMaximumRange());

                            sb.setProgress(progress);

                            sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                                    progress=i;
                                    txt1.setText(" "+i);
                                }

                                @Override
                                public void onStartTrackingTouch(SeekBar seekBar) {

                                }

                                @Override
                                public void onStopTrackingTouch(SeekBar seekBar) {

                                }
                            });


                            b1.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          sensorManager.unregisterListener(lightSensorEventListener,
                                                                  lightSensor
                                                                  );

                                                          dialog.cancel();
                                                      }
                                                  }
                            );
                            b2.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          dialog.cancel();

                                                              trigeven=progress;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[14]);
                                                              startActivity(nextScree);

                                                      }
                                                  }
                            );








                        }

                        if(parent.getItemAtPosition((int)id).equals(classes5[15]))
                        { trigid=15;
                            final Dialog dialog=new Dialog(MainActivity.this);

                            dialog.setTitle("Select Option");
                            dialog.setContentView(R.layout.z115);
                            dialog.show();

                            Button b1=(Button)dialog.findViewById(R.id.b1);
                            Button b2=(Button)dialog.findViewById(R.id.b2);
                            RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                            RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                            b1.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                          dialog.cancel();
                                                      }
                                                  }
                            );
                            b2.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                                                          RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                                                          dialog.cancel();

                                                          if(r1.isChecked())
                                                          {
                                                              trigeven=1;

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[15]);
                                                              startActivity(nextScree);
                                                          }
                                                          else if(r2.isChecked())
                                                          {
                                                              trigeven=0;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[15]);
                                                              startActivity(nextScree);
                                                          }

                                                      }
                                                  }
                            );




                        }




                        if(parent.getItemAtPosition((int)id).equals(classes5[16]))
                        { trigid=16;
                            final Dialog dialog=new Dialog(MainActivity.this);

                            dialog.setTitle("Select Option");
                            dialog.setContentView(R.layout.z116);
                            dialog.show();

                            Button b1=(Button)dialog.findViewById(R.id.b1);
                            Button b2=(Button)dialog.findViewById(R.id.b2);
                            RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                            RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                            b1.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                          dialog.cancel();
                                                      }
                                                  }
                            );
                            b2.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                                                          RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                                                          dialog.cancel();

                                                          if(r1.isChecked())
                                                          {
                                                              trigeven=1;

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[trigid]);
                                                              startActivity(nextScree);
                                                          }
                                                          else if(r2.isChecked())
                                                          {
                                                              trigeven=0;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[trigid]);
                                                              startActivity(nextScree);
                                                          }

                                                      }
                                                  }
                            );

                        }
                        if(parent.getItemAtPosition((int)id).equals(classes5[17]))
                        { trigid=17;

                            final Dialog dialog=new Dialog(MainActivity.this);

                            dialog.setTitle("Select Option");
                            dialog.setContentView(R.layout.z117);
                            dialog.show();

                            Button b1=(Button)dialog.findViewById(R.id.b1);
                            Button b2=(Button)dialog.findViewById(R.id.b2);
                            RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                            RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                            b1.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                          dialog.cancel();
                                                      }
                                                  }
                            );
                            b2.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                                                          RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                                                          dialog.cancel();

                                                          if(r1.isChecked())
                                                          {
                                                              trigeven=1;

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[trigid]);
                                                              startActivity(nextScree);
                                                          }
                                                          else if(r2.isChecked())
                                                          {
                                                              trigeven=0;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[trigid]);
                                                              startActivity(nextScree);
                                                          }

                                                      }
                                                  }
                            );

                        }
                        if(parent.getItemAtPosition((int)id).equals(classes5[18]))
                        { trigid=18;
                            trigeven=-1;

                            Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                            nextScree.putExtra("triggerName",classes5[trigid]);
                            startActivity(nextScree);


                        }
                        if(parent.getItemAtPosition((int)id).equals(classes5[19]))
                        { trigid=19;
                            trigeven=-1;

                            Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                            nextScree.putExtra("triggerName",classes5[trigid]);
                            startActivity(nextScree);


                        }

                        if(parent.getItemAtPosition((int)id).equals(classes5[20]))
                        { trigid=20;

                            final Dialog dialog=new Dialog(MainActivity.this);

                            dialog.setTitle("Select Option");
                            dialog.setContentView(R.layout.z120);
                            dialog.show();

                            Button b1=(Button)dialog.findViewById(R.id.b1);
                            Button b2=(Button)dialog.findViewById(R.id.b2);
                            RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                            RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                            b1.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                          dialog.cancel();
                                                      }
                                                  }
                            );
                            b2.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                                                          RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                                                          dialog.cancel();

                                                          if(r1.isChecked())
                                                          {
                                                              trigeven=1;

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[trigid]);
                                                              startActivity(nextScree);
                                                          }
                                                          else if(r2.isChecked())
                                                          {
                                                              trigeven=0;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[trigid]);
                                                              startActivity(nextScree);
                                                          }

                                                      }
                                                  }
                            );





                        }
                        if(parent.getItemAtPosition((int)id).equals(classes5[21]))
                        { trigid=21;

                            trigeven=-1;

                            Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                            nextScree.putExtra("triggerName",classes5[trigid]);
                            startActivity(nextScree);


                        }
                        if(parent.getItemAtPosition((int)id).equals(classes5[22]))
                        { trigid=22;

                            final Dialog dialog=new Dialog(MainActivity.this);

                            dialog.setTitle("Select Option");
                            dialog.setContentView(R.layout.z122);
                            dialog.show();

                            Button b1=(Button)dialog.findViewById(R.id.b1);
                            Button b2=(Button)dialog.findViewById(R.id.b2);
                            RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                            RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                            b1.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                          dialog.cancel();
                                                      }
                                                  }
                            );
                            b2.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          RadioButton r1=(RadioButton)dialog.findViewById(R.id.r1);
                                                          RadioButton r2=(RadioButton)dialog.findViewById(R.id.r2);
                                                          dialog.cancel();

                                                          if(r1.isChecked())
                                                          {
                                                              trigeven=1;

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[trigid]);
                                                              startActivity(nextScree);
                                                          }
                                                          else if(r2.isChecked())
                                                          {
                                                              trigeven=0;
                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName",classes5[trigid]);
                                                              startActivity(nextScree);
                                                          }

                                                      }
                                                  }
                            );





                        }
                        if(parent.getItemAtPosition((int)id).equals(classes5[23])) {
                            trigid = 23;
                            if (check(23) == 1) {
                                final Dialog dialog = new Dialog(MainActivity.this);

                                dialog.setTitle("Select Time");
                                dialog.setContentView(R.layout.on_ime);
                                dialog.show();

                                Button b1 = (Button) dialog.findViewById(R.id.b1);
                                Button b2 = (Button) dialog.findViewById(R.id.b2);

                                final TimePicker tp = (TimePicker) dialog.findViewById(R.id.timePicker);
                                tp.setIs24HourView(false);

                                tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

                                    @Override
                                    public void onTimeChanged(TimePicker timePicker, int i, int i1) {


                                        h = tp.getCurrentHour();
                                        m = tp.getCurrentMinute();

                                    }
                                });

                                b1.setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {

                                                              dialog.cancel();
                                                          }
                                                      }
                                );

                                b2.setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              SharedPreferences sharedPreferences = getSharedPreferences("TaskIt", MODE_PRIVATE);
                                                              SharedPreferences.Editor editor = sharedPreferences.edit();
                                                              editor.putInt("hour", h);
                                                              editor.putInt("minute", m);
                                                              editor.apply();
                                                            //  Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_LONG).show();

                                                              Intent nextScree = new Intent(getApplicationContext(), MainActivity2.class);
                                                              nextScree.putExtra("triggerName", classes5[trigid]);
                                                              startActivity(nextScree);
                                                          }
                                                      }
                                );
                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }
                        }







                        }
                });





    }

    SensorEventListener lightSensorEventListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            if(event.sensor.getType()==Sensor.TYPE_LIGHT) {
                if (txt != null) {
                    final float currentReading = event.values[0];
                    txt.setText("Sensor Current value:-"+String.valueOf(currentReading));


                }
            }
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menu.findItem(R.id.menu_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }
    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(getApplicationContext(),Main3Activity.class));
        super.onBackPressed();  // optional depending on your needs
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.menu_search) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra((RecognizerIntent.EXTRA_CALLING_PACKAGE), getClass().getPackage().getName());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech Hint Here");
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        /*if(msTextMatches.getSelectedItemPosition() == AdapterView.INVALID_POSITION){
            Toast.makeText(this,"Please Select Number of matches from spinner", Toast.LENGTH_LONG).show();
            return;
        }*/
            int noOfMatches = 5;//Integer.parseInt(msTextMatches.getSelectedItem().toString());
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, noOfMatches);
            intent.putExtra("id", id);
            try {
                startActivityForResult(intent, 1001);
            } catch (ActivityNotFoundException e) {

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            ArrayList<String> textMatchelist = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            int id = data.getIntExtra("id", R.id.menu_search);
            String[] a = new String[textMatchelist.size()];
            int i = 0;
            for (String c : textMatchelist) {
                a[i] = c;
                i++;
            }
            if (!textMatchelist.isEmpty()) {
                if (textMatchelist.get(0).contains("search")) {
                    String searchQuery = textMatchelist.get(0).replace("search", " ");
                    Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
                    search.putExtra(SearchManager.QUERY, searchQuery);
                    startActivity(search);
                } else {
                    /*android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) func().findItem(R.id.menu_search).getActionView();
                    SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
                    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));*/
                    //Toast.makeText(this, "HELlo", Toast.LENGTH_LONG).show();
                    //mlvTextHint.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,textMatchelist));
                    final Intent intent = new Intent(this, MainActivity.class);
                    intent.putStringArrayListExtra("list", textMatchelist);
                    startActivity(intent);
                }
            }
        } else if (resultCode == RecognizerIntent.RESULT_AUDIO_ERROR) {
            showToastMessage("Audio Error");
        } else if (resultCode == RecognizerIntent.RESULT_CLIENT_ERROR) {
            showToastMessage("Client Error");
        } else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR) {
            showToastMessage("Network Error");
        } else if (resultCode == RecognizerIntent.RESULT_NO_MATCH) {
            showToastMessage("No Match");
        } else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR) {
            showToastMessage("Server Error");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
