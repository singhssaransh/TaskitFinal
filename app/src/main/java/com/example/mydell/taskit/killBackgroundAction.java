package com.example.mydell.taskit;



        import android.app.ActivityManager;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.pm.ApplicationInfo;
        import android.content.pm.PackageManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.PopupMenu;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.ListView;
        import android.widget.RadioGroup;
        import android.widget.Toast;

        import java.sql.Array;
        import java.util.Arrays;
        import java.util.Comparator;
        import java.util.List;

public class killBackgroundAction extends AppCompatActivity {

    Context context;
    model[] appName;
    int counter = 0;
    ListView listView;
    static public String a = "";
    String[] newArray;
    customAdapter3 adapter;
    CheckBox checkAll;
    int c = 0;
    int value = 0;
    int count=0;
    Button b1,b2;
    String nam;
int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kill_background_action);

nam = getIntent().getStringExtra("triggerName");
        newArray = new String[1000];
        b2 = (Button) findViewById(R.id.buttonOk);
        b1 = (Button) findViewById(R.id.buttonCancel);
        context = this;
        listView = (ListView) findViewById(R.id.listApps);
        checkAll = (CheckBox) findViewById(R.id.checkBoxAll);
        final PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {

            if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null &&
                    !pm.getLaunchIntentForPackage(packageInfo.packageName).equals("")) {
                c++;
            }

        }
        appName = new model[c];
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .4));


        for (ApplicationInfo packageInfo : packages) {
            if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null &&
                    !pm.getLaunchIntentForPackage(packageInfo.packageName).equals("")) {
                newArray[counter] = packageInfo.packageName;
                appName[counter] = new model(pm.getApplicationLabel(packageInfo).toString(), newArray[counter], value);

                //popMenu.getMenu().add(Menu.NONE, ++counter, counter, pm.getApplicationLabel(packageInfo));
                counter++;
            }

        }
        Arrays.sort(appName, new Comparator<model>() {
            @Override
            public int compare(model lhs, model rhs) {
                return lhs.name.compareToIgnoreCase(rhs.name);
                /*if(Integer.valueOf(lhs.name) > Integer.valueOf(rhs.name)) return 1;
                else if (Integer.valueOf(lhs.name) < Integer.valueOf(rhs.name)) return -1;
                else return 0;*/
            }
        });
        adapter = new customAdapter3(context, appName);
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    for(int j=0;j<c;j++){
                        appName[j].setValue(1);
                    }
                    SharedPreferences sharedPreferences = getSharedPreferences("TaskIt", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("SelectAll",true);
                    editor.apply();
                    adapter.notifyDataSetChanged();
                }else{
                    for(int j=0;j<c;j++){
                        appName[j].setValue(0);
                    }
                    SharedPreferences sharedPreferences = getSharedPreferences("TaskIt", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("SelectAll",false);
                    editor.apply();
                    adapter.notifyDataSetChanged();
                }
            }
        });
                   /* popMenu.setGravity(Gravity.CENTER);
                    popMenu.show();
                    popMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            a = newArray[item.getItemId() - 1];
                            SharedPreferences spf = context.getSharedPreferences("ab", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = spf.edit();
                            editor.putString("valuea", a);
                            editor.apply();

                            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(a);
                            if (launchIntent != null) {
                                editor.putString("OpenActivity", newArray[item.getItemId() - 1]);
                                editor.apply();
                            }

                /*String a;
                a=newArray[item.getItemId()-1];
                Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(a);
                if (launchIntent != null) {
                    context.startActivity(launchIntent);
                }*/


        assert listView != null;
        listView.setAdapter(adapter);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(context,MainActivity2.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("triggerName",nam);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.sharepref();
                /*List<ApplicationInfo> packages;
                PackageManager pm;
                pm = getPackageManager();
                //get a list of installed apps.
                packages = pm.getInstalledApplications(0);
                String myPackage = getApplicationContext().getPackageName();



                ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfo = am.getRunningAppProcesses();

                SharedPreferences sharedPref = getSharedPreferences("TaskIt", Context.MODE_PRIVATE);
                int apps = sharedPref.getInt("appsSelected",0);
                String[] closeApp = new String[apps];// = sharedPref.getString("")
                for (int j = 0; j < apps; j++) {
                    //Log.i("APP NAME",a[j]);
                    closeApp[j] = sharedPref.getString("app" + j, "");
                }
                for (ApplicationInfo packageInfo : packages) {
                    if((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM)==1)continue;
                    if(packageInfo.packageName.equals(myPackage)) continue;
                    for (int j = 0; j < apps; j++) {
                        if(packageInfo.packageName.equals(closeApp[j])){
                            Toast.makeText(getApplicationContext(),closeApp[j],Toast.LENGTH_LONG).show();
                            am.killBackgroundProcesses(closeApp[j]);
                        }
                    }
                }*/
                fu(5);
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

    public int getCounter() {
        return counter;
    }
}
