package com.example.android.elmo;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MoncActivity extends AppCompatActivity {


    // Instantiate the monster
    Monster monc = null;

    int foodElement = 0;
    TextView hungerText;
    ProgressBar hungerMeter;
    Toast toast = null;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monc);

        Intent intent = getIntent();

        monc = StableSingleton.getInstance().getArray().get(0);
        toast = Toast.makeText(MoncActivity.this, "", Toast.LENGTH_SHORT);

        try {


            MonsterFragment fragment1 = new MonsterFragment();
            FeedingFragment fragment2 = new FeedingFragment();
            FightButtonFragment fragment3 = new FightButtonFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_monster, fragment1)
                    .replace(R.id.fragment_feeding, fragment2)
                    .replace(R.id.fragment_fight_button, fragment3)
                    .commit();

        } catch (Exception e) {
            // This will catch any exception, because they are all descended from Exception
            System.out.println("Error " + e.getMessage());

        }

    }



    @Override
    public void onResume(){
        super.onResume();
        // To refresh the attributes, when returned
        RefreshView();
    }


    //
    // Helper methods
    //

    public void RefreshView () {

        for (Fragment frag : this.getSupportFragmentManager().getFragments()
                ) {
            if (frag.isVisible() && frag instanceof IRefreshView) {
                ((IRefreshView) frag).SetAttributes();
            }
        }
    }
    
    
    
    /*
    // region SetAttributes()
    public void SetAttributes() {

        // Monster name edit text
        TextView monsterName = (TextView) findViewById(R.id.monsterName_TV);
        // Fill the EditText with a random elemental monster name
        monsterName.setText(monc.mName);

        // Find the View that shows the ATTACK
        TextView attack = (TextView) findViewById(R.id.attack_TV);
        //Set the view with the corresponding picture
        attack.setText(String.valueOf(monc.getAttack()));

        // Find the View that shows the DEFENSE
        TextView defense = (TextView) findViewById(R.id.defense_TV);
        //Set the view with the corresponding picture
        defense.setText(String.valueOf(monc.getDefense()));

        // Find the View that shows the HIT POINTS
        TextView hitPoints = (TextView) findViewById(R.id.hitPoints_TV);
        //Set the view with the corresponding picture
        hitPoints.setText(String.valueOf(monc.getHitPoints()));

        // Find the XP and fill it
        TextView xp = (TextView) findViewById(R.id.xp_TV);
        xp.setText(String.valueOf(monc.LevelMaxXp()) + " / " + String.valueOf(monc.getXP()));

        // Find the level, and set it
        TextView lvl = (TextView) findViewById(R.id.level_TV);
        lvl.setText(String.valueOf(monc.getLevel()));


    }
    // endregion
*/
    // region Countdown timer to the progressBar

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished/100);
            hungerMeter.setProgress(progress);
        }

        @Override
        public void onFinish() {
            hungerMeter.setProgress(0);
        }

    }
    // endregion


}
