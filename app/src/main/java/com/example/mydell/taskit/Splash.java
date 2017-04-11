package com.example.mydell.taskit;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.Locale;

public class Splash extends Activity implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        tts = new TextToSpeech(this,this);


       int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECEIVE_SMS);
        if (result == PackageManager.PERMISSION_GRANTED){



        } else {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},1);

        }


        result = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.SYSTEM_ALERT_WINDOW);
        if (result == PackageManager.PERMISSION_GRANTED){

            //startService(new Intent(getApplicationContext(),FloatingViewService.class));

        } else {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this) ){
                // Show alert dialog to the user saying a separate permission is needed
                // Launch the settings activity if the user prefers
                Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(myIntent);
            }


        }

        result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED){



        } else {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

        }

        result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED){



        } else {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);

        }
        result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALL_LOG);
        if (result == PackageManager.PERMISSION_GRANTED){



        } else {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_CALL_LOG},1);

        }


      /*  ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.RECEIVE_SMS},1);
         ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);*/
        Thread timer=new Thread()
        {
            public void run()
            {
                try{
                    speakOut();
                        sleep(5000);
                }catch (InterruptedException e){e.printStackTrace();}
                finally {
                    Intent ourintent=new Intent("com.example.mydell.taskit");
                    startActivity(ourintent);

                }
            }

        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
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
    private void speakOut(){
        String text = "Taskit makes your life easy";

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}
