package com.example.android.elmo;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anasztázia on 2017.09.11..
 */
public class AirMonster extends Monster{

    public AirMonster () {}

    @Override
    public void setDefaultAttributes () {
        super.setDefaultAttributes();
        Random r = new Random();
        this.mAttack += r.nextInt(2 - 1) + 1;
        this.mDefense += r.nextInt(2 - 1) + 1;
        this.mHitPoints += r.nextInt(2 - 1) + 1;
        this.mElementPicture = R.drawable.e_air;
        this.mElement = Constants.ELEMENT_AIR;
        this.mMonsterPicture = R.drawable.air_monster;
        this.mElementColor = "#38caf7";
    }

    @Override
    public ArrayList<String> GetAcceptedSpeach () {
        ArrayList<String> acceptedSpeach = super.GetAcceptedSpeach();
        acceptedSpeach.add("Yummy!");
        acceptedSpeach.add("Thanks");
        acceptedSpeach.add("I like it!");
        acceptedSpeach.add("Whoosh! Whoosh!");

        return acceptedSpeach;
    }

    @Override
    public ArrayList<String> GetDeniedSpeach () {
        ArrayList<String> deniedSpeach = super.GetDeniedSpeach();
        deniedSpeach.add("Pfooo!");
        deniedSpeach.add("No thanks!");
        deniedSpeach.add("I don't like it!");
        deniedSpeach.add("Hash! Hash!");

        return deniedSpeach;
    }

    @Override
    public ArrayList<String> GetMonsterName () {
        ArrayList<String> monsterName = new ArrayList<String>();
        monsterName.add("Cerulea");
        monsterName.add("Ozone");
        monsterName.add("Tumulus");
        monsterName.add("Aeranas");

        return monsterName;
    }

    @Override
    public int GetOpponentElement () {
        return Constants.ELEMENT_EARTH;
    }
}
