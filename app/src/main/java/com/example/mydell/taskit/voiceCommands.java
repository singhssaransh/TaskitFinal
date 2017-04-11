package com.example.mydell.taskit;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.util.ArrayList;

public class voiceCommands extends AppCompatActivity {
    private CameraManager mCameraManager;
    String mCameraId="";
    int count5;
    Camera mCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_commands);

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
//moveTaskToBack(true);
        /*DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .1), (int) (height * .1));*/
        try {
            //Toast.makeText(getApplicationContext(),"fa",Toast.LENGTH_LONG).show();
            startActivityForResult(intent, 1001);
        } catch (ActivityNotFoundException e) {

        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
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
            Toast.makeText(getApplicationContext(),"Searched : "+textMatchelist.get(0).toLowerCase(),Toast.LENGTH_LONG).show();
            if (!textMatchelist.isEmpty()) {
                if (textMatchelist.get(0).contains("search")) {
                    String searchQuery = textMatchelist.get(0).replace("search", " ");
                    Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
                    search.putExtra(SearchManager.QUERY, searchQuery);
                    startActivity(search);
                }
                else if(textMatchelist.get(0).toLowerCase().contains("add trigger")){
                    final Intent intent = new Intent(this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else if(textMatchelist.get(0).toLowerCase().contains("add template")){
                    Intent intent=new Intent("com.example.mydell.template");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else if(textMatchelist.get(0).toLowerCase().contains("open forum")){
                    Intent nextScree = new Intent(this , Login.class);
                    startActivity(nextScree);
                }else if(textMatchelist.get(0).toLowerCase().contains("on bluetooth")){
                    BluetoothAdapter mBluetoothAdapter;
                    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    mBluetoothAdapter.enable();
                }else if(textMatchelist.get(0).toLowerCase().contains("off bluetooth")){
                    BluetoothAdapter mBluetoothAdapter;
                    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    mBluetoothAdapter.disable();
                }else if(textMatchelist.get(0).toLowerCase().contains("on flashlight")){

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

                }else if(textMatchelist.get(0).toLowerCase().contains("off flashlight")){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                        try {
                            mCameraId = mCameraManager.getCameraIdList()[0];
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }

                            try {
                                mCameraManager.setTorchMode(mCameraId, false);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }


                    } else
                    {
                      {
                            mCamera.stopPreview();
                            mCamera.release();
                        }
                    }
                }
                else {
                    final Intent intent = new Intent(this, Main3Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
