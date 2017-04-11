package com.example.mydell.taskit;



/**
 * Created by Shashank on 11/19/2016.
 */

class model {
    public String name;
    public String packageName;
    private String id;
    int value;


    model(String name, String packageName,int value){
        this.name = name;
        this.packageName = packageName;
        this.value = value;
        // Log.i("NAME = ",id + " " + name);
    }

    public void setValue(int check){
        value = check;
    }
    public String getName(){
        return this.name;
    }
    public String getpackageName(){
        return this.packageName;
    }

    public String getId() {
        return this.id;
    }

    public int getValue() {
        return value;
    }
}
