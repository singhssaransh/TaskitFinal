package com.example.mydell.taskit;



        import android.app.Activity;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.support.annotation.NonNull;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.TextView;
        import android.widget.Toast;

/**
 * Created by Shashank on 11/19/2016.
 */

class customAdapter3 extends ArrayAdapter<model> {
    Context context;
    model[] modelItems;
    int i = 0;
    String[] a = new String[100];
    killBackgroundAction kba = new killBackgroundAction();

    customAdapter3(Context context, model[] resource) {
        super(context, R.layout.app_list, resource);
        this.context = context;
        this.modelItems = resource;
    }

    private static class ViewHolder {

        private TextView name;
        private CheckBox cb;

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        final ViewHolder mViewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.app_list, parent, false);
            mViewHolder = new ViewHolder();


            mViewHolder.name = (TextView) convertView.findViewById(R.id.textView1);
            mViewHolder.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);

            convertView.setTag(mViewHolder);


        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        //Log.i("positions",position+"");
        if (modelItems[position] != null) {
            mViewHolder.name.setText(modelItems[position].getName());
        } else {

        }

        mViewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isChecked() && modelItems[position] != null) {

                    modelItems[position].value = 1;
                    a[i] = modelItems[position].getpackageName();
                    /*Log.i(modelItems[position].getName(),modelItems[position].getNumber());
                    Log.i("SAVED", ContactNumber[i]);*/
                    i++;
                } else {
                    if (modelItems[position] != null) {
                        modelItems[position].value = 0;
                    }
                }
            }
        });
       /* mViewHolder.name.setTag(position);
        mViewHolder.cb.setTag(position);*/
        if (modelItems[position] != null) {
            if (modelItems[position].getValue() == 1)
                mViewHolder.cb.setChecked(true);
            else
                mViewHolder.cb.setChecked(false);
        }

        return convertView;

    }

    public void sharepref() {
        SharedPreferences sharedPref = context.getSharedPreferences("TaskIt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        for (int j = 0; j < i; j++) {
            Toast.makeText(context,a[j],Toast.LENGTH_LONG).show();

            editor.putString("app" + j, a[j]);
        }
        editor.putInt("appsSelected",i);
        editor.apply();
    }

}

