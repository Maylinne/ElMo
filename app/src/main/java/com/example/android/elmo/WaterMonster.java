package com.example.android.elmo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anasztázia on 2017.09.11..
 */


public class WaterMonster extends Monster {

    public WaterMonster () {}

    @Override
    public void setDefaultAttributes() {
        super.setDefaultAttributes();
        Random r = new Random();
        this.mDefense += r.nextInt(4 - 2) + 2;
        this.mElementPicture = R.drawable.e_water;
        this.mElement = Constants.ELEMENT_WATER;
        this.mMonsterPicture = R.drawable.water_monster;
        this.mElementColor = "#232a8e";
    }

    @Override
    public ArrayList<String> GetAcceptedSpeach () {
        ArrayList<String> acceptedSpeach = super.GetAcceptedSpeach();
        acceptedSpeach.add("Flood in the desert!");
        acceptedSpeach.add("Thanks!");
        acceptedSpeach.add("It flows through my throat.");
        acceptedSpeach.add("Csobb! Csobb!");

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
        monsterName.add("Aquira");
        monsterName.add("Typhis");
        monsterName.add("River");
        monsterName.add("Precipe");

        return monsterName;
    }

    @Override
    public int GetOpponentElement () {
        return Constants.ELEMENT_AIR;
    }
}
