package com.example.android.elmo;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;


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
        monc = intent.getParcelableExtra("Monster");
        toast = Toast.makeText(MoncActivity.this, "", Toast.LENGTH_SHORT);


      //  monc = getExtraData(Class<Monster> Monster);

        // Toggle button instantiate
        ((RadioGroup) findViewById(R.id.toggleGroup)).setOnCheckedChangeListener(ToggleListener);

        // region Pictures
        //

        // Find the View that shows the PICTURE OF THE MONSTER
        ImageView monc_picture = (ImageView) findViewById(R.id.monc_picture);
        //Set the view with the corresponding picture
        monc_picture.setImageResource(monc.mMonsterPicture);

        ImageButton fight_btn = (ImageButton) findViewById(R.id.fight);
        fight_btn.setImageResource(R.drawable.fight_btn);

        // endregion

        SetAttributes();

        //  region FEEDING
        //

        //Initiate the progress bar, and the hunger text
        hungerMeter = (ProgressBar) findViewById(R.id.hungerMeter_PB);
        hungerText = (TextView) findViewById(R.id.hungerText_TV);

        // Setting the progress bar, and the hunger text
        hungerMeter.setMax(100);
        hungerMeter.getProgressDrawable().setColorFilter(monc.getElementColor(), PorterDuff.Mode.SRC_IN);
        hungerMeter.setProgress(monc.mHunger);
        hungerText.setText(monc.HungerTextSelect());

        // endregion

        // region Hungry button

        // Find the View that shows the HUNGRY BUTTON
        Button hungry_button = (Button) findViewById(R.id.hungry_button);

        hungry_button.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the FEEDING BUTTON is clicked on.
            @Override
            public void onClick(View view) {
                if (foodElement == 0) {
                    toast.cancel();
                    toast = Toast.makeText(MoncActivity.this, "Please, select a food first!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                        Random r = new Random();
                        int pos = 0;

                        if (monc.canEat(foodElement)) {
                            int prevHunger = monc.mHunger;
                            ArrayList<String> speach = monc.GetAcceptedSpeach();
                            pos = r.nextInt(speach.size());
                            toast.cancel();
                            toast = Toast.makeText(MoncActivity.this, speach.get(pos), Toast.LENGTH_SHORT);
                            toast.show();
                            monc.mHunger += r.nextInt(30 - 15) + 15;
                            if (monc.mHunger >= 100) {
                                monc.Heal();
                                monc.mHunger = 0;
                                SetAttributes();
                            }
                            hungerMeter.setProgress(monc.mHunger);
                            hungerText.setText(monc.HungerTextSelect());
                            UiHelper.AnimateProgressBar(hungerMeter, prevHunger, monc.mHunger, 1000);
                        } else {
                            ArrayList<String> speach = monc.GetDeniedSpeach();
                            pos = r.nextInt(speach.size());
                            toast.cancel();
                            toast = Toast.makeText(MoncActivity.this, speach.get(pos), Toast.LENGTH_SHORT);
                            toast.show();
                        }

                }
            }
        });

        // endregion

        // region Fight! button

        // What does the fight button
        fight_btn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the fight button View is clicked on.
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(view.getContext(), FightActivity.class);
                mainIntent.putExtra("PlayerMonster", monc);
                startActivityForResult(mainIntent, 1);

            }
        });

        // endregion
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                monc.setHitPoints(data.getIntExtra("HitPoints", monc.getHitPoints()));
                monc.setXP(data.getIntExtra("XP", monc.getXP()));
                SetAttributes();
            }
        }
    }


    //
    // Helper methods
    //

    // region SetAttributes()
    private void SetAttributes() {

        //


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
        xp.setText(String.valueOf(monc.getXP()));

        // Find the level, and set it
        TextView lvl = (TextView) findViewById(R.id.level_TV);
        lvl.setText(String.valueOf(monc.getLevel()));


    }
    // endregion

    // region ToggleButton toggling

    static final RadioGroup.OnCheckedChangeListener ToggleListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
            for (int j = 0; j < radioGroup.getChildCount(); j++) {
                final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                view.setChecked(view.getId() == i);
            }
        }
    };

    public void onToggle(View view) {
        ((RadioGroup)view.getParent()).check(view.getId());
        foodElement = Integer.parseInt(view.getTag().toString());
    }

    // endregion

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
