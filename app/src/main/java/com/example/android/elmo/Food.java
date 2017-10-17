package com.example.android.elmo;

/**
 * Created by Anasztázia on 2017.10.16..
 */

public class Food {


    // Attributes
    private int mElement;
    private int mAmount;

    // Constructor
    public Food () {}

    public Food (int element, int amount) {
       mElement = element;
        mAmount = amount;
    }


    // region Getter methods

    public int getElement() {
        return mElement;
    }

    public int getAmount() {
        return mAmount;
    }

    // endregion

    // region Setter methods

    public void setElement(int mElement) {
        this.mElement = mElement;
    }

    public void setAmount(int mAmount) {
        this.mAmount = mAmount;
    }

    // endregion



}
