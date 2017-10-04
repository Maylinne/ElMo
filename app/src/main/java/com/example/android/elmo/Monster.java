package com.example.android.elmo;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anasztázia on 2017.09.11..
 */

public class Monster implements Parcelable {

    // The variables
    public String mName;
    public int mElement;
    public int mElementPicture;
    public int mMonsterPicture;
    public int mAttack;
    public int mDefense;
    public int mMaxDefense;
    public int mHitPoints;
    public int mMaxHitPoints;
    public int mHunger;
    public int mXP;
    public int mLevel;
    public int mRemainingPoints;
    public String mHungerText;
    public String mElementColor;

    // Constructor - Creating a new Monster
    public Monster () {
        this.setDefaultAttributes();
    }

    // Writes the values to the parcel. ORDER SZAMIT!
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mName);
        out.writeInt(mElement);
        out.writeInt(mElementPicture);
        out.writeInt(mMonsterPicture);
        out.writeInt(mAttack);
        out.writeInt(mDefense);
        out.writeInt(mMaxDefense);
        out.writeInt(mHitPoints);
        out.writeInt(mMaxHitPoints);
        out.writeInt(mHunger);
        out.writeInt(mXP);
        out.writeInt(mLevel);
        out.writeInt(mRemainingPoints);
        out.writeString(mHungerText);
        out.writeString(mElementColor);
    }

    // Reads the values from the parcel. ORDER SZAMIT!
    public Monster (Parcel in){
        mName = in.readString();
        mElement = in.readInt();
        mElementPicture = in.readInt();
        mMonsterPicture = in.readInt();
        mAttack = in.readInt();
        mDefense = in.readInt();
        mMaxDefense = in.readInt();
        mHitPoints = in.readInt();
        mMaxHitPoints = in.readInt();
        mHunger = in.readInt();
        mXP = in.readInt();
        mLevel = in.readInt();
        mRemainingPoints = in.readInt();
        mHungerText = in.readString();
        mElementColor = in.readString();
    }

    // Parcelhez kell. NEMPISZKA
    public int describeContents() {
        return 0;
    }

    // Parcel creator. NEMPISZKA
    public static final Parcelable.Creator<Monster> CREATOR
            = new Parcelable.Creator<Monster>() {
        public Monster createFromParcel(Parcel in) {
            return new Monster(in);
        }

        public Monster[] newArray(int size) {
            return new Monster[size];
        }
    };


    // region Getter methods
    public  String getName () {
        return mName;
    }

    public int getElement () {
        return mElement;
    }

    public int getElementPicture () {
        return  mElementPicture;
    }

    public int getMonsterPicture () {
        return mMonsterPicture;
    }

    public int getAttack () {
        return mAttack;
    }

    public int getDefense () {
        return mDefense;
    }

    public int getMaxDefense() {
        return mMaxDefense;
    }

    public int getHitPoints () {
        return mHitPoints;
    }

    public int getMaxHitPoints() {
        return mMaxHitPoints;
    }

    public int getHunger () {
        return mHunger;
    }

    public int getXP() {
        return mXP;
    }

    public int getLevel() {
        return mLevel;
    }

    public int getRemainingPoints() {
        return mRemainingPoints;
    }

    public int getElementColor () {
        return Color.parseColor(mElementColor);
    }

    public String getHungerText () {
        return mHungerText;
    }

    // endregion

    // region Setter methods

    public void setName (String name) {
       this.mName = name;
    }

    public void setAttack(int mAttack) {
        this.mAttack = mAttack;
    }

    public void setDefense(int mDefense) {
        this.mDefense = mDefense;
    }

    public void setMaxDefense(int mMaxDefense) {
        this.mMaxDefense = mMaxDefense;
    }

    public void setHitPoints(int mHitPoints) {
        this.mHitPoints = mHitPoints;
    }

    public void setMaxHitPoints(int mMaxHitPoints) {
        this.mMaxHitPoints = mMaxHitPoints;
    }

    public void setXP(int mXP) {
        this.mXP = mXP;
        LevelUp();
    }

    public void setLevel(int mLevel) {
        this.mLevel = mLevel;
    }

    public void setRemainingPoints(int mRemainingPoints) {
        this.mRemainingPoints = mRemainingPoints;
    }

    //endregion


    // Methods
    public void setDefaultAttributes () {

        Random r = new Random();
        this.mAttack = r.nextInt(12 - 8) + 8;
        this.mDefense = r.nextInt(12 - 8) + 8;
        this.mHitPoints = r.nextInt(12 - 8) + 8;
        this.mHunger = 40;
        this.mName = MonsterNaming();
        this.mLevel = 1;
        this.mXP = 0;
        this.mRemainingPoints = 5;
    }

    public boolean canEat (int foodElement) {

       /*
       if (mElement != 0) { return foodElement % mElement == 0;
        } else { return false; }

        return mElement != 0 ? foodElement % mElement == 0 : false;
        */
        return mElement != 0 && foodElement % mElement == 0;
    }

    //
    // Language
    //

    // Accepted food
    public ArrayList<String> GetAcceptedSpeach () {
        return new ArrayList<String>();
    }

    //Denied food
    public ArrayList<String> GetDeniedSpeach () {
        return new ArrayList<String>();
    }

    // Hunger text
    public ArrayList<String> GetHungerSpeach () {
        ArrayList<String> hungerSpeach = new ArrayList<String>();
        hungerSpeach.add("I'm really hungry! GRRR! GIMME FOOD! >:(");
        hungerSpeach.add("I'm hungry. Feed me please!");
        hungerSpeach.add("I'm full. Don't give me more food!");

        return hungerSpeach;
    }

    // region Naming the Monster
    //

    // Create the name ArrayList. It is further filled with names by the element monsters
    public ArrayList<String> GetMonsterName () {
        return new ArrayList<String>();
    }

    // Select a random name from the ArrayList
    public String MonsterNaming () {
        return MonsterHelper.GetRandomString(GetMonsterName());
    }

    // endregion


    // Hunger text selection

    public String HungerTextSelect () {

        if (getHunger() <= Constants.HUNGRY) {
            mHungerText = (GetHungerSpeach().get(0));
        }
        if (Constants.HUNGRY < getHunger() && getHunger() <= Constants.OK) {
            mHungerText = (GetHungerSpeach().get(1));
        }
        if (Constants.OK < getHunger() && getHunger() < Constants.FULL) {
            mHungerText = (GetHungerSpeach().get(2));
        }

        return mHungerText;
    }

    // Get hit
    public void GetHit (int attack) {
        if (attack < this.mDefense) {
            this.mDefense -= attack;
        } else {
            this.mHitPoints -= attack - this.mDefense;
            this.mDefense = 0;
        }
    }

    public void Heal () {
        Random r = new Random();
        int healValue = r.nextInt(5 - 0) + 1;
        this.mHitPoints = (healValue+this.mHitPoints) > this.mMaxHitPoints ?
            this.mMaxHitPoints : healValue+this.mHitPoints;
    }


    public int LevelMaxXp (){
        int lmx = (int) Math.pow(2, this.mLevel);
        return lmx;
    }

    public void LevelUp () {
        if (this.mXP >= LevelMaxXp()) {
/*            this.setXP(this.getXP() - LevelMaxXp());
            this.setLevel(this.getLevel() + 1);*/
            this.mXP -= LevelMaxXp();
            this.mLevel++;
        }
    }
}

