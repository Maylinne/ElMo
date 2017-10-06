package com.example.android.elmo;

import java.util.ArrayList;

/**
 * Created by Anasztázia on 2017.10.06..
 */

public class StableSingleton  {

    private static StableSingleton mInstance;
    private ArrayList<Monster> list = null;

    public static StableSingleton getInstance() {
        if(mInstance == null)
            mInstance = new StableSingleton();

        return mInstance;
    }

    private StableSingleton() {
        list = new ArrayList<Monster>();
    }
    // retrieve array from anywhere
    public ArrayList<Monster> getArray() {
        return this.list;
    }
    //Add element to array
    public void addToArray(Monster monster) {
        list.add(monster);
    }
}
