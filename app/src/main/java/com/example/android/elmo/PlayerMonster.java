package com.example.android.elmo;

/**
 * Created by Anasztázia on 2017.09.11..
 */

public class PlayerMonster extends Monster {

    // The variables
    private String mName;

    // Creating the PlayerMonster

    public PlayerMonster (String name, int element, int att, int def, int hp) {
        mName = name;
        mElement = element;
        mAttack = att;
        mDefense = def;
        mHitPoints = hp;
    }

    // Getter methods
    public String getName () {
        return mName;
    }
}
