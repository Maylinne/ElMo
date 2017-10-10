package com.example.android.elmo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anasztázia on 2017.09.11..
 */

public class EarthMonster extends Monster {

    public EarthMonster () {}

    @Override
    public void setDefaultAttributes() {
        super.setDefaultAttributes();
        Random r = new Random();
        this.mHitPoints = this.mHitPoints + r.nextInt(4 - 2) + 2;
        this.mElementPicture = R.drawable.e_earth;
        this.mElement = Constants.ELEMENT_EARTH;
        this.mMonsterPicture = R.drawable.earth_monster;
        this.mElementColor = "#33702b";
    }

    @Override
    public ArrayList<String> GetAcceptedSpeach () {
        ArrayList<String> acceptedSpeach = super.GetAcceptedSpeach();
        acceptedSpeach.add("Dig deep and found the food!");
        acceptedSpeach.add("Thanks!");
        acceptedSpeach.add("It's consumabury! :)");
        acceptedSpeach.add("Crunch! Crunch!");

        return acceptedSpeach;
    }

    @Override
    public ArrayList<String> GetDeniedSpeach () {
        ArrayList<String> deniedSpeach = super.GetDeniedSpeach();
        deniedSpeach.add("Pfooo!");
        deniedSpeach.add("No thanks!");
        deniedSpeach.add("I don't like it!");
        deniedSpeach.add("Bury it!");

        return deniedSpeach;
    }

    @Override
    public ArrayList<String> GetMonsterName () {
        ArrayList<String> monsterName = new ArrayList<String>();
        monsterName.add("Slab");
        monsterName.add("Valanche");
        monsterName.add("Claye");
        monsterName.add("Duster");

        return monsterName;
    }

    @Override
    public int GetOpponentElement () {
        return Constants.ELEMENT_FIRE;
    }
}
