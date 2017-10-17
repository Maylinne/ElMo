package com.example.android.elmo;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    AttributePresetsFragment apfFragment = null;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monc);

        Intent intent = getIntent();

        monc = StableSingleton.getInstance().getMonsterArray().get(0);
        toast = Toast.makeText(MoncActivity.this, "", Toast.LENGTH_SHORT);

        try {


            MonsterFragment moncFragment = new MonsterFragment();
            FeedingFragment feedingFragment = new FeedingFragment();
            FightButtonFragment fightButtonFragment = new FightButtonFragment();
            apfFragment = new AttributePresetsFragment(true);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_monster, moncFragment)
                    .replace(R.id.fragment_feeding, feedingFragment)
                    .replace(R.id.fragment_fight_button, fightButtonFragment)
                    .replace(R.id.fragment_apf, apfFragment)
                    .commit();

        } catch (Exception e) {
            // This will catch any exception, because they are all descended from Exception
            System.out.println("Error " + e.getMessage());

        }



        getSupportFragmentManager()
                .registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                    @Override
                    public void onFragmentStopped(FragmentManager fm, Fragment f) {
                        super.onFragmentDestroyed(fm, f);
                        if ( f instanceof AttributePresetsFragment) {
                            RefreshView();
                            HideApf();
                        }
                    }
                }, true);



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
            if (frag!=null && frag.isVisible() && frag instanceof IRefreshView) {
                ((IRefreshView) frag).SetAttributes();
            }
        }
    }
    
    public void ShowApf () {
        findViewById(R.id.fragment_feeding).setVisibility(View.GONE);
        findViewById(R.id.fragment_fight_button).setVisibility(View.GONE);
        if (this.apfFragment == null) this.apfFragment = new AttributePresetsFragment(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_apf, this.apfFragment).commit();
        findViewById(R.id.fragment_apf).setVisibility(View.VISIBLE);

    }

    public void HideApf () {
        findViewById(R.id.fragment_feeding).setVisibility(View.VISIBLE);
        findViewById(R.id.fragment_fight_button).setVisibility(View.VISIBLE);
        findViewById(R.id.fragment_apf).setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
    }


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
