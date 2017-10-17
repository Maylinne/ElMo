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

    public static Monster GetRandomEnemy (int level) {
        if (monsterType.size() == 0) {
            Init();
        }

        Random r = new Random();
        int pos = 0;

        pos = r.nextInt(monsterType.size());

        try {
            Class<?> c = Class.forName(monsterType.get(pos));
            Monster monster = (Monster) c.newInstance();
            monster.setLevel(level);

            if (level > 1) level--;

            // ToDo Rekurziv metodussal megoldani
            int remP = monster.getRemainingPoints() * level;

            int plusA = r.nextInt(remP) ;
            monster.setAttack(plusA + monster.getAttack());
            remP -= plusA;

            int plusD = r.nextInt(remP);
            monster.setDefense(plusD + monster.getDefense());
            remP -= plusD;

            monster.setHitPoints(remP + monster.getHitPoints());

            return monster;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    public static void Drop (int enemyLevel) {

        ArrayList<Food> dropList = new ArrayList<Food>();
        ArrayList<Food> foodList = StableSingleton.getInstance().getFoodArray();
        Random r = new Random();

        for (int i = 0; i < enemyLevel; i++) {
            dropList.add(new Food(foodList.get(r.nextInt(foodList.size())).getElement(), r.nextInt(10-5)+5));
        }

        // Foodlistbe belerakni a droplistet => addToFoodArray túlterhelése ArrayList<Food> parammal
        StableSingleton.getInstance().addToFoodArray(dropList);
    }

}
