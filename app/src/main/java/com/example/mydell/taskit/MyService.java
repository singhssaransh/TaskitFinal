package com.example.mydell.taskit;

import android.*;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Vibrator;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MyService extends Service implements SensorEventListener, LocationListener {
    int scale;
    Context context;
    Camera mCamera;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast;
    private int counter = 0, counter2 = 0;
    long firstmovetime = 0;
    long now = 0;
    boolean islight = true;
    private CameraManager mCameraManager;
    String mCameraId = "";
    BluetoothAdapter bluetoothAdapter;
    int counter1 = 0;
    long firstmovetime1 = 0;
    long now1 = 0;
    private String a, b;
    public int flag = 0;
    Criteria criteria;
    public long prev5;
    String Lati = "", Long = "", resul = "";
    String result = "";
    LocationManager locationManager;
    String mprovider;
    Location location;
    private SensorManager sensorManager;
    Boolean isGPS, isNet;
    private BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {

        @TargetApi(Build.VERSION_CODES.KITKAT)
        public void onReceive(Context context, Intent intent) {
            //context.unregisterReceiver(this);

            SharedPreferences pref = getSharedPreferences("TaskIt", MODE_PRIVATE);

            // Toast.makeText(getApplicationContext(), "Receiver Called", Toast.LENGTH_LONG).show();

            String action = intent.getAction();

            if (action.equals("android.intent.action.HEADSET_PLUG") && pref.getBoolean("item4", false)) {
                int state = intent.getIntExtra("state", -1);
                if (state == 0) {

                } else if (state == 1) {
                    /*Toast.makeText(MyService.this,"dasdasdasdasd",Toast.LENGTH_LONG).show();
                    */
                    Intent launchIntent;

                    SharedPreferences spf = getSharedPreferences("ab", Context.MODE_PRIVATE);
                    a = spf.getString("valuea", "");
                    if (a.isEmpty())
                        launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");

                    else
                        launchIntent = context.getPackageManager().getLaunchIntentForPackage(a);


                    if (launchIntent != null) {
                        context.startActivity(launchIntent);
                    }
/*
                    if(RecyclerAdapter.a.isEmpty())
                     launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");

                    else
                     launchIntent = context.getPackageManager().getLaunchIntentForPackage(RecyclerAdapter.a);



                    if (launchIntent != null) {
                        context.startActivity(launchIntent);
                    }*/

/*

                    final PackageManager pm = getPackageManager();
                    List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);


                    for (ApplicationInfo packageInfo : packages) {

                        if (packageInfo.packageName.equals("com.sonyericsson.music")) {
                            Intent i = new Intent(Intent.ACTION_MAIN);
                            PackageManager managerclock = getPackageManager();
                            i = managerclock.getLaunchIntentForPackage("com.sonyericsson.music");
                            i.addCategory(Intent.CATEGORY_LAUNCHER);
                            startActivity(i);
                            break;

                        } else if (packageInfo.packageName.equals("com.google.android.music")) {
                            Intent i = new Intent(Intent.ACTION_MAIN);
                            PackageManager managerclock = getPackageManager();
                            i = managerclock.getLaunchIntentForPackage("com.google.android.music");
                            i.addCategory(Intent.CATEGORY_LAUNCHER);
                            startActivity(i);
                            break;
                        } else if (packageInfo.packageName.equals("com.musixmatch.android.lyrify")) {
                            Intent i = new Intent(Intent.ACTION_MAIN);
                            PackageManager managerclock = getPackageManager();
                            i = managerclock.getLaunchIntentForPackage("com.musixmatch.android.lyrify");
                            i.addCategory(Intent.CATEGORY_LAUNCHER);
                            startActivity(i);
                            break;
                        } else if (packageInfo.packageName.equals("com.mxtech.videoplayer.pro")) {
                            Intent i = new Intent(Intent.ACTION_MAIN);
                            PackageManager managerclock = getPackageManager();
                            i = managerclock.getLaunchIntentForPackage("com.mxtech.videoplayer.pro");
                            i.addCategory(Intent.CATEGORY_LAUNCHER);
                            startActivity(i);
                            break;
                        } else if (packageInfo.packageName.equals("com.google.android.youtube")) {
                            Intent i = new Intent(Intent.ACTION_MAIN);
                            PackageManager managerclock = getPackageManager();
                            i = managerclock.getLaunchIntentForPackage("com.google.android.youtube");
                            i.addCategory(Intent.CATEGORY_LAUNCHER);
                            startActivity(i);
                            break;
                        }


                    }

*/

                }
            }/*
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, ifilter);*/
            Log.i("BAttery TAG ", "reached");
            if (pref.getBoolean("item6", false)) {
                Log.i("BAttery TAG ", "INSIDE");
                int rawlevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale1 = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;
                if (rawlevel >= 0 && scale1 > 0) {
                    level = (rawlevel * 100) / scale1;
                }

                scale = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

                if (scale == BatteryManager.BATTERY_STATUS_CHARGING) {
                    showNotification("Battery Charging", "Charger is Plugged in (" + level + ")", 1);
                } else if (scale == BatteryManager.BATTERY_STATUS_FULL) {
                    showNotification("Battery Full", "Unplug Charger", 2);
                } else if (level < 15) {
                    showNotification("Battery is Very LOW :( ", "Plug Charger (" + level + ")", 3);
                } /*else if (scale == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                    showNotification("Battery Discharging", "", 4);
                }*/
            }

        }/*
        class MyPhoneStateListener extends PhoneStateListener {

            public void onCallStateChanged(int state, String incomingNumber) {

                Log.d("MyPhoneListener",state+"   incoming no:"+incomingNumber);

                if (state == 1) {

                    String msg = "New Phone Call Event. Incomming Number : "+incomingNumber;
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(getApplicationContext(), msg, duration);
                    toast.show();

                }
            }
        }*/
    };


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(String title, String text, int number) {
        Intent intent = null;
        Uri NotificationSound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notifysnd);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setSound(NotificationSound)
                        .setDefaults(~Notification.DEFAULT_SOUND);


// Creates an explicit intent for an Activity in your app
        if (number == 3) {
            //  Toast.makeText(getApplicationContext(), "Number 3", Toast.LENGTH_LONG).show();
            String packageName = "com.android.settings";

            intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");

            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(intent, 0);

            for (ResolveInfo info : resolveInfoList)
                if (info.activityInfo.packageName.equalsIgnoreCase(packageName)) {
                    final ComponentName cn = new ComponentName(info.activityInfo.packageName, info.activityInfo.name);
                    intent.setComponent(cn);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                }

        } else {
            intent = new Intent(getApplicationContext(), MainActivity.class);
        }

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());
    }

    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void launchCall() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        String package_name = "com.android.calculator2";
        String class_name = "com.android.calculator2.Calculator";
        //   Toast.makeText(this.getApplicationContext(), "PRO2 ", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Intent.ACTION_MAIN);
        PackageManager managerclock = getPackageManager();
       /* if(RecyclerAdapter.b.isEmpty())
        i = managerclock.getLaunchIntentForPackage("com.google.android.youtube");
        else
            i = managerclock.getLaunchIntentForPackage(RecyclerAdapter.b);

        i.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(i);*/
        SharedPreferences spf = getSharedPreferences("ab", Context.MODE_PRIVATE);
        b = spf.getString("valueb", "");
        if (b.isEmpty())
            i = managerclock.getLaunchIntentForPackage("com.google.android.youtube");
        else
            i = managerclock.getLaunchIntentForPackage(b);

        i.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(i);


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        SharedPreferences pref = getSharedPreferences("TaskIt", MODE_PRIVATE);

        if (mySensor.getType() == Sensor.TYPE_PROXIMITY && pref.getBoolean("item5", false)) {
            // Toast.makeText(this.getApplicationContext(), "PROXIMITY"+counter1, Toast.LENGTH_SHORT).show();

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
                        launchCall();
                    }

                }
            }
        }

        if (mySensor.getType() == Sensor.TYPE_PROXIMITY && pref.getBoolean("item0", false)) {
            // Toast.makeText(this.getApplicationContext(), "PROXIMITY"+counter1, Toast.LENGTH_SHORT).show();
            if (event.values[0] == 3.0 || event.values[0] == 0.0) {

                Log.d("paras", String.valueOf(event.values[0]));
                counter2++;
            }
            if (counter2 > 0) {
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                boolean isScreenOn = pm.isScreenOn();


                if (isScreenOn == false) {


                    PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyLock");

                    wl.acquire(10000);
                    PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock");

                    wl_cpu.acquire(10000);

                }
                if (event.values[0] == 1.0 || (event.values[0] == 100.0)) {
                    Log.d("paras", String.valueOf(event.values[0]));

                    counter2 = 0;
                }
            }


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
                        if (counter == 2 && (pref.getBoolean("item3", false) || pref.getBoolean("item1", false))) {

                            if (pref.getBoolean("item3", false)) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                                    try {
                                        mCameraId = mCameraManager.getCameraIdList()[0];
                                    } catch (CameraAccessException e) {
                                        e.printStackTrace();
                                    }

                                    if (islight == true) {
                                        try {
                                            mCameraManager.setTorchMode(mCameraId, true);
                                        } catch (CameraAccessException e) {
                                            e.printStackTrace();
                                        }
                                        islight = false;
                                    } else {
                                        try {
                                            mCameraManager.setTorchMode(mCameraId, false);
                                        } catch (CameraAccessException e) {
                                            e.printStackTrace();
                                        }
                                        islight = true;
                                    }


                                } else {
                                    if (islight == true) {

                                        if (getApplicationContext().getPackageManager()
                                                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {


                                            mCamera = Camera.open();
                                            Parameters parameters = mCamera.getParameters();
                                            parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
                                            mCamera.setParameters(parameters);
                                            mCamera.startPreview();
                                            islight = false;

                                        } else {
                                            AlertDialog alert = new AlertDialog.Builder(this).create();
                                            alert.setTitle("Error");
                                            alert.setMessage("Sorry, your device doesn't support flash light!");
                                            alert.show();
                                        }
                                    } else {
                                        mCamera.stopPreview();
                                        mCamera.release();
                                        islight = true;
                                    }
                                }
                            }
                            if (pref.getBoolean("item1", false)) {

                                SharedPreferences spf = getSharedPreferences("zlay1", Context.MODE_PRIVATE);
                                String abc = spf.getString("zlay1", String.valueOf(-1));
                                spf = getSharedPreferences("zlay2", Context.MODE_PRIVATE);
                                String abc2 = spf.getString("zlay2", String.valueOf(-1));
                                spf = getSharedPreferences("zlay3", Context.MODE_PRIVATE);
                                String abc3 = spf.getString("zlay3", String.valueOf(-1));

                                location = getLocation();
                       //         Toast.makeText(getApplicationContext(), "Women Security" + resul + " " + Lati + " " + Long, Toast.LENGTH_LONG).show();
                                SmsManager sm = SmsManager.getDefault();

                                /*if (Lati == "" && Long == "") {
                                    ContentResolver contentResolver = getContentResolver();
                                    int mode = Settings.Secure.getInt(
                                            contentResolver, Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_OFF);

                                    if (mode == Settings.Secure.LOCATION_MODE_OFF) {
                                        Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                        startActivity(i);
                                    }
                                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                                    criteria = new Criteria();

                                    mprovider = locationManager.getBestProvider(criteria, false);
                                    if (mprovider != null && !mprovider.equals("")) {
                                        if (ActivityCompat.checkSelfPermission(this,
                                            android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                                        }
                                        Location location = locationManager.getLastKnownLocation(mprovider);
                                        locationManager.requestLocationUpdates(mprovider, 500, 1, (LocationListener) this);

                                        if (location != null) {
                                            onLocationChanged(location);


                                        } else {
                                            Toast.makeText(getBaseContext(), "No Location Provider Found Check Your Code", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "msg sent", Toast.LENGTH_LONG).show();
                                    sm.sendTextMessage("" + abc, null, "HELP!!HELP!!HELP!! AT " + resul + " Latitude:" + Lati + " Longitude:" + Long, null, null);
                                }*/
                                if(!abc.equals("null"))
                                sm.sendTextMessage("" +abc, null, "HELP!!HELP!!HELP!! AT " + resul + " Latitude:" + Lati + " Longitude:" + Long , null, null);
                                if(!abc3.equals("null"))
                                    sm.sendTextMessage(""+abc3, null,"HELP!!HELP!!HELP!! AT "+resul+" Latitude:"+Lati+" Longitude:"+Long, null, null);
                                if(!abc2.equals("null"))
                                    sm.sendTextMessage(""+abc2, null, "HELP!!HELP!!HELP!! AT "+resul+" Latitude:"+Lati+" Longitude:"+Long, null, null);
                                Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                v.vibrate(500);


                                //Log.i("ASD",Lati+" "+Long+" "+resul);

                            }

                        }
                    }


                }
            }

            if (z > 0) {
                flag = 0;
            }

            if (z < 0 && flag == 0 && pref.getBoolean("item2", false))

            {
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                if (audioManager.isMusicActive() && !audioManager.isWiredHeadsetOn()) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                    //  Toast.makeText(this.getApplicationContext(), "Flipped ", Toast.LENGTH_SHORT).show();


                }


                flag = 1;
            }

          /*  if((y>7||x>7||x<-7) && pref.getBoolean("item0",false))

            {
                // Toast.makeText(this.getApplicationContext(), "now ", Toast.LENGTH_SHORT).show();


                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);

                boolean isScreenOn = pm.isScreenOn();

                Log.i("screen on.", "" + isScreenOn);

                if (isScreenOn == false) {

                    PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyLock");

                    wl.acquire(10000);
                    PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock");

                    wl_cpu.acquire(10000);
                }

            }*/

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public void onCreate() {
        super.onCreate();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction("android.intent.action.HEADSET_PLUG");
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        registerReceiver(batteryLevelReceiver, filter);


    }

    @Override
    public void onLocationChanged(Location location) {
        // TextView longitude = (TextView) findViewById(R.id.textView);
        //TextView latitude = (TextView) findViewById(R.id.textView1);

        //  longitude.setText("Current Longitude:" + location.getLongitude());
        // latitude.setText("Current Latitude:" + location.getLatitude());

        Lati = "" + location.getLatitude();
        Long = "" + location.getLongitude();
        String filterAddress = "";

        /*Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses =
                    geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append("\n");
                }

                // sb.append(address.getLocality()).append("\n");
                //sb.append(address.getPostalCode()).append("\n");
                sb.append(address.getCountryName());
                result = sb.toString();
                //       TextView textview = (TextView)findViewById(R.id.test);
                //  textview.setText(result);
                resul = "" + result;
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e2) {
            // TODO: handle exception
            e2.printStackTrace();
        }*/
        //stop location updates

    }


    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onStart(Intent intent, int startId) {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        sensorManager.unregisterListener(this);
        unregisterReceiver(batteryLevelReceiver);

        // Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNet = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPS && !isNet) {
            //    Toast.makeText(getApplicationContext(), isGPS + "1" + isNet, Toast.LENGTH_LONG).show();
            } else if (!isGPS && isNet) {
             //   Toast.makeText(getApplicationContext(), isGPS + "2" + isNet, Toast.LENGTH_LONG).show();

                if (isNet) {
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    }
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 1, this);
                    if (locationManager != null) {
                        //Toast.makeText(getApplicationContext(), isGPS + "lm not null" + isNet, Toast.LENGTH_LONG).show();

                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            Lati = location.getLatitude() + "";
                            Long = location.getLongitude() + "";
                        }
                    } else {
                        //Toast.makeText(getApplicationContext(), isGPS + "lm null" + isNet, Toast.LENGTH_LONG).show();
                    }if(isNetworkAvailable()){
                        Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
                        try {
                            //Toast.makeText(getApplicationContext(), isGPS+"geocoder executed"+isNet, Toast.LENGTH_LONG).show();

                            List<Address> addresses =
                                    geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (addresses != null && addresses.size() > 0) {
                                Address address = addresses.get(0);
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                                    sb.append(address.getAddressLine(i)).append("\n");
                                }

                                // sb.append(address.getLocality()).append("\n");
                                //sb.append(address.getPostalCode()).append("\n");
                                sb.append(address.getCountryName());
                                result = sb.toString();
                                //       TextView textview = (TextView)findViewById(R.id.test);
                                //  textview.setText(result);
                                resul = "" + result;
                            }


                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (Exception e2) {
                            // TODO: handle exception
                            e2.printStackTrace();
                        }
                    }else{

                    }



                }
            } else if (isGPS && !isNet) {
        //        Toast.makeText(getApplicationContext(), isGPS + "3" + isNet, Toast.LENGTH_LONG).show();
                if (location == null) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            3000,
                            1, this);
                    Log.d("GPS", "GPS Enabled");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            Lati = "" + location.getLatitude();
                            Long = "" + location.getLongitude();
                        }
                    }
                }
            } else {
        //        Toast.makeText(getApplicationContext(), isGPS + "4" + isNet, Toast.LENGTH_LONG).show();
                if (isNet) {
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    }
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 1, this);
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            Lati = location.getLatitude() + "";
                            Long = location.getLongitude() + "";
                        }
                    }
                    if(isNetworkAvailable()){
                        Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
                        try {
                            List<Address> addresses =
                                    geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (addresses != null && addresses.size() > 0) {
                                Address address = addresses.get(0);
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                                    sb.append(address.getAddressLine(i)).append("\n");
                                }

                                // sb.append(address.getLocality()).append("\n");
                                //sb.append(address.getPostalCode()).append("\n");
                                sb.append(address.getCountryName());
                                result = sb.toString();
                                //       TextView textview = (TextView)findViewById(R.id.test);
                                //  textview.setText(result);
                                resul = "" + result;
                            }


                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (Exception e2) {
                            // TODO: handle exception
                            e2.printStackTrace();
                        }
                    }else{

                    }



                }
                if (isGPS) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                3000,
                                1, this);
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                Lati = "" + location.getLatitude();
                                Long = "" + location.getLongitude();
                            }
                        }
                        if(isNetworkAvailable()){
                            Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
                            try {
                                List<Address> addresses =
                                        geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                if (addresses != null && addresses.size() > 0) {
                                    Address address = addresses.get(0);
                                    StringBuilder sb = new StringBuilder();
                                    for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                                        sb.append(address.getAddressLine(i)).append("\n");
                                    }

                                    // sb.append(address.getLocality()).append("\n");
                                    //sb.append(address.getPostalCode()).append("\n");
                                    sb.append(address.getCountryName());
                                    result = sb.toString();
                                    //       TextView textview = (TextView)findViewById(R.id.test);
                                    //  textview.setText(result);
                                    resul = "" + result;
                                }


                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (Exception e2) {
                                // TODO: handle exception
                                e2.printStackTrace();
                            }
                        }else{

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

