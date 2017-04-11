package com.example.mydell.taskit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.CallLog;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

/**
 * Created by Gaurang on 16-11-2016.
 */


public class TriggerService extends Service implements SensorEventListener{
    private SensorManager sensorManager1;
    int[] arr =new int[100];
    int trig1;int trig2;int trig3;int trig4;int trig5;
    int even1;int even2;int even3;int even4;int even5;
    int ev1;int ev2;int ev3;int ev4;int ev5;

    private CameraManager mCameraManager;
    String mCameraId="";
    int count5;
    Camera mCamera;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast;
    private int counter=0,counter2=0;
    long firstmovetime=0;
    long now=0;
    public int flag=0;
    int counter1=0;
    long firstmovetime1=0;
    int prev=0;
    long now1=0;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    openFileAction openFile = new openFileAction();
   /* @TargetApi(Build.VERSION_CODES.M)*/

    private void enableAP(boolean enable) {
        WifiManager wifi_manager = (WifiManager) this
                .getSystemService(WIFI_SERVICE);

        wifi_manager.setWifiEnabled(false);

        try {
            // USE REFLECTION TO GET METHOD "SetWifiAPEnabled"
            Method method = wifi_manager.getClass().getMethod(
                    "setWifiApEnabled", WifiConfiguration.class, boolean.class);
            method.invoke(wifi_manager, null, enable);
        } catch (NoSuchMethodException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            /* TODO Auto-generated catch block */
            e.printStackTrace();
        }
    }

    public void showAlert(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton("OK",null);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    private boolean IsWifiApEnabled() {
        boolean isWifiAPEnabled = false;
        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        Method[] wmMethods = wifi.getClass().getDeclaredMethods();
        for (Method method : wmMethods) {
            if (method.getName().equals("isWifiApEnabled")) {
                try {
                    isWifiAPEnabled = (Boolean) method.invoke(wifi);
                } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return isWifiAPEnabled;
    }
    public void showNotification() {
        SharedPreferences sp = getSharedPreferences("TaskIt", MODE_PRIVATE);
        String title = sp.getString("title","");
        String subtitle = sp.getString("subtitle","");

        Uri NotificationSound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notifysnd);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(subtitle)
                        .setSound(NotificationSound)
                        .setDefaults(~Notification.DEFAULT_SOUND);



        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void func2(int i)

    {
        if (i == 0) {

            BluetoothAdapter mBluetoothAdapter;
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            SharedPreferences spf=getSharedPreferences("z22",Context.MODE_PRIVATE);
            String abc= spf.getString("z22", String.valueOf(-1));

            if(abc.equals(String.valueOf(1))) {

                mBluetoothAdapter.enable();

            } else if(abc.equals(String.valueOf(0))){
               mBluetoothAdapter.disable();

            }
        }
        if(i==1)
        {        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {}
        else {

            Settings.System.putInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);

/*
            android.provider.Settings.System.putInt(getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE,
                    android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

            android.provider.Settings.System.putInt(getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS,
                    1000);*/
        }

        }
        if(i==2) { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        }
        else {
            SharedPreferences  spf=getSharedPreferences("zbr",Context.MODE_PRIVATE);
            int    ev= Integer.parseInt(spf.getString("zbr",String.valueOf(-1)));

           /* Settings.System.putInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);*/

            Settings.System.putInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS,
                    ev*10);
        }
        }
        if(i==3)
        {
            showNotification();
        }
        if(i==4)
        {
            SharedPreferences spf=getSharedPreferences("z26",Context.MODE_PRIVATE);
            String abc= spf.getString("z26", String.valueOf(-1));

            if(abc.equals(String.valueOf(1))) {


                enableAP(true);
            }
            if(abc.equals(String.valueOf(0)))
            enableAP(false);



        }
        if(i==5) {

//Toast.makeText(getApplicationContext(),"Killing apps",Toast.LENGTH_LONG).show();
            ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfo = am.getRunningAppProcesses();

            /*List<ApplicationInfo> packages;
            PackageManager pm;
            pm = getPackageManager();*/
            SharedPreferences sharedPref = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);
            int apps = sharedPref.getInt("appsSelected",0);
            String[] closeApp = new String[apps];// = sharedPref.getString("")
            for (int j = 0; j < apps; j++) {
                //Log.i("APP NAME",a[j]);

                closeApp[j] = sharedPref.getString("app" + j, "");
         //       Toast.makeText(getApplicationContext(),j +" -- " +closeApp[j],Toast.LENGTH_LONG).show();
            }
            //get a list of installed apps.
            //packages = pm.getInstalledApplications(0);

            //ActivityManager mActivityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            String myPackage = getApplicationContext().getPackageName();

            for ( ActivityManager.RunningAppProcessInfo appProcess: runningAppProcessInfo ) {
                //Log.d(appProcess.processName.toString(), "is running");
                if (appProcess.processName.equals(myPackage)) continue;
                for (int j = 0; j < apps; j++) {
                    if(appProcess.processName.equals(closeApp[j])){
           //             Toast.makeText(getApplicationContext(),closeApp[j],Toast.LENGTH_LONG).show();
                        am.killBackgroundProcesses(closeApp[j]);
                    }
                }
            }
            //for (ApplicationInfo packageInfo : packages) {
                /*if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) continue;
                if (packageInfo.packageName.equals(myPackage)) continue;
                for (int j = 0; j < apps; j++) {
                    //Log.i("APP NAME",a[j]);
                    if(packageInfo.packageName.equals(closeApp[j])){
                        Log.i("App Closed",closeApp[j]);
                        mActivityManager.killBackgroundProcesses(closeApp[j]);
                    }

                }*/

        }
        if(i==6)
        {  SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("zs2",MODE_PRIVATE);
            String url = sharedPreferences.getString("zs2","com.whatsapp");

            Intent gh = new Intent(Intent.ACTION_MAIN);
            PackageManager managerclock = getPackageManager();
            gh = managerclock.getLaunchIntentForPackage(url);

            gh.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(gh);



        }
        if(i==7)
        {Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);



        }
        if(i==8)
        {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE);
            }


        }
        if(i==9) {
            Intent callLogIntent = new Intent();

            callLogIntent.setAction(Intent.ACTION_VIEW);
            callLogIntent.setType(CallLog.Calls.CONTENT_TYPE);
            callLogIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(callLogIntent);
           /*// Cursor cur = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
            int numberInd = cur.getColumnIndex(CallLog.Calls.NUMBER);
            int typeInd = cur.getColumnIndex(CallLog.Calls.TYPE);
            int dateInd = cur.getColumnIndex(CallLog.Calls.DATE);
            int durationInd = cur.getColumnIndex(CallLog.Calls.DURATION);

            while (cur.moveToNext()) {
                String number = cur.getString(numberInd);
                String type = cur.getString(typeInd);
                String date = cur.getString(dateInd);
                String duration = cur.getString(durationInd);
                int callCode = Integer.parseInt(type);
                // callCode can be OUTGOING_TYPE, INCOMING_TYPE or MISSED_TYPE
                Log.i("Call Id",number+"   "+date+"    ");*/

        }
        else if(i==11){
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("TaskIt",MODE_PRIVATE);
            String url = sharedPreferences.getString("url","");
            if (!url.startsWith("https://") && !url.startsWith("http://")){
                url = "http://" + url;
            }
            Intent openWebsite = new Intent(Intent.ACTION_VIEW);
            openWebsite.setData(Uri.parse(url));
            openWebsite.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openWebsite);
        }
        else if(i==10){

            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("TaskIt",MODE_PRIVATE);
            String url = sharedPreferences.getString("FileLocation","");
            File file = new File(url);
            Uri path = Uri.parse(url);
            Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
            pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            pdfOpenintent.setDataAndType(path, "*/*");
            startActivity(pdfOpenintent);

        }
        if(i==12)
        {}
        if(i==13)
        {PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            boolean isScreenOn = pm.isScreenOn();


            if (isScreenOn == false) {





            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyLock");

            wl.acquire(10000);
            PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock");

            wl_cpu.acquire(10000);  }

        }
        if(i==14)
        {     SharedPreferences spf=getSharedPreferences("z2151",Context.MODE_PRIVATE);
            String abc= spf.getString("z2151", String.valueOf(0));
            spf=getSharedPreferences("z2152",Context.MODE_PRIVATE);
            String abc1= spf.getString("z2152", String.valueOf(0));
            spf=getSharedPreferences("z2153",Context.MODE_PRIVATE);
            String abc2= spf.getString("z2153", String.valueOf(0));



            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");

            //Log.i("ACA","abc"+abc);
            emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[] { abc.toString() });
            //  emailIntent.putExtra(Intent.EXTRA_CC, "K");
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, abc1);
            emailIntent.putExtra(Intent.EXTRA_TEXT, abc2);

            try {
            /*Intent i = new Intent().setClass(mActivity.getApplication(), TestUserProfileScreenActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

// Launch the new activity and add the additional flags to the intent
            mActivity.getApplication().startActivity(i);
            */
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(emailIntent);
          /*  Intent.createChooser(emailIntent, "Send mail...")*/
            } catch (ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(),
                        "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }


        }
        if(i==15)
        { SmsManager sm = SmsManager.getDefault();

            SharedPreferences spf=getSharedPreferences("z2171",Context.MODE_PRIVATE);
            String abc= spf.getString("z2171", String.valueOf(0));
            spf=getSharedPreferences("z2172",Context.MODE_PRIVATE);
            String abc1= spf.getString("z2172", String.valueOf(0));
            sm.sendTextMessage(abc, null, abc1, null, null);


        }
        if(i==16)
        {
            AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            audioManager.setSpeakerphoneOn(true);

        }
        if(i==17)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                    try {
                        mCameraId = mCameraManager.getCameraIdList()[0];
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }

                    try {
                        mCameraManager.setTorchMode(mCameraId, true);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }


                } else {
                    {

                        if (getApplicationContext().getPackageManager()
                                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {


                            mCamera = Camera.open();
                            Camera.Parameters parameters = mCamera.getParameters();
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                            mCamera.setParameters(parameters);
                            mCamera.startPreview();

                        } else {
                            AlertDialog alert = new AlertDialog.Builder(this).create();
                            alert.setTitle("Error");
                            alert.setMessage("Sorry, your device doesn't support flash light!");
                            alert.show();
                        }
                    }
                }


        }
        if(i==18)
        {



            Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);

        }
        if(i==19)
        {  SharedPreferences spf=getSharedPreferences("z221",Context.MODE_PRIVATE);
            String abc= spf.getString("z221", String.valueOf(-1));

            AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,Integer.parseInt(abc), AudioManager.FLAG_SHOW_UI);

        }
        if(i==20)
        {

            SharedPreferences spf=getSharedPreferences("z222",Context.MODE_PRIVATE);
            String abc= spf.getString("z222", String.valueOf(-1));

            if(abc.equals(String.valueOf(1)))
            {
            WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);}
            if(abc.equals(String.valueOf(0)))
            {
                WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(false);}


        }
         if(i==21)
        {    AudioManager audio;
            audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);

        }
        if(i==22)
        {    AudioManager audio;
            audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

            audio.setStreamVolume(AudioManager.STREAM_RING, audio.getStreamMaxVolume(AudioManager.STREAM_RING), AudioManager.FLAG_SHOW_UI);

        }
         if(i==23)
        {
            AudioManager audio;
            audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

            audio.setStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);

        }
         if(i==24)
        {


        }


        /*else if (i == 2) {

        } else if (i == 3) {



        } else if (i == 4) {

        } else if (i == 5) {

        } else if (i == 6) {


        } else if (i == 7) {

        } else if (i == 8) {


        } else if (i == 9) {

        } *//*else if (i == 10) {


        } else if (i == 11) {



        }
*//*
        else if(i==12)
    {
      *//*  ContentResolver cr = getContentResolver();
//Where
        String sel = CallLog.Calls.DATE + " <?";
//get the date backed to the last 7 days
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date dt = cal.getTime();
//Selection args
        String[] selArgs = {String.valueOf(dt.getTime())};
//delete old call logs

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        int num_deleted = cr.delete(CallLog.Calls.CONTENT_URI, sel, selArgs);
        Toast.makeText(this, num_deleted+" items are deleted.", Toast.LENGTH_SHORT).show();*//*
    }

        else if (i == 13) {



        } else if (i == 14) {

            *//*android.provider.Settings.System.putInt(getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        *//*} else if (i == 15) {


        } else if (i == 16) {




            }

        else if(i==17)
        {
            }
     else if(i==20){
            *//*Activity c = openFile.getC();
            if(c!=null)
                c.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);*//*
            *//*int flag=0
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
            mLayoutParams.flags = flag;
            mWindowManager.updateViewLayout(mRootView, mLayoutParams);*//*
        }*/

        }




    private BroadcastReceiver Receiver2 = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            SharedPreferences spf=getSharedPreferences("trig1",Context.MODE_PRIVATE);
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



            String action = intent.getAction();
            if (intent.getAction().intern() == Intent.ACTION_AIRPLANE_MODE_CHANGED)
            {
                if(Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) != 0 )
                {
                    // Toast.makeText(getApplicationContext() ,"airplane mode",Toast.LENGTH_LONG).show();
                    if(trig1==0 &&ev1==1)
                    {func2(even1);
                    }
                    if(trig2==0 &&ev2==1)
                    {func2(even2);}
                    if(trig3==0&&ev3==1)
                    {func2(even3);}
                    if(trig4==0 &&ev4==1)
                    {func2(even4);}
                    if(trig5==0 &&ev5==1)
                    {func2(even5);}

                }
                else if (Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) == 0)
                {
                    if(trig1==0 &&ev1==0)
                    {func2(even1);
                    }
                    if(trig2==0 &&ev2==0)
                    {func2(even2);}
                    if(trig3==0&&ev3==0)
                    {func2(even3);}
                    if(trig4==0 &&ev4==0)
                    {func2(even4);}
                    if(trig5==0 &&ev5==0)
                    {func2(even5);}
                }

            }


                if(action.equals("android.provider.Telephony.SMS_RECEIVED")){
                    if(trig1==21)
                    {func2(even1);
                    }
                    if(trig2==21)
                    {func2(even2);}
                    if(trig3==21)
                    {func2(even3);}
                    if(trig4==21)
                    {func2(even4);}
                    if(trig5==21)
                    {func2(even5);}


                }
            if(action.equals("android.intent.action.USER_PRESENT") )


            {                    if(trig1==18 )
            {func2(even1);
            }
                if(trig2==18 )
                {func2(even2);}
                if(trig3==18)
                {func2(even3);}
                if(trig4==18 )
                {func2(even4);}
                if(trig5==18 )
                {func2(even5);}
            }



            if(action.equals("android.intent.action.SCREEN_ON") )


            {     if(trig1==17 &&ev1==1)
            {func2(even1);
            }
                if(trig2==17 &&ev2==1)
                {func2(even2);}
                if(trig3==17&&ev3==1)
                {func2(even3);}
                if(trig4==17 &&ev4==1)
                {func2(even4);}
                if(trig5==17 &&ev5==1)
                {func2(even5);}
            }
            if(action.equals("android.intent.action.SCREEN_OFF") )

            { if(trig1==17 &&ev1==0)
            {func2(even1);
            }
                if(trig2==17 &&ev2==0)
                {func2(even2);}
                if(trig3==17&&ev3==0)
                {func2(even3);}
                if(trig4==17 &&ev4==0)
                {func2(even4);}
                if(trig5==17 &&ev5==0)
                {func2(even5);}}





            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        {
                        break;}
                    case BluetoothAdapter.STATE_TURNING_OFF:
                    {
                        if(trig1==3 &&ev1==0)
                        {func2(even1);
                        }
                        if(trig2==3 &&ev2==0)
                        {func2(even2);}
                        if(trig3==3&&ev3==0)
                        {func2(even3);}
                        if(trig4==3 &&ev4==0)
                        {func2(even4);}
                        if(trig5==3 &&ev5==0)
                        {func2(even5);}





                        break;
                    }
                    case BluetoothAdapter.STATE_ON:

                    {   break;
                    }
                    case BluetoothAdapter.STATE_TURNING_ON:

                    { if(trig1==3 &&ev1==1)
                    {func2(even1);
                    }
                        if(trig2==3 &&ev2==1)
                        {func2(even2);}
                        if(trig3==3&&ev3==1)
                        {func2(even3);}
                        if(trig4==3 &&ev4==1)
                        {func2(even4);}
                        if(trig5==3 &&ev5==1)
                        {func2(even5);}


                        break;
                    }
                }
            }



            if(action.equals("android.intent.action.HEADSET_PLUG"))
            {
                int state = intent.getIntExtra("state", -1);
                if (state == 0) {
                    if(trig1==12 &&ev1==0)
                    {func2(even1);
                    }
                    if(trig2==12 &&ev2==0)
                    {func2(even2);}
                    if(trig3==12&&ev3==0)
                    {func2(even3);}
                    if(trig4==12 &&ev4==0)
                    {func2(even4);}
                    if(trig5==12 &&ev5==0)
                    {func2(even5);}

                } else if(state == 1) {
                    if(trig1==12 &&ev1==1)
                    {func2(even1);
                    }
                    if(trig2==12 &&ev2==1)
                    {func2(even2);}
                    if(trig3==12&&ev3==1)
                    {func2(even3);}
                    if(trig4==12 &&ev4==1)
                    {func2(even4);}
                    if(trig5==12 &&ev5==1)
                    {func2(even5);}
                }

            }















                if(intent.getAction().intern() == Intent.ACTION_BATTERY_CHANGED){
                //Log.i("aaa","prev-"+prev);

                spf=getSharedPreferences("z12",Context.MODE_PRIVATE);
                    int z12 = Integer.parseInt(spf.getString("z12", String.valueOf(-1)));
                    spf=getSharedPreferences("z123",Context.MODE_PRIVATE);
                    int z123 = Integer.parseInt(spf.getString("z123", String.valueOf(-1)));
                   // Log.i("aaa","z123-"+z123);
                    //Log.i("aaa","z12-"+z12);

                    int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                    //Log.i("aaa","level-"+level);
                    if((trig1==2 &&ev1==0)||(trig2==2 &&ev2==0)||(trig3==2&&ev3==0)||(trig4==2 &&ev4==0)||(trig5==2 &&ev5==0))
                    {

                        if(level==z12&&prev>level)
                        {

                            if(trig1==2 &&ev1==0)
                            {func2(even1);
                            }
                            if(trig2==2 &&ev2==0)
                            {func2(even2);}
                            if(trig3==2&&ev3==0)
                            {func2(even3);}
                            if(trig4==2 &&ev4==0)
                            {func2(even4);}
                            if(trig5==2 &&ev5==0)
                            {func2(even5);}
                        }
                    }
                     if((trig1==2 &&ev1==1)||(trig2==2 &&ev2==1)||(trig3==2&&ev3==1)||(trig4==2 &&ev4==1)||(trig5==2 &&ev5==1))
                    {
                        if(level==z123&&prev<level)
                        {

                            if(trig1==2 &&ev1==1)
                            {func2(even1);
                            }
                            if(trig2==2 &&ev2==1)
                            {func2(even2);}
                            if(trig3==2&&ev3==1)
                            {func2(even3);}
                            if(trig4==2 &&ev4==1)
                            {func2(even4);}
                            if(trig5==2 &&ev5==1)
                            {func2(even5);}
                        }
                    }





               prev=level;

                }

            if (action.equals( Intent.ACTION_POWER_CONNECTED)) {


              /*  int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
                boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
                if (usbCharge || acCharge)*/

                    if(trig1==15 &&ev1==1)
                    {func2(even1);
                    }
                    if(trig2==15 &&ev2==1)
                    {func2(even2);}
                    if(trig3==15&&ev3==1)
                    {func2(even3);}
                    if(trig4==15 &&ev4==1)
                    {func2(even4);}
                    if(trig5==15 &&ev5==1)
                    {func2(even5);}



                }  if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {

                        if (trig1 == 15 && ev1 == 0) {
                            func2(even1);
                        }
                        if (trig2 == 15 && ev2 == 0) {
                            func2(even2);
                        }
                        if (trig3 == 15 && ev3 == 0) {
                            func2(even3);
                        }
                        if (trig4 == 15 && ev4 == 0) {
                            func2(even4);
                        }
                        if (trig5 == 15 && ev5 == 0) {
                            func2(even5);
                        }


                }


            String s,k;
            //outgoing call
           if(action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
               String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
               if(String.valueOf(number.charAt(0)).equals("+"))
               {
                 s=number.substring(3);
               }
               else if(String.valueOf(number.charAt(0)).equals("0"))
               {s=number.substring(1);}
               else
               {s=number;}
               spf=getSharedPreferences("z18",Context.MODE_PRIVATE);
               String z18 = spf.getString("z18", String.valueOf(-1));

               if(String.valueOf(z18.charAt(0)).equals("+"))
               {
                   k=number.substring(3);
               }
               else if(String.valueOf(z18.charAt(0)).equals("0"))
               {k=number.substring(1);}
               else
               {k=z18;}

               if(s.equals(k))
               {if(trig1==8 )
               {func2(even1);
               }
                   if(trig2==8 )
                   {func2(even2);}
                   if(trig3==8)
                   {func2(even3);}
                   if(trig4==8)
                   {func2(even4);}
                   if(trig5==8)
                   {func2(even5);}



               }

             //  Toast.makeText(context,"Outgoing: "+number, Toast.LENGTH_LONG).show();
           }





           if(action.equals( PhoneStateListener.LISTEN_CALL_STATE))
           {


               spf=getSharedPreferences("z16",Context.MODE_PRIVATE);
               String z18 = spf.getString("z16", String.valueOf(-1));

               spf=getSharedPreferences("z161",Context.MODE_PRIVATE);
               String z161 = spf.getString("z161", String.valueOf(-1));

               if(z161.equals(z18))
               {
            //       Toast.makeText(context,"Incoming: "+z161+" "+z18, Toast.LENGTH_LONG).show();


               }
           }








            if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

                if (intent.getExtras() != null) {
                    NetworkInfo ni = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
                    if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED && ni.getTypeName().equals("WIFI")) {
                        Log.i("par", "Network WIFI" + " connected");
                        if(trig1==9 &&ev1==1)
                        {func2(even1);
                        }
                        if(trig2==9 &&ev2==1)
                        {func2(even2);}
                        if(trig3==9&&ev3==1)
                        {func2(even3);}
                        if(trig4==9 &&ev4==1)
                        {func2(even4);}
                        if(trig5==9 &&ev5==1)
                        {func2(even5);}



                    } else if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED ) {
                        Log.i("par", "Network mobile" + " connected");
                        if(trig1==9 &&ev1==1)
                        {func2(even1);
                        }
                        if(trig2==9 &&ev2==1)
                        {func2(even2);}
                        if(trig3==9&&ev3==1)
                        {func2(even3);}
                        if(trig4==9 &&ev4==1)
                        {func2(even4);}
                        if(trig5==9 &&ev5==1)
                        {func2(even5);}

                    } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
                        //      Log.i("paras", "There's no network connectivity");
                        if(trig1==9 &&ev1==0)
                        {func2(even1);
                        }
                        if(trig2==9 &&ev2==0)
                        {func2(even2);}
                        if(trig3==9&&ev3==0)
                        {func2(even3);}
                        if(trig4==9 &&ev4==0)
                        {func2(even4);}
                        if(trig5==9 &&ev5==0)
                        {func2(even5);}
                    }
                }
            }


            if(action.equals("android.location.PROVIDERS_CHANGED")) {


                ContentResolver contentResolver = context.getContentResolver();
                // Find out what the settings say about which providers are enabled
                int mode = Settings.Secure.getInt(
                        contentResolver, Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_OFF);

                if (mode == Settings.Secure.LOCATION_MODE_OFF) {
                    if(trig1==11 &&ev1==0)
                    {func2(even1);
                    }
                    if(trig2==11 &&ev2==0)
                    {func2(even2);}
                    if(trig3==11&&ev3==0)
                    {func2(even3);}
                    if(trig4==11 &&ev4==0)
                    {func2(even4);}
                    if(trig5==11 &&ev5==0)
                    {func2(even5);}




                } else {

                    LocationManager locationManager = (LocationManager) context.
                            getSystemService(Context.LOCATION_SERVICE);
                    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                            && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                        if(trig1==11 &&ev1==1)
                        {func2(even1);
                        }
                        if(trig2==11 &&ev2==1)
                        {func2(even2);}
                        if(trig3==11&&ev3==1)
                        {func2(even3);}
                        if(trig4==11 &&ev4==1)
                        {func2(even4);}
                        if(trig5==11 &&ev5==1)
                        {func2(even5);}





                    } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        if(trig1==11 &&ev1==1)
                        {func2(even1);
                        }
                        if(trig2==11 &&ev2==1)
                        {func2(even2);}
                        if(trig3==11&&ev3==1)
                        {func2(even3);}
                        if(trig4==11 &&ev4==1)
                        {func2(even4);}
                        if(trig5==11 &&ev5==1)
                        {func2(even5);}


                    } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                        if(trig1==11 &&ev1==1)
                        {func2(even1);}
                        if(trig2==11 &&ev2==1)
                        {func2(even2);}
                        if(trig3==11&& ev3==1)
                        {func2(even3);}
                        if(trig4==11 &&ev4==1)
                        {func2(even4);}
                        if(trig5==11 &&ev5==1)
                        {func2(even5);}

                    }

                }
            }

            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                String packageName = intent.getDataString();
                Log.i("paras", packageName + "package name of the program");
            }
            if (intent.getAction (). equals ("android.intent.action.PACKAGE_REMOVED")) {
                String packageName = intent.getDataString ();
                Log.i("paras", packageName + "package name of the program");
            }



        }
  };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        SharedPreferences spf=getSharedPreferences("trig1",Context.MODE_PRIVATE);

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


        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] < 100.0) {

                if (counter1 == 0) {
                    counter1++;
                    firstmovetime1 = System.currentTimeMillis();
                } else {
                    now1 = System.currentTimeMillis();


                    if ((now1 - firstmovetime1) < 800) {
                        counter1++;
                        firstmovetime1 = now1;
                    } else {
                        counter1 = 0;
                        now1 = 0;
                        firstmovetime1 = System.currentTimeMillis();
                        counter1++;
                    }
                    if (counter1 == 2) {
                        if(trig1==16 &&ev1==1)
                        {func2(even1);
                        }
                        if(trig2==16 &&ev2==1)
                        {func2(even2);}
                        if(trig3==16&&ev3==1)
                        {func2(even3);}
                        if(trig4==16 &&ev4==1)
                        {func2(even4);}
                        if(trig5==16 &&ev5==1)
                        {func2(even5);}

                    }

                }
            }
            if(event.values[0]<3)
            {
                if(trig1==16 &&ev1==0)
                {func2(even1);
                }
                if(trig2==16 &&ev2==0)
                {func2(even2);}
                if(trig3==16&&ev3==0)
                {func2(even3);}
                if(trig4==16 &&ev4==0)
                {func2(even4);}
                if(trig5==16 &&ev5==0)
                {func2(even5);}

            }



        }
        if (mySensor.getType() == Sensor.TYPE_LIGHT)
        {  float currentReading = event.values[0];
            if(trig1==14 &&  (currentReading>ev1-10 && currentReading<ev1+10)     )
            {func2(even1);
            }
            if(trig2==14 &&(currentReading>ev2-10 && currentReading<ev2+10))
            {func2(even2);}
            if(trig3==14&&(currentReading>ev3-10 && currentReading<ev3+10))
            {func2(even3);}
            if(trig4==14 &&(currentReading>ev4-10 && currentReading<ev4+10))
            {func2(even4);}
            if(trig5==14 &&(currentReading>ev5-10 && currentReading<ev5+10))
            {func2(even5);}


        }
            if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                // Log.e("Accelerometer", "X"+x+" Y"+y+" Z"+z);


                {
                    mAccelLast = mAccelCurrent;
                    mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
                    float delta = mAccelCurrent - mAccelLast;
                    mAccel = mAccel * 0.9f + delta;

                    if (mAccel > 5) {
                        if (counter == 0) {
                            counter++;
                            firstmovetime = System.currentTimeMillis();
                        } else {
                            now = System.currentTimeMillis();
                            if ((now - firstmovetime) < 400) {
                                counter++;
                                firstmovetime = now;
                            } else {
                                counter = 0;
                                now = 0;
                                firstmovetime = System.currentTimeMillis();
                                counter++;
                            }
                            if (counter == 2) {
                                if(trig1==19)
                                {func2(even1);
                                }
                                if(trig2==19)
                                {func2(even2);}
                                if(trig3==19)
                                {func2(even3);}
                                if(trig4==19)
                                {func2(even4);}
                                if(trig5==19)
                                {func2(even5);}
                            }
                        }


                    }
                }

                if (z > 0) {
                    flag = 0;
                }

                if (z < 0 && flag == 0)

                {
                    if(trig1==10)
                    {func2(even1);
                    }
                    if(trig2==10)
                    {func2(even2);}
                    if(trig3==10)
                    {func2(even3);}
                    if(trig4==10)
                    {func2(even4);}
                    if(trig5==10)
                    {func2(even5);}


                    flag = 1;
                }


            }


    }
    @Override
    public void onDestroy() {
       unregisterReceiver(Receiver2);
        sensorManager1.unregisterListener(this);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager1.registerListener(this,sensorManager1.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager1.registerListener(this,sensorManager1.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager1.registerListener(this,sensorManager1.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);


        final String str = "";
        Timer timer  =  new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int phonelaunched = 0,phoneclosed =0;
                int phonelaunches = 1;
            int flagb=0,hm,mm;
            int phonelaunched1=0;
            int phoneclosed1=0;
            int phoneclosed2=0;
            int flag=0;

            int phonelaunche1=0;
            int phoneclose1=0;
            int phoneclose2=0;
            int fla=0;

            int phonelaunch1=0;
            int phoneclos1=0;
            int phoneclos2=0;
            int fl=0;

            int  qwe1=0;
            int qwe2=0;
                    int q1=0;

            int phonelaunch3=0;
            int phoneclos3=0;
            int fl3=0;


            @Override
            public void run() {

                SharedPreferences spf = getSharedPreferences("trig1", Context.MODE_PRIVATE);

                trig1 = Integer.parseInt(spf.getString("trig1", String.valueOf(-1)));
                spf = getSharedPreferences("evn1", Context.MODE_PRIVATE);
                even1 = Integer.parseInt(spf.getString("evn1", String.valueOf(-1)));

                spf = getSharedPreferences("counter", Context.MODE_PRIVATE);
                count5 = Integer.parseInt(spf.getString("counter", String.valueOf(0)));

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


                WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                Method[] wmMethods = wifi.getClass().getDeclaredMethods();
                phonelaunched1 = 0;
                for (Method method : wmMethods) {
                    if (method.getName().equals("isWifiApEnabled")) {

                        try {
                            boolean isWifiAPenabled = (boolean) method.invoke(wifi);
                            if (isWifiAPenabled == true) {

                                phonelaunched1 = 1;


                            }
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }

                }


                if (phonelaunched1 == 1) {
                    if (phoneclosed1 == 1) {
                    } else if (phoneclosed1 == 0) {
                        if (trig1 == 13 && ev1 == 1) {
                            func2(even1);
                        }
                        if (trig2 == 13 && ev2 == 1) {
                            func2(even2);
                        }
                        if (trig3 == 13 && ev3 == 1) {
                            func2(even3);
                        }
                        if (trig4 == 13 && ev4 == 1) {
                            func2(even4);
                        }
                        if (trig5 == 13 && ev5 == 1) {
                            func2(even5);
                        }

                        phoneclosed1 = 1;
                    }
                    phoneclosed2 = 0;
                    flag = 1;
                } else if (phonelaunched1 == 0 && flag == 1) {
                    phoneclosed1 = 0;
                    if (phoneclosed2 == 1) {
                    } else if (phoneclosed2 == 0) {
                        if (trig1 == 13 && ev1 == 0) {
                            func2(even1);
                        }
                        if (trig2 == 13 && ev2 == 0) {
                            func2(even2);
                        }
                        if (trig3 == 13 && ev3 == 0) {
                            func2(even3);
                        }
                        if (trig4 == 13 && ev4 == 0) {
                            func2(even4);
                        }
                        if (trig5 == 13 && ev5 == 0) {
                            func2(even5);
                        }

                        phoneclosed2 = 1;
                    }

                }
/*-----------------------------------------------------------------------------------------------____--*/

                phonelaunche1 = 0;
                AudioManager am1 = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                if (am1.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
                    phonelaunche1 = 1;
                }
                if (phonelaunche1 == 1) {
                    if (phoneclose1 == 1) {
                    } else if (phoneclose1 == 0) {
                        if (trig1 == 20 && ev1 == 1) {
                            func2(even1);
                        }
                        if (trig2 == 20 && ev2 == 1) {
                            func2(even2);
                        }
                        if (trig3 == 20 && ev3 == 1) {
                            func2(even3);
                        }
                        if (trig4 == 20 && ev4 == 1) {
                            func2(even4);
                        }
                        if (trig5 == 20 && ev5 == 1) {
                            func2(even5);
                        }
                        phoneclose1 = 1;
                    }
                    phoneclose2 = 0;
                    fla = 1;
                } else if (phonelaunche1 == 0 && fla == 1) {
                    phoneclose1 = 0;
                    if (phoneclose2 == 1) {
                    } else if (phoneclose2 == 0) {
                        if (trig1 == 20 && ev1 == 0) {
                            func2(even1);
                        }
                        if (trig2 == 20 && ev2 == 0) {
                            func2(even2);
                        }
                        if (trig3 == 20 && ev3 == 0) {
                            func2(even3);
                        }
                        if (trig4 == 20 && ev4 == 0) {
                            func2(even4);
                        }
                        if (trig5 == 20 && ev5 == 0) {
                            func2(even5);
                        }

                        phoneclose2 = 1;
                    }

                }

                /*-----------------------------------------------------------------------------------------------____--*/
                phonelaunch1 = 0;
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo ni = cm.getActiveNetworkInfo();
                if (ni != null && ni.getType() == ConnectivityManager.TYPE_WIFI) {
                    phonelaunch1 = 1;

                }


                if (phonelaunch1 == 1) {
                    if (phoneclos1 == 1) {
                    } else if (phoneclos1 == 0) {
                        if (trig1 == 22 && ev1 == 1) {
                            func2(even1);
                        }
                        if (trig2 == 22 && ev2 == 1) {
                            func2(even2);
                        }
                        if (trig3 == 22 && ev3 == 1) {
                            func2(even3);
                        }
                        if (trig4 == 22 && ev4 == 1) {
                            func2(even4);
                        }
                        if (trig5 == 22 && ev5 == 1) {
                            func2(even5);
                        }

                        phoneclos1 = 1;
                    }
                    phoneclos2 = 0;
                    fl = 1;
                } else if (phonelaunch1 == 0 && fl == 1) {
                    phoneclos1 = 0;
                    if (phoneclos2 == 1) {
                    } else if (phoneclos2 == 0) {
                        if (trig1 == 22 && ev1 == 0) {
                            func2(even1);
                        }
                        if (trig2 == 22 && ev2 == 0) {
                            func2(even2);
                        }
                        if (trig3 == 22 && ev3 == 0) {
                            func2(even3);
                        }
                        if (trig4 == 22 && ev4 == 0) {
                            func2(even4);
                        }
                        if (trig5 == 22 && ev5 == 0) {
                            func2(even5);
                        }
                        phoneclos2 = 1;
                    }

                }


                /*-----------------------------------------------------------------------------------------------____--*/
                phonelaunch3=0;
                ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfo = am.getRunningAppProcesses();
                spf = getSharedPreferences("zs", Context.MODE_PRIVATE);
                final String zs = spf.getString("zs", String.valueOf(-1));
                Log.i("qwerty", zs+"DAS");

                for ( ActivityManager.RunningAppProcessInfo appProcess: runningAppProcessInfo ) {
                   // Log.d(appProcess.processName.toString(), "is running");
                    if (appProcess.processName.equals(zs)) {
                        Log.i("qwerty", zs);


                        if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND  /*isForeground(getApplicationContext(),runningAppProcessInfo.get(i).processName)*/) {
                            phonelaunch3 = 1;
                            break;
                /*            if (phonelaunched == 0 ){
                                phonelaunched = 1;
                                //opened
                                Log.i("paras","dude phone has been launched");
                                if(trig1==1 )
                                {func2(even1);
                                }
                                if(trig2==1 )
                                {func2(even2);}
                                if(trig3==1)
                                {func2(even3);}
                                if(trig4==1 )
                                {func2(even4);}
                                if(trig5==2 )
                                {func2(even5);}
                            }
                            else if (phoneclosed == 1){
                                phonelaunches++;
                                phoneclosed = 0;
                                //again opened
                                Log.i("paras",String.valueOf(phonelaunches)+" dude that was counter");
                                if(trig1==1 )
                                {func2(even1);
                                }
                                if(trig2==1 )
                                {func2(even2);}
                                if(trig3==1)
                                {func2(even3);}
                                if(trig4==1 )
                                {func2(even4);}
                                if(trig5==1 )
                                {func2(even5);}
                            }*/

                        }

                    }
                }
                if (phonelaunch3 == 1) {
                    if (qwe1 == 1) {
                    } else if (qwe1 == 0) {
                        if (trig1 == 1 ) {
                            func2(even1);
                        }
                        if (trig2 == 1 ) {
                            func2(even2);
                        }
                        if (trig3 == 1 ) {
                            func2(even3);
                        }
                        if (trig4 == 1 ) {
                            func2(even4);
                        }
                        if (trig5 == 1 ) {
                            func2(even5);
                        }

                        qwe1 = 1;
                    }
                    qwe2 = 0;
                    q1 = 1;
                } else if (phonelaunch3 == 0 && q1 == 1) {
                    qwe1 = 0;
                    if (qwe2 == 1) {
                    } else if (qwe2 == 0) {

                        qwe2 = 1;
                    }

                }

                /*-------------------------------------------------------------------------------------------------------------*/

SharedPreferences sharedPreferences = getSharedPreferences("TaskIt",MODE_PRIVATE);
                int h = sharedPreferences.getInt("hour",0);
                int m = sharedPreferences.getInt("minute",0);

                        final Calendar c = Calendar.getInstance();
                        int h1 = c.get(Calendar.HOUR_OF_DAY);
                        int m1 = c.get(Calendar.MINUTE);
                        if(h1==h && m==m1){
                            if(trig1==23 &&flagb==0)
                            {func2(even1);flagb=1;hm=h;mm=m;
                            }
                            if(trig2==23 &&flagb==0)
                            {func2(even2);flagb=1;hm=h;mm=m;}
                            if(trig3==23 &&flagb==0)
                            {func2(even3);flagb=1;hm=h;mm=m;}
                            if(trig4==23 &&flagb==0)
                            {func2(even4);flagb=1;hm=h;mm=m;}
                            if(trig5==23 &&flagb==0)
                            {func2(even5);flagb=1;hm=h;mm=m;}
                        }
                if((h1==hm && mm+1==m)|| h1>hm){flagb=0;}
              //  Log.i("SAD","h1="+h1+" "+"m1="+m1+" "+"h="+h+" "+"m="+m+" "+flagb);
                try {
                    Log.i("TAG","TRY");
                    if(new ForegroundCheckTask().execute(getApplicationContext()).get() ){
                        Log.i("TAG","stop");
                        //stopService(new Intent(getApplicationContext(), FloatingViewService.class));
                    }else{
                        Log.i("TAG","start");
                       // startService(new Intent(getApplicationContext(),FloatingViewService.class));
                        /*int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SYSTEM_ALERT_WINDOW);
                        if (result == PackageManager.PERMISSION_GRANTED){

                            startService(new Intent(getApplicationContext(),FloatingViewService.class));
                        } else {

                            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},1);

                        }*/

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        },2000,3000);

        return START_STICKY;    }
    public TriggerService() {

    }
    class ForegroundCheckTask extends AsyncTask<Context, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Context... params) {
            final Context context = params[0].getApplicationContext();
            return isAppOnForeground(context);
        }

        private boolean isAppOnForeground(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
            if (appProcesses == null) {
                return false;
            }
            final String packageName = context.getPackageName();
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                    return true;
                }
            }
            return false;
        }
    }
    @Override
    public void onCreate() {

        super.onCreate();




        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        sensorManager1 = (SensorManager) getSystemService(SENSOR_SERVICE);
        PhoneStateChangeListener pscl = new PhoneStateChangeListener();
        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(pscl, PhoneStateListener.LISTEN_CALL_STATE);

        IntentFilter filter1 = new IntentFilter();
        IntentFilter filter2 = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(Receiver2, filter2);


        filter1.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter1.addAction("android.intent.action.HEADSET_PLUG");
        //   filter.addAction(Intent.ACTION_USER_BACKGROUND);
        filter1.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter1.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
        filter1.addAction(Intent.ACTION_DREAMING_STARTED);
        filter1.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        filter1.addAction(Intent.ACTION_UNINSTALL_PACKAGE);
        filter1.addAction(Intent.ACTION_INSTALL_PACKAGE);
        filter1.addAction(Intent.ACTION_POWER_CONNECTED);
        filter1.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter1.addAction(Intent.ACTION_SCREEN_ON);
        filter1.addAction("android.intent.action.SCREEN_ON");
        filter1.addAction(Intent.ACTION_SCREEN_OFF);
        filter1.addAction("android.provider.Telephony.SMS_RECEIVED");
        filter1.addAction(Intent.ACTION_USER_PRESENT);
        filter1.addAction("android.intent.action.USER_PRESENT");
        filter1.addAction("android.intent.action.SCREEN_OFF");
        filter1.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        filter1.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");

       // registerReceiver(Receiver2, filter1);

        registerReceiver(Receiver2, new IntentFilter("android.intent.action.PACKAGE_ADDED"));
        registerReceiver(Receiver2, new IntentFilter("android.intent.action.PACKAGE_REMOVED"));

        registerReceiver(Receiver2, new IntentFilter("android.location.PROVIDERS_CHANGED"));
        registerReceiver(Receiver2, filter1);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
