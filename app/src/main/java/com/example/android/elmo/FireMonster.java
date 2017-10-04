package com.example.android.elmo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anasztázia on 2017.09.11..
 */

public class FireMonster extends Monster {

    public FireMonster () {}
    public FireMonster (Parcel in) {
        super(in);
    }

    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<FireMonster> CREATOR
            = new Parcelable.Creator<FireMonster>() {
        public FireMonster createFromParcel(Parcel in) {
            return new FireMonster(in);
        }

        public FireMonster[] newArray(int size) {
            return new FireMonster[size];
        }
    };

    @Override
    public void setDefaultAttributes() {
        super.setDefaultAttributes();
        Random r = new Random();
        this.mAttack += r.nextInt(4 - 2) + 2;
        this.mElementPicture = R.drawable.e_fire;
        this.mElement = Constants.ELEMENT_FIRE;
        this.mMonsterPicture = R.drawable.fire_monster;
        this.mElementColor = "#b22929";


    }
    @Override
    public ArrayList<String> GetAcceptedSpeach () {
        ArrayList<String> acceptedSpeach = super.GetAcceptedSpeach();
        acceptedSpeach.add("Fight fire with fire!");
        acceptedSpeach.add("Thanks!");
        acceptedSpeach.add("I can burn it! :D");
        acceptedSpeach.add("Burn! Burn!");

        return acceptedSpeach;
    }

    @Override
    public ArrayList<String> GetDeniedSpeach () {
        ArrayList<String> deniedSpeach = super.GetDeniedSpeach();
        deniedSpeach.add("Pfooo!");
        deniedSpeach.add("No thanks!");
        deniedSpeach.add("I can't burn it!");
        deniedSpeach.add("Hash! Hash!");

        return deniedSpeach;
    }

    @Override
    public ArrayList<String> GetMonsterName () {
        ArrayList<String> monsterName = new ArrayList<String>();
        monsterName.add("Incedis");
        monsterName.add("Flashfire");
        monsterName.add("Fye");
        monsterName.add("Lavar");

        return monsterName;
    }

}
