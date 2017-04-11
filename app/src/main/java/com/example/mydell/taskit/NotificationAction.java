package com.example.mydell.taskit;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.util.Objects;

public class NotificationAction extends AppCompatActivity {

    int count=0;
    EditText title, subtitle;
    Button cancel, ok;
    TextView warning;
    String nam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_action);

        nam = getIntent().getStringExtra("triggerName");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .7));

        warning = (TextView) findViewById(R.id.warning);
        title = (EditText) findViewById(R.id.notifyTitle);
        subtitle = (EditText) findViewById(R.id.notifySubTitle);
        cancel = (Button) findViewById(R.id.cancel);
        ok = (Button) findViewById(R.id.ok);
        warning.setVisibility(View.GONE);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nTitle = title.getText().toString();
                final String nSubTitle = subtitle.getText().toString();
                SharedPreferences sp = getSharedPreferences("TaskIt", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
Boolean c = false;
                if (!Objects.equals(nTitle, "")) {
                    editor.putString("title", nTitle);
                    editor.putString("subtitle", nSubTitle);
                    warning.setVisibility(View.GONE);c=true;
                }else{
                    warning.setVisibility(View.VISIBLE);
                    c=false;
                }
                editor.apply();
                if(c)
                    fu(3);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(),MainActivity2.class);
                i.putExtra("triggerName",nam);

                startActivity(i);
            }
        });
    }

    public void fu(int id)
    {
        SharedPreferences spf=getSharedPreferences("counter", Context.MODE_PRIVATE);
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

