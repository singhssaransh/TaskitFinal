package com.example.mydell.taskit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class openFileAction extends AppCompatActivity {

    Button b1,b2,b3;
    EditText editText;
    Context context;
    int count=0;
String nam;
    String fileLoc="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file_action);

        nam = getIntent().getStringExtra("triggerName");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .4));

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button3);
        b3 = (Button) findViewById(R.id.button4);
        context = this;
        editText = (EditText) findViewById(R.id.editText);
        if(fileLoc!=null)
            editText.setText(fileLoc);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mediaIntent = new Intent(Intent.ACTION_GET_CONTENT);
                mediaIntent.setType("*/*"); //set mime type as per requirement
                startActivityForResult(mediaIntent,4321);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(context,MainActivity2.class);
                i.putExtra("triggerName",nam);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("TaskIt",MODE_PRIVATE);
              //  Toast.makeText(getApplicationContext(),fileLoc,Toast.LENGTH_LONG).show();
                Log.i("path",fileLoc);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("FileLocation", fileLoc);
                editor.apply();
                fu(10);
                //ts.func2(19);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        File file = new File(Environment.getExternalStorageDirectory(),
                "share_image.jpg");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4321
                && resultCode == Activity.RESULT_OK) {
            Uri videoUri = data.getData();
            fileLoc = videoUri.toString();
            Log.d(file+"", "Video URI= " + videoUri);

            editText.setText(videoUri.toString());
        }
    }
    public Activity getC(){return this;}

    public void fu(int id)
    {
        SharedPreferences spf=getSharedPreferences("counter",Context.MODE_PRIVATE);
        count= Integer.parseInt(spf.getString("counter", String.valueOf(0)));
        if(count==5)
        {

        }
        if(count<=4)
        {                         count++;
            SharedPreferences.Editor editor= spf.edit();
            editor.putString("counter", String.valueOf(count));
            spf=  getApplicationContext().getSharedPreferences("counter", Context.MODE_PRIVATE);
            editor.apply();
            Log.i("paras", String.valueOf(count));
            if(count==1)
            {
                SharedPreferences spf1=  getApplicationContext().getSharedPreferences("trig1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1= spf1.edit();
                editor1.putString("trig1", String.valueOf(MainActivity.trigid));
                editor1.apply();

                spf1=  getApplicationContext().getSharedPreferences("ev1", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev1", String.valueOf(MainActivity.trigeven));
                editor1.apply();

                SharedPreferences spf2=  getApplicationContext().getSharedPreferences("evn1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2= spf2.edit();
                editor2.putString("evn1", String.valueOf(id));
                editor2.apply();


                spf1=  getApplicationContext().getSharedPreferences("trig2", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("trig2", String.valueOf(-1));
                editor1.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev2", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev2", String.valueOf(-1));
                editor1.apply();

                spf2=getApplicationContext().getSharedPreferences("evn2", Context.MODE_PRIVATE);
                editor2= spf2.edit();
                editor2.putString("evn2", String.valueOf(-1));
                editor2.apply();

                spf1=  getApplicationContext().getSharedPreferences("trig3", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("trig3", String.valueOf(-1));
                editor1.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev3", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev3", String.valueOf(-1));
                editor1.apply();

                spf2=getApplicationContext().getSharedPreferences("evn3", Context.MODE_PRIVATE);
                editor2= spf2.edit();
                editor2.putString("evn3", String.valueOf(-1));
                editor2.apply();

                spf1=  getApplicationContext().getSharedPreferences("trig4", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("trig4", String.valueOf(-1));
                editor1.apply();

                spf1=  getApplicationContext().getSharedPreferences("ev4", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev4", String.valueOf(-1));
                editor1.apply();

                spf2=getApplicationContext().getSharedPreferences("evn4", Context.MODE_PRIVATE);
                editor2= spf2.edit();
                editor2.putString("evn4", String.valueOf(-1));
                editor2.apply();
                spf1=  getApplicationContext().getSharedPreferences("trig5", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("trig5", String.valueOf(-1));
                editor1.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev5", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev5", String.valueOf(-1));
                editor1.apply();

                spf2=getApplicationContext().getSharedPreferences("evn5", Context.MODE_PRIVATE);
                editor2= spf2.edit();
                editor2.putString("evn5", String.valueOf(-1));
                editor2.apply();


                Log.i("paras", String.valueOf(MainActivity.trigid));
                Log.i("paras", String.valueOf(id));
            }
            else if(count==2)
            {
                SharedPreferences spf1=  getApplicationContext().getSharedPreferences("trig2", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1= spf1.edit();
                editor1.putString("trig2", String.valueOf(MainActivity.trigid));
                editor1.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev2", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev2", String.valueOf(MainActivity.trigeven));
                editor1.apply();
                SharedPreferences spf2=  getApplicationContext().getSharedPreferences("evn2", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2= spf2.edit();
                editor2.putString("evn2", String.valueOf(id));
                editor2.apply();

                spf1=  getApplicationContext().getSharedPreferences("trig3", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("trig3", String.valueOf(-1));
                editor1.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev3", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev3", String.valueOf(-1));
                editor1.apply();

                spf2=getApplicationContext().getSharedPreferences("evn3", Context.MODE_PRIVATE);
                editor2= spf2.edit();
                editor2.putString("evn3", String.valueOf(-1));
                editor2.apply();

                spf1=  getApplicationContext().getSharedPreferences("trig4", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("trig4", String.valueOf(-1));
                editor1.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev4", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev4", String.valueOf(-1));
                editor1.apply();


                spf2=getApplicationContext().getSharedPreferences("evn4", Context.MODE_PRIVATE);
                editor2= spf2.edit();
                editor2.putString("evn4", String.valueOf(-1));
                editor2.apply();
                spf1=  getApplicationContext().getSharedPreferences("trig5", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("trig5", String.valueOf(-1));
                editor1.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev5", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev5", String.valueOf(-1));
                editor1.apply();

                spf2=  getApplicationContext().getSharedPreferences("evn5", Context.MODE_PRIVATE);
                editor2= spf2.edit();
                editor2.putString("evn5", String.valueOf(-1));
                editor2.apply();


                Log.i("paras", String.valueOf(MainActivity.trigid));
                Log.i("paras", String.valueOf(id));
            }
            else if(count==3)
            {
                SharedPreferences spf1=  getApplicationContext().getSharedPreferences("trig3", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1= spf1.edit();
                editor1.putString("trig3", String.valueOf(MainActivity.trigid));
                editor1.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev3", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev3", String.valueOf(MainActivity.trigeven));
                editor1.apply();

                SharedPreferences spf2=  getApplicationContext().getSharedPreferences("evn3", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2= spf2.edit();
                editor2.putString("evn3", String.valueOf(id));
                editor2.apply();

                spf1=  getApplicationContext().getSharedPreferences("trig4", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("trig4", String.valueOf(-1));
                editor1.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev4", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev4", String.valueOf(-1));
                editor1.apply();

                spf2=                            getApplicationContext().getSharedPreferences("evn4", Context.MODE_PRIVATE);
                editor2= spf2.edit();
                editor2.putString("evn4", String.valueOf(-1));
                editor2.apply();
                spf1=  getApplicationContext().getSharedPreferences("trig5", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("trig5", String.valueOf(-1));
                editor1.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev5", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev5", String.valueOf(-1));
                editor1.apply();

                spf2=getApplicationContext().getSharedPreferences("evn5", Context.MODE_PRIVATE);
                editor2= spf2.edit();
                editor2.putString("evn5", String.valueOf(-1));
                editor2.apply();



                Log.i("paras", String.valueOf(MainActivity.trigid));
                Log.i("paras", String.valueOf(id));
            }
            else if(count==4)
            {
                SharedPreferences spf1=  getApplicationContext().getSharedPreferences("trig4", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1= spf1.edit();
                editor1.putString("trig4", String.valueOf(MainActivity.trigid));
                editor1.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev4", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev4", String.valueOf(MainActivity.trigeven));
                editor1.apply();

                SharedPreferences spf2=  getApplicationContext().getSharedPreferences("evn4", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2= spf2.edit();
                editor2.putString("evn4", String.valueOf(id));
                editor2.apply();
                spf1=  getApplicationContext().getSharedPreferences("trig5", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("trig5", String.valueOf(-1));
                editor1.apply();


                spf2= getApplicationContext().getSharedPreferences("evn5", Context.MODE_PRIVATE);
                editor2= spf2.edit();
                editor2.putString("evn5", String.valueOf(-1));
                editor2.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev5", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev5", String.valueOf(-1));
                editor1.apply();
                Log.i("paras", String.valueOf(MainActivity.trigid));
                Log.i("paras", String.valueOf(id));
            }
            else if(count==5)
            {
                SharedPreferences spf1=  getApplicationContext().getSharedPreferences("trig5", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1= spf1.edit();
                editor1.putString("trig5", String.valueOf(MainActivity.trigid));
                editor1.apply();

                SharedPreferences spf2=  getApplicationContext().getSharedPreferences("evn5", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2= spf2.edit();
                editor2.putString("evn5", String.valueOf(id));
                editor2.apply();
                spf1=  getApplicationContext().getSharedPreferences("ev5", Context.MODE_PRIVATE);
                editor1= spf1.edit();
                editor1.putString("ev5", String.valueOf(MainActivity.trigeven));
                editor1.apply();

                Log.i("paras", String.valueOf(MainActivity.trigid));
                Log.i("paras", String.valueOf(id));
            }

            Intent nextScree = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(nextScree);

        }

    }

}

