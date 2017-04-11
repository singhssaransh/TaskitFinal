package com.example.mydell.taskit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class openURL extends AppCompatActivity {
    EditText editText;
    Button b1,b2;
    Context context;
    String nam;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_url);

        nam = getIntent().getStringExtra("triggerName");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .4));

        editText = (EditText) findViewById(R.id.editText2);
        editText.getBackground().setColorFilter(1 , PorterDuff.Mode.SRC_IN);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        context = this;

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(context,MainActivity2.class);
                i.putExtra("triggerName",nam);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("TaskIt",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("url",url);
                editor.apply();
                fu(11);
                /*
                Intent openWebsite = new Intent(Intent.ACTION_VIEW);

                openWebsite.setData(Uri.parse(url));
                openWebsite.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(openWebsite);*/
            }
        });
    }

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

