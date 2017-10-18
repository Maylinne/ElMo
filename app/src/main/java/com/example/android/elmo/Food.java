package com.example.android.elmo;

/**
 * Created by Anasztázia on 2017.10.16..
 */

public class Food {


    // Attributes
    private String mName;
    private int mElement;
    private int mAmount;

    // Constructor
    public Food () {}


    public Food (String name, int element, int amount) {
        mName = name;
        mElement = element;
        mAmount = amount;
    }


    // region Getter methods

    public String getName() {
        return mName;
    }

    public int getElement() {
        return mElement;
    }

    public int getAmount() {
        return mAmount;
    }

    // endregion

    // region Setter methods

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setElement(int mElement) {
        this.mElement = mElement;
    }

    public void setAmount(int mAmount) {
        this.mAmount = mAmount;
    }

    // endregion



}
