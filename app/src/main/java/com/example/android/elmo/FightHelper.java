package com.example.android.elmo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anasztázia on 2017.10.02..
 */

public class FightHelper {

    // ArrayList for selection of a random enemy monster
    public static ArrayList<String> monsterType = new ArrayList<String>();


    public static void Init() {
        monsterType.add("com.example.android.elmo.AirMonster");
        monsterType.add("com.example.android.elmo.FireMonster");
        monsterType.add("com.example.android.elmo.EarthMonster");
        monsterType.add("com.example.android.elmo.WaterMonster");
    }

    public static Monster GetRandomEnemy () {
        if (monsterType.size() == 0) {
            Init();
        }

        Random r = new Random();
        int pos = 0;

        pos = r.nextInt(monsterType.size());

        try {
            Class<?> c = Class.forName(monsterType.get(pos));
            return (Monster) c.newInstance();
        }
        catch (Exception ex)
        {
            return null;
        }

    }

    /*
    public void BattleHit () {
        int aa = myMonster.getAttack();
        int ad = myMonster.getDefense();
        int ah = myMonster.getHitPoints();

        int ba = enemyMonster.getAttack();
        int bd = enemyMonster.getDefense();
        int bh = enemyMonster.getHitPoints();

        if (bd > aa) {
            bd = bd - aa;
            return;
        }

        if (bd <= aa) {
            bh = bh - aa + bd;
            bd = 0;
            return;
        }

        if (bh > aa) {
            bh = bh - aa;
            return;
        }

        if (bh <= aa) {
            bh = 0;
        }
    }

    */


}
