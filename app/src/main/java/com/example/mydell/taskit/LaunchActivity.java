package com.example.mydell.taskit;


        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.pm.ApplicationInfo;
        import android.content.pm.PackageManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.DisplayMetrics;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.Spinner;

        import java.util.Arrays;
        import java.util.Comparator;
        import java.util.List;

public class LaunchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Context context;
    Button b6,b7;
    int counter = 0;
    Spinner spinner;
    static public String a = "";
    String[] newArray;
    String[] ArrayPackage;
    String app="";
    String packageName="";
    ArrayAdapter<CharSequence> adapter;
    int c=0;
    int value = 0;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        ArrayPackage= new String[1000];

        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);

        spinner = (Spinner) findViewById(R.id.spinner);
        context = this;
        final PackageManager pm = context.getPackageManager();
        final List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {

            if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null &&
                    !pm.getLaunchIntentForPackage(packageInfo.packageName).equals("")) {
                c++;
            }

        }

        newArray = new String[c];
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .4));


        for (ApplicationInfo packageInfo : packages) {
            if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null &&
                    !pm.getLaunchIntentForPackage(packageInfo.packageName).equals("")) {
                newArray[counter] = pm.getApplicationLabel(packageInfo).toString();
                ArrayPackage[counter] = packageInfo.packageName;


                //popMenu.getMenu().add(Menu.NONE, ++counter, counter, pm.getApplicationLabel(packageInfo));
                counter++;
            }

        }
        /*Arrays.sort(appName, new Comparator<model>() {
            @Override
            public int compare(model lhs, model rhs) {
                return lhs.name.compareToIgnoreCase(rhs.name);
                /*if(Integer.valueOf(lhs.name) > Integer.valueOf(rhs.name)) return 1;
                else if (Integer.valueOf(lhs.name) < Integer.valueOf(rhs.name)) return -1;
                else return 0;*/
            /*}
        });*/
        adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, newArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("TaskIt",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("AppLaunch",app);
                editor.putString("AppLaunchPackage",packageName);
                editor.apply();
                startActivity(new Intent(context , MainActivity.class));
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        app = (String) parent.getItemAtPosition(position);
        packageName = ArrayPackage[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

