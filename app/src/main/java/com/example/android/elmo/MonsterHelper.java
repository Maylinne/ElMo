package com.example.android.elmo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anasztázia on 2017.09.18..
 */

public class MonsterHelper {

    public MonsterHelper () {}

    public static String GetRandomString (ArrayList<String> stringList) {

        Random r = new Random();
        int pos = 0;

        pos = r.nextInt(stringList.size());

        return stringList.get(pos);

    }
}
