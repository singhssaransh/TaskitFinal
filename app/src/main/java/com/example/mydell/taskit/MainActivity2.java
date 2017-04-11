package com.example.mydell.taskit;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity2 extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1001;
    String newarray[] = new String[1000];
    int counter = 0;
    String classes2[] = {"Adjust Brightness", "Auto Brightness ON"
            , "Bluetooth Configure", "Display Notification", "Do not disturb mode enabled", "Flashlight On", "Hotspot On/Off",
            "Kill Background Apps", "Launch Application", "Launch Home Screen",
            "Open Call Log", "Open File", "Open Website", "Ringer Volume Full",
            "Screen On", "Send Email", "Send SMS", "Silent Mode", "Speaker Phone On",
            "Vibrate", "Vibration Mode", "Volume Change", "Wifi Configure"};
    int[] images2 = {R.drawable.a11, R.drawable.a5, R.drawable.a4, R.drawable.a12, R.drawable.a18, R.drawable.a39, R.drawable.a14,
            R.drawable.a18, R.drawable.a19, R.drawable.a20,
            R.drawable.a25, R.drawable.a26, R.drawable.g, R.drawable.a29, R.drawable.a31,
            R.drawable.a32, R.drawable.a33, R.drawable.ah, R.drawable.a36,
            R.drawable.a40, R.drawable.a40, R.drawable.a41, R.drawable.a43};//27
    int[] trig = new int[100];
    int[] even = new int[100];
    int count = 0;
    TextView triggerName;
    ArrayList<String> dummyClass = new ArrayList<String>();
    ArrayList<Integer> dummyImages = new ArrayList<Integer>();
    ArrayList<String> s = new ArrayList<String>();
    Intent intent;
    Boolean chec = false;
    String abcd="";

    int check(int i) {
        int counter1;
        int count5, trig1, trig2, trig3, trig4, trig5, even1, even2, even3, even4, even5, ev1, ev2, ev3, ev4, ev5;
        SharedPreferences spf = getSharedPreferences("trig1", Context.MODE_PRIVATE);
        trig1 = Integer.parseInt(spf.getString("trig1", String.valueOf(-1)));
        spf = getSharedPreferences("evn1", Context.MODE_PRIVATE);
        even1 = Integer.parseInt(spf.getString("evn1", String.valueOf(-1)));

        spf = getSharedPreferences("counter", Context.MODE_PRIVATE);
        count5 = Integer.parseInt(spf.getString("counter", String.valueOf(0)));
        Log.i("paras", "" + count5);
        spf = getSharedPreferences("trig2", Context.MODE_PRIVATE);
        trig2 = Integer.parseInt(spf.getString("trig2", String.valueOf(-1)));
        spf = getSharedPreferences("evn2", Context.MODE_PRIVATE);
        even2 = Integer.parseInt(spf.getString("evn2", String.valueOf(-1)));

        spf = getSharedPreferences("trig3", Context.MODE_PRIVATE);
        trig3 = Integer.parseInt(spf.getString("trig3", String.valueOf(-1)));
        spf = getSharedPreferences("evn3", Context.MODE_PRIVATE);
        even3 = Integer.parseInt(spf.getString("evn3", String.valueOf(-1)));

        spf = getSharedPreferences("trig4", Context.MODE_PRIVATE);
        trig4 = Integer.parseInt(spf.getString("trig4", String.valueOf(-1)));
        spf = getSharedPreferences("evn4", Context.MODE_PRIVATE);
        even4 = Integer.parseInt(spf.getString("evn4", String.valueOf(-1)));

        spf = getSharedPreferences("trig5", Context.MODE_PRIVATE);
        trig5 = Integer.parseInt(spf.getString("trig5", String.valueOf(-1)));
        spf = getSharedPreferences("evn5", Context.MODE_PRIVATE);
        even5 = Integer.parseInt(spf.getString("evn5", String.valueOf(-1)));

        spf = getSharedPreferences("ev1", Context.MODE_PRIVATE);
        ev1 = Integer.parseInt(spf.getString("ev1", String.valueOf(-1)));
        spf = getSharedPreferences("ev2", Context.MODE_PRIVATE);
        ev2 = Integer.parseInt(spf.getString("ev2", String.valueOf(-1)));
        spf = getSharedPreferences("ev3", Context.MODE_PRIVATE);
        ev3 = Integer.parseInt(spf.getString("ev3", String.valueOf(-1)));
        spf = getSharedPreferences("ev4", Context.MODE_PRIVATE);
        ev4 = Integer.parseInt(spf.getString("ev4", String.valueOf(-1)));
        spf = getSharedPreferences("ev5", Context.MODE_PRIVATE);
        ev5 = Integer.parseInt(spf.getString("ev5", String.valueOf(-1)));

        if (count5 == 1) {
            if (i == even1) {
                return 0;
            }

        } else if (count5 == 2) {
            if (i == even1) {
                return 0;
            }
            if (i == even2) {
                return 0;
            }


        } else if (count5 == 3) {
            if (i == even1) {
                return 0;
            }
            if (i == even2) {
                return 0;
            }
            if (i == even3) {
                return 0;
            }

        } else if (count5 == 4) {
            if (i == even1) {
                return 0;
            }
            if (i == even2) {
                return 0;
            }
            if (i == even3) {
                return 0;
            }
            if (i == even4) {
                return 0;
            }


        } else if (count5 == 5) {
            if (i == even1) {
                return 0;
            }

            if (i == even2) {
                return 0;
            }
            if (i == even3) {
                return 0;
            }
            if (i == even4) {
                return 0;
            }
            if (i == even5) {
                return 0;
            }


        }


        return 1;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_row2);

        if (getIntent().hasExtra("list")) {
            s = getIntent().getStringArrayListExtra("list");
            Toast.makeText(this, s.get(0), Toast.LENGTH_LONG).show();
            triggerName = (TextView) findViewById(R.id.triggerName);
            triggerName.setText(getIntent().getStringExtra("triggerName"));
            chec = true;
        } else {
            setContentView(R.layout.custom_row2);
            triggerName = (TextView) findViewById(R.id.triggerName);
            triggerName.setText(getIntent().getStringExtra("triggerName"));
            chec = false;
        }

        tts = new TextToSpeech(this, this);

        /*triggerName = (TextView) findViewById(R.id.triggerName);
        intent = getIntent();
        triggerName.setText(intent.getStringExtra("triggerName"));*/
        ListView lv = (ListView) findViewById(R.id.lv2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);*/
        Intent searchIntent = getIntent();
        if (Intent.ACTION_SEARCH.equals(searchIntent.getAction()) | chec) {
            String query = searchIntent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, query, Toast.LENGTH_LONG).show();
/*int i=0;
            for(String check : classes2){
                if(check.toLowerCase().contains(query.toLowerCase())){
                    dummyClass.add(check);
                    dummyImages.add(images2[i]);
                }
                i++;
            }*/

            int i = 0;
            if (chec) {
                for (String check : classes2) {
                    if (check.toLowerCase().contains(s.get(0).toLowerCase())) {
                        dummyClass.add(check);
                        dummyImages.add(images2[i]);
                    }
                    i++;
                }
            }

            i = 0;

            if (Intent.ACTION_SEARCH.equals(searchIntent.getAction())) {
                SharedPreferences sharedPreferences = getSharedPreferences("trigName", MODE_PRIVATE);
                String name = sharedPreferences.getString("triggName", "");
                triggerName.setText(name);
                for (String check : classes2) {
                    if (check.toLowerCase().contains(query.toLowerCase())) {
                        dummyClass.add(check);
                        dummyImages.add(images2[i]);
                    }
                    i++;
                }
            }


            String[] dummyClass1 = new String[dummyClass.size()];
            int[] dummyImages1 = new int[dummyImages.size()];
            for (int j = 0; j < dummyClass.size(); j++) {
                dummyClass1[j] = dummyClass.get(j);
                dummyImages1[j] = dummyImages.get(j);
            }

            CustomAdapter listadap = new CustomAdapter(this, dummyClass1, dummyImages1);
            lv.setAdapter(listadap);
        } else {
            CustomAdapter listadap = new CustomAdapter(this, classes2, images2);
            lv.setAdapter(listadap);
        }

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                        if (parent.getItemAtPosition((int) id).equals(classes2[0])) {
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {


                                if (check(2) == 1) {
                                    final Dialog dialog = new Dialog(MainActivity2.this);
                                    final int[] z222 = {0};
                                    dialog.setTitle("Select Option");
                                    dialog.setContentView(R.layout.z223);
                                    dialog.show();
                                    SeekBar skb = (SeekBar) dialog.findViewById(R.id.seekBar);

                                    skb.setMax(25);
                                    final TextView t1 = (TextView) dialog.findViewById(R.id.textView2l);
                                    skb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                                            z222[0] = progress;
                                            t1.setText("" + progress);

                                        }

                                        public void onStartTrackingTouch(SeekBar arg0) {
                                            // TODO Auto-generated method stub

                                        }

                                        public void onStopTrackingTouch(SeekBar seekBar) {
                                            // TODO Auto-generated method stub

                                        }

                                    });
                                    Button b1 = (Button) dialog.findViewById(R.id.buttonc);
                                    Button b2 = (Button) dialog.findViewById(R.id.buttono);


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

                                                                  SharedPreferences spf1 = getApplicationContext().getSharedPreferences("zbr", Context.MODE_PRIVATE);
                                                                  SharedPreferences.Editor editor = spf1.edit();
                                                                  editor.putString("zbr", String.valueOf(z222[0]));
                                                                  editor.apply();
                                                                  fu(2);

                                                              }
                                                          }
                                    );


                                } else {
                                    Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                                }
                            } else {Toast.makeText(getApplicationContext(), "Your Android version does not support this feature", Toast.LENGTH_LONG).show();
                            }
                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[1])) {
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {


                                if (check(1) == 1) {
                                    fu(1);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                                }

                            } else {Toast.makeText(getApplicationContext(), "Your Android version does not support this feature", Toast.LENGTH_LONG).show();
                            }
                        }


                        if (parent.getItemAtPosition((int) id).equals(classes2[2])) {
                            if (check(0) == 1) {
                                final Dialog dialog = new Dialog(MainActivity2.this);

                                dialog.setTitle("Select Option");
                                dialog.setContentView(R.layout.z22);
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

                                                              dialog.cancel();
                                                              RadioButton r1 = (RadioButton) dialog.findViewById(R.id.r1);
                                                              RadioButton r2 = (RadioButton) dialog.findViewById(R.id.r2);
                                                              dialog.cancel();

                                                              if (r1.isChecked()) {
                                                                  SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z22", Context.MODE_PRIVATE);
                                                                  SharedPreferences.Editor editor = spf1.edit();
                                                                  editor.putString("z22", String.valueOf(1));
                                                                  editor.apply();
                                                                  fu(0);


                                                              } else if (r2.isChecked()) {
                                                                  SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z22", Context.MODE_PRIVATE);
                                                                  SharedPreferences.Editor editor = spf1.edit();
                                                                  editor.putString("z22", String.valueOf(0));
                                                                  editor.apply();
                                                                  fu(0);

                                                              }


                                                          }

                                                      }
                                );


                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }
                        }

                        if (parent.getItemAtPosition((int) id).equals(classes2[3])) {
                            if (check(3) == 1) {

                                Intent startMain = new Intent(getApplicationContext(), NotificationAction.class);
                                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startMain.putExtra("triggerName", triggerName.getText());
                                startActivity(startMain);
                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }
                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[4])) {


                            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                                if (check(8) == 1) {
                                    NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        if (!mNotificationManager.isNotificationPolicyAccessGranted()) {
                                            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                                            startActivity(intent);


                                        }
                                    }

                                    fu(8);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                                }

                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Your Android version does not support this feature", Toast.LENGTH_LONG).show();
                            }
                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[5])) {
                            if (check(17) == 1) {
                                fu(17);
                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }


                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[6])) {
                            if (check(4) == 1) {

                                final Dialog dialog = new Dialog(MainActivity2.this);

                                dialog.setTitle("Select Option");
                                dialog.setContentView(R.layout.z26);
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

                                                              dialog.cancel();
                                                              RadioButton r1 = (RadioButton) dialog.findViewById(R.id.r1);
                                                              RadioButton r2 = (RadioButton) dialog.findViewById(R.id.r2);
                                                              dialog.cancel();

                                                              if (r1.isChecked()) {
                                                                  SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z26", Context.MODE_PRIVATE);
                                                                  SharedPreferences.Editor editor = spf1.edit();
                                                                  editor.putString("z26", String.valueOf(1));
                                                                  editor.apply();
                                                                  fu(4);


                                                              } else if (r2.isChecked()) {
                                                                  SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z26", Context.MODE_PRIVATE);
                                                                  SharedPreferences.Editor editor = spf1.edit();
                                                                  editor.putString("z26", String.valueOf(0));
                                                                  editor.apply();
                                                                  fu(4);

                                                              }



                                                          }

                                                      }
                                );


                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }


                        }


                        if (parent.getItemAtPosition((int) id).equals(classes2[7])) {
                            if (check(5) == 1) {
                                Intent startMain = new Intent(getApplicationContext(), killBackgroundAction.class);
                                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startMain.putExtra("triggerName", triggerName.getText());
                                startActivity(startMain);
                                //fu((int) id);}
                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }

                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[8])) {
                            if (check(6) == 1) {


                                PopupMenu popMenu = new PopupMenu(MainActivity2.this, view, Gravity.CENTER);
                                PackageManager pm = getApplicationContext().getPackageManager();
                                List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

                                for (ApplicationInfo packageInfo : packages)

                                {
                                    if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null &&

                                            !pm.getLaunchIntentForPackage(packageInfo.packageName).equals(""))


                                    {

                                        newarray[counter] = packageInfo.packageName;
                                        // String sk= (String) pm.getApplicationLabel(packageInfo);

                                        // Log.i("abc",packageInfo.packageName);
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
                                        SharedPreferences spf1 = MainActivity2.this.getSharedPreferences("zs2", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = spf1.edit();
                                        editor.putString("zs2", a);
                                        editor.apply();
                                        // Log.i("paras123",a+"");

                                        spf1 = MainActivity2.this.getSharedPreferences("zsk2", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor3 = spf1.edit();
                                        editor3.putString("zsk2", String.valueOf(item));
                                        editor3.apply();


                                        fu(6);

                                        return true;
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }
                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[9])) {
                            if (check(7) == 1) {
                                fu(7);
                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }


                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[10])) {
                            if (check(9) == 1) {
                                fu(9);
                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }

                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[11])) {
                            if (check(10) == 1) {
                                Intent startMain = new Intent(MainActivity2.this, openFileAction.class);
                                startMain.putExtra("triggerName", triggerName.getText());
                                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(startMain);
                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }

                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[12])) {
                            if (check(11) == 1) {

                                Intent startMain = new Intent(MainActivity2.this, openURL.class);
                                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startMain.putExtra("triggerName", triggerName.getText());
                                startActivity(startMain);
                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }

                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[13])) {
                            if (check(22) == 1) {
                                fu(22);
                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }


                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[14])) {
                            if (check(13) == 1)
                                fu(13);

                            else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }

                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[15])) {
                            if (check(14) == 1) {
                                final Dialog dialog = new Dialog(MainActivity2.this);

                                dialog.setTitle("Select Option");
                                dialog.setContentView(R.layout.z215);
                                dialog.show();

                                Button b1 = (Button) dialog.findViewById(R.id.button3);
                                Button b2 = (Button) dialog.findViewById(R.id.button2);

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

                                                              dialog.cancel();
                                                              EditText e1 = (EditText) dialog.findViewById(R.id.editText);
                                                              EditText e2 = (EditText) dialog.findViewById(R.id.editText2);
                                                              EditText e3 = (EditText) dialog.findViewById(R.id.editText3);

                                                              SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z2151", Context.MODE_PRIVATE);
                                                              SharedPreferences.Editor editor = spf1.edit();
                                                              editor.putString("z2151", String.valueOf(e1.getText()));
                                                              editor.apply();

                                                              spf1 = getApplicationContext().getSharedPreferences("z2153", Context.MODE_PRIVATE);
                                                              editor = spf1.edit();
                                                              editor.putString("z2153", String.valueOf(e3.getText()));
                                                              editor.apply();

                                                              spf1 = getApplicationContext().getSharedPreferences("z2152", Context.MODE_PRIVATE);
                                                              editor = spf1.edit();
                                                              editor.putString("z2152", String.valueOf(e2.getText()));
                                                              editor.apply();


                                                              fu(14);
                                                          }

                                                      }
                                );


                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }
                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[16])) {
                            if (check(15) == 1) {
                                final Dialog dialog = new Dialog(MainActivity2.this);

                                dialog.setTitle("Select Option");
                                dialog.setContentView(R.layout.z217);
                                dialog.show();

                                Button b1 = (Button) dialog.findViewById(R.id.button3);
                                Button b2 = (Button) dialog.findViewById(R.id.button2);

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

                                                              dialog.cancel();
                                                              EditText e1 = (EditText) dialog.findViewById(R.id.editText);
                                                              EditText e2 = (EditText) dialog.findViewById(R.id.editText3);

                                                              SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z2171", Context.MODE_PRIVATE);
                                                              SharedPreferences.Editor editor = spf1.edit();
                                                              editor.putString("z2171", String.valueOf(e1.getText()));
                                                              editor.apply();

                                                              spf1 = getApplicationContext().getSharedPreferences("z2172", Context.MODE_PRIVATE);
                                                              editor = spf1.edit();
                                                              editor.putString("z2172", String.valueOf(e2.getText()));
                                                              editor.apply();


                                                              fu(15);
                                                          }

                                                      }
                                );


                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }
                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[17])) {
                            if (check(23) == 1)
                              fu(23);

                            else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }


                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[18])) {
                            if (check(16) == 1)
                            {    fu(16);
                                Toast.makeText(getApplicationContext(), "Please ensure that your call outgoing screen is on when the action is triggered", Toast.LENGTH_LONG).show();
                            }


                            else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }


                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[19])) {
                            if (check(18) == 1)
                                fu(18);

                            else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }


                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[20])) {
                            if (check(21) == 1)
                                fu(21);

                            else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }


                        }
                        if (parent.getItemAtPosition((int) id).equals(classes2[21])) {
                            if (check(19) == 1) {
                                final Dialog dialog = new Dialog(MainActivity2.this);
                                final int[] z221 = {0};
                                dialog.setTitle("Select Option");
                                dialog.setContentView(R.layout.z221);
                                dialog.show();
                                SeekBar skb = (SeekBar) dialog.findViewById(R.id.seekBar);
                                AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

                                skb.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
                                final TextView t1 = (TextView) dialog.findViewById(R.id.textView2l);
                                skb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                                    /*prog[0] =progress;*/
                                        z221[0] = progress;
                                        t1.setText("" + progress);

                                    }

                                    public void onStartTrackingTouch(SeekBar arg0) {
                                        // TODO Auto-generated method stub

                                    }

                                    public void onStopTrackingTouch(SeekBar seekBar) {
                                        // TODO Auto-generated method stub

                                    }

                                });
                                Button b1 = (Button) dialog.findViewById(R.id.buttonc);
                                Button b2 = (Button) dialog.findViewById(R.id.buttono);


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

                                                              SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z221", Context.MODE_PRIVATE);
                                                              SharedPreferences.Editor editor = spf1.edit();
                                                              editor.putString("z221", String.valueOf(z221[0]));
                                                              editor.apply();
                                                              fu(19);

                                                          }
                                                      }
                                );


                            } else {
                                Toast.makeText(getApplicationContext(), "Cannot Make more than one of these", Toast.LENGTH_LONG).show();
                            }
                        }

                        if (parent.getItemAtPosition((int) id).equals(classes2[22])) {
                            if (check(20) == 1) {
                                final Dialog dialog = new Dialog(MainActivity2.this);

                                dialog.setTitle("Select Option");
                                dialog.setContentView(R.layout.z222);
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

                                                              dialog.cancel();
                                                              RadioButton r1 = (RadioButton) dialog.findViewById(R.id.r1);
                                                              RadioButton r2 = (RadioButton) dialog.findViewById(R.id.r2);
                                                              dialog.cancel();

                                                              if (r1.isChecked()) {
                                                                  SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z222", Context.MODE_PRIVATE);
                                                                  SharedPreferences.Editor editor = spf1.edit();
                                                                  editor.putString("z222", String.valueOf(1));
                                                                  editor.apply();
                                                                  fu(20);


                                                              } else if (r2.isChecked()) {
                                                                  SharedPreferences spf1 = getApplicationContext().getSharedPreferences("z222", Context.MODE_PRIVATE);
                                                                  SharedPreferences.Editor editor = spf1.edit();
                                                                  editor.putString("z222", String.valueOf(0));
                                                                  editor.apply();
                                                                  fu(20);

                                                              }


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
                bname="Bluetooth diable";

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menu.findItem(R.id.menu_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        SharedPreferences sharedPreferences = getSharedPreferences("trigName", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("triggName", triggerName.getText().toString());
        editor.apply();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        //noinspection SimplifiableIfStatement
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
                startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
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
                    final Intent intent = new Intent(this, MainActivity2.class);
                    intent.putStringArrayListExtra("list", textMatchelist);
                    intent.putExtra("triggerName", triggerName.getText().toString());
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

    public void fu(int id) {
        SharedPreferences spf = getSharedPreferences("counter", Context.MODE_PRIVATE);
        count = Integer.parseInt(spf.getString("counter", String.valueOf(0)));
        if (count == 5) {
            Toast.makeText(getApplicationContext(), "You have reached maximum trigger limit", Toast.LENGTH_LONG).show();

        }
        if (count <= 4) {
            count++;
            SharedPreferences.Editor editor = spf.edit();
            editor.putString("counter", String.valueOf(count));
            spf = getApplicationContext().getSharedPreferences("counter", Context.MODE_PRIVATE);
            editor.apply();
            Log.i("paras", String.valueOf(count));
            if (count == 1) {
                SharedPreferences spf1 = getApplicationContext().getSharedPreferences("trig1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = spf1.edit();
                editor1.putString("trig1", String.valueOf(MainActivity.trigid));
                editor1.apply();

                spf1 = getApplicationContext().getSharedPreferences("ev1", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev1", String.valueOf(MainActivity.trigeven));
                editor1.apply();

                SharedPreferences spf2 = getApplicationContext().getSharedPreferences("evn1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = spf2.edit();
                editor2.putString("evn1", String.valueOf(id));
                editor2.apply();


                spf1 = getApplicationContext().getSharedPreferences("trig2", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("trig2", String.valueOf(-1));
                editor1.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev2", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev2", String.valueOf(-1));
                editor1.apply();

                spf2 = getApplicationContext().getSharedPreferences("evn2", Context.MODE_PRIVATE);
                editor2 = spf2.edit();
                editor2.putString("evn2", String.valueOf(-1));
                editor2.apply();

                spf1 = getApplicationContext().getSharedPreferences("trig3", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("trig3", String.valueOf(-1));
                editor1.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev3", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev3", String.valueOf(-1));
                editor1.apply();

                spf2 = getApplicationContext().getSharedPreferences("evn3", Context.MODE_PRIVATE);
                editor2 = spf2.edit();
                editor2.putString("evn3", String.valueOf(-1));
                editor2.apply();

                spf1 = getApplicationContext().getSharedPreferences("trig4", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("trig4", String.valueOf(-1));
                editor1.apply();

                spf1 = getApplicationContext().getSharedPreferences("ev4", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev4", String.valueOf(-1));
                editor1.apply();

                spf2 = getApplicationContext().getSharedPreferences("evn4", Context.MODE_PRIVATE);
                editor2 = spf2.edit();
                editor2.putString("evn4", String.valueOf(-1));
                editor2.apply();
                spf1 = getApplicationContext().getSharedPreferences("trig5", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("trig5", String.valueOf(-1));
                editor1.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev5", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev5", String.valueOf(-1));
                editor1.apply();

                spf2 = getApplicationContext().getSharedPreferences("evn5", Context.MODE_PRIVATE);
                editor2 = spf2.edit();
                editor2.putString("evn5", String.valueOf(-1));
                editor2.apply();


                Log.i("paras", String.valueOf(MainActivity.trigid));
                Log.i("paras", String.valueOf(id));
            } else if (count == 2) {
                SharedPreferences spf1 = getApplicationContext().getSharedPreferences("trig2", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = spf1.edit();
                editor1.putString("trig2", String.valueOf(MainActivity.trigid));
                editor1.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev2", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev2", String.valueOf(MainActivity.trigeven));
                editor1.apply();
                SharedPreferences spf2 = getApplicationContext().getSharedPreferences("evn2", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = spf2.edit();
                editor2.putString("evn2", String.valueOf(id));
                editor2.apply();

                spf1 = getApplicationContext().getSharedPreferences("trig3", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("trig3", String.valueOf(-1));
                editor1.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev3", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev3", String.valueOf(-1));
                editor1.apply();

                spf2 = getApplicationContext().getSharedPreferences("evn3", Context.MODE_PRIVATE);
                editor2 = spf2.edit();
                editor2.putString("evn3", String.valueOf(-1));
                editor2.apply();

                spf1 = getApplicationContext().getSharedPreferences("trig4", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("trig4", String.valueOf(-1));
                editor1.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev4", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev4", String.valueOf(-1));
                editor1.apply();


                spf2 = getApplicationContext().getSharedPreferences("evn4", Context.MODE_PRIVATE);
                editor2 = spf2.edit();
                editor2.putString("evn4", String.valueOf(-1));
                editor2.apply();
                spf1 = getApplicationContext().getSharedPreferences("trig5", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("trig5", String.valueOf(-1));
                editor1.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev5", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev5", String.valueOf(-1));
                editor1.apply();

                spf2 = getApplicationContext().getSharedPreferences("evn5", Context.MODE_PRIVATE);
                editor2 = spf2.edit();
                editor2.putString("evn5", String.valueOf(-1));
                editor2.apply();


                Log.i("paras", String.valueOf(MainActivity.trigid));
                Log.i("paras", String.valueOf(id));
            } else if (count == 3) {
                SharedPreferences spf1 = getApplicationContext().getSharedPreferences("trig3", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = spf1.edit();
                editor1.putString("trig3", String.valueOf(MainActivity.trigid));
                editor1.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev3", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev3", String.valueOf(MainActivity.trigeven));
                editor1.apply();

                SharedPreferences spf2 = getApplicationContext().getSharedPreferences("evn3", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = spf2.edit();
                editor2.putString("evn3", String.valueOf(id));
                editor2.apply();

                spf1 = getApplicationContext().getSharedPreferences("trig4", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("trig4", String.valueOf(-1));
                editor1.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev4", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev4", String.valueOf(-1));
                editor1.apply();

                spf2 = getApplicationContext().getSharedPreferences("evn4", Context.MODE_PRIVATE);
                editor2 = spf2.edit();
                editor2.putString("evn4", String.valueOf(-1));
                editor2.apply();
                spf1 = getApplicationContext().getSharedPreferences("trig5", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("trig5", String.valueOf(-1));
                editor1.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev5", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev5", String.valueOf(-1));
                editor1.apply();

                spf2 = getApplicationContext().getSharedPreferences("evn5", Context.MODE_PRIVATE);
                editor2 = spf2.edit();
                editor2.putString("evn5", String.valueOf(-1));
                editor2.apply();


                Log.i("paras", String.valueOf(MainActivity.trigid));
                Log.i("paras", String.valueOf(id));
            } else if (count == 4) {
                SharedPreferences spf1 = getApplicationContext().getSharedPreferences("trig4", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = spf1.edit();
                editor1.putString("trig4", String.valueOf(MainActivity.trigid));
                editor1.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev4", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev4", String.valueOf(MainActivity.trigeven));
                editor1.apply();

                SharedPreferences spf2 = getApplicationContext().getSharedPreferences("evn4", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = spf2.edit();
                editor2.putString("evn4", String.valueOf(id));
                editor2.apply();
                spf1 = getApplicationContext().getSharedPreferences("trig5", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("trig5", String.valueOf(-1));
                editor1.apply();


                spf2 = getApplicationContext().getSharedPreferences("evn5", Context.MODE_PRIVATE);
                editor2 = spf2.edit();
                editor2.putString("evn5", String.valueOf(-1));
                editor2.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev5", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev5", String.valueOf(-1));
                editor1.apply();
                Log.i("paras", String.valueOf(MainActivity.trigid));
                Log.i("paras", String.valueOf(id));
            } else if (count == 5) {
                SharedPreferences spf1 = getApplicationContext().getSharedPreferences("trig5", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = spf1.edit();
                editor1.putString("trig5", String.valueOf(MainActivity.trigid));
                editor1.apply();

                SharedPreferences spf2 = getApplicationContext().getSharedPreferences("evn5", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = spf2.edit();
                editor2.putString("evn5", String.valueOf(id));
                editor2.apply();
                spf1 = getApplicationContext().getSharedPreferences("ev5", Context.MODE_PRIVATE);
                editor1 = spf1.edit();
                editor1.putString("ev5", String.valueOf(MainActivity.trigeven));
                editor1.apply();

                Log.i("paras", String.valueOf(MainActivity.trigid));
                Log.i("paras", String.valueOf(id));
            }
            if(count==5)
            {abcd=func8(MainActivity.trigid,(int) id,(int)MainActivity.trigeven);}
            if(count==4)
            {abcd=func8(MainActivity.trigid,(int) id,(int)MainActivity.trigeven);}
            if(count==3)
            {abcd=func8(MainActivity.trigid,(int) id,(int)MainActivity.trigeven);}
            if(count==2)
            {abcd=func8(MainActivity.trigid,(int) id,(int)MainActivity.trigeven);}
            if(count==1)
            {abcd=func8(MainActivity.trigid,(int) id,(int)MainActivity.trigeven);}
            speakOut();

            Toast.makeText(getApplicationContext(), "Trigger Created Successfully", Toast.LENGTH_LONG).show();
            Intent nextScree = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(nextScree);

        }

    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.UK);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                //btnSpeak.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    private void speakOut() {
        String text = "Trigger " + triggerName.getText() + " set for action " + classes2[6];

        tts.speak(abcd, TextToSpeech.QUEUE_FLUSH, null);
    }

}
