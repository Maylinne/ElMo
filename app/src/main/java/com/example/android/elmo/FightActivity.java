package com.example.android.elmo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.android.elmo.Constants.ENEMYDEFENSE;
import static com.example.android.elmo.Constants.ENEMYHITPOINTS;
import static com.example.android.elmo.Constants.MYDEFENSE;
import static com.example.android.elmo.Constants.MYHITPOINTS;

public class FightActivity extends AppCompatActivity {

    Monster myMonster = null;
    Monster enemyMonster = null;
    int originalValues[] = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        // ToDo Get the pos of the actual monc from an extra. For now 0, since we get only one monster in stable.
        myMonster = StableSingleton.getInstance().getArray().get(0);
        enemyMonster = FightHelper.GetRandomEnemy();
        final Button endButton = (Button) findViewById(R.id.fightEnd_B);
        UiHelper.SetButtonVisibility(endButton, View.GONE);

        SetOriginalValues();

        SetAttributes();

        // Next turn button
        Button nextTurn = (Button) findViewById(R.id.nextTurn_B);
        // It will be called, when net turn button is clicked
        nextTurn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the FEEDING BUTTON is clicked on.
            @Override
            public void onClick(View view) {
                myMonster.GetHit(enemyMonster.getAttack(), enemyMonster.getElement());
                enemyMonster.GetHit(myMonster.getAttack(), myMonster.getElement());

                // Refresh attributes
                SetAttributes();

                if (myMonster.getHitPoints() < 1 || enemyMonster.getHitPoints() < 1) {
                    UiHelper.SetButtonVisibility((Button) view, View.GONE);
                    if (myMonster.getHitPoints() < 1 && enemyMonster.getHitPoints() < 1) {
                        // DRAW
                        endButton.setText("Draw. Nobody won and nobody lost.");
                        endButton.setTextColor(Color.WHITE);
                        UiHelper.SetButtonVisibility(endButton, View.VISIBLE);

                    } else if (myMonster.getHitPoints() < 1 ) {
                        // PLAYER LOSE
                        endButton.setText("You lost. Better luck next time!");
                        endButton.setTextColor(Color.RED);
                        UiHelper.SetButtonVisibility(endButton, View.VISIBLE);

                    } else {
                        // PLAYER WINS
                        endButton.setText(myMonster.getName() + " won! GG");
                        endButton.setTextColor(Color.GREEN);
                        myMonster.setXP(myMonster.getXP() + enemyMonster.getLevel());
                        UiHelper.SetButtonVisibility(endButton, View.VISIBLE);
                    }
                }
            }
        });

        // The button is pressed in the end of the battle
        endButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the FEEDING BUTTON is clicked on.
            @Override
            public void onClick(View view) {

                myMonster.setDefense(myMonster.getMaxDefense());

                finish();
            }
        });

    }

    private void SetOriginalValues() {
        originalValues = new int[4];
        originalValues [MYDEFENSE] = myMonster.getDefense();
        originalValues [MYHITPOINTS] = myMonster.getHitPoints();
        originalValues [ENEMYDEFENSE] = enemyMonster.getDefense();
        originalValues [ENEMYHITPOINTS] = enemyMonster.getHitPoints();
    }


    // region Fill the monster's (both) data

    private void SetAttributes () {
        // Name
        TextView playerName = (TextView) findViewById(R.id.name_TV);
            playerName.setText(myMonster.getName());

        // Picture
        ImageView playerImage = (ImageView) findViewById(R.id.playerImage_IV);
            playerImage.setImageResource(myMonster.getMonsterPicture());

        // Attack
        TextView playerAttack = (TextView) findViewById(R.id.attack_TV);
            playerAttack.setText(String.valueOf(myMonster.getAttack()));

        // Defense
        TextView playerDefense = (TextView) findViewById(R.id.defense_TV);
        if (myMonster.getDefense() != originalValues[MYDEFENSE]) {
            playerDefense.setTextColor(Color.RED);
        }
            playerDefense.setText(String.valueOf(myMonster.getDefense()));

        // Hit points
        TextView playerHP = (TextView) findViewById(R.id.hp_TV);
        if (myMonster.getHitPoints() != originalValues[MYHITPOINTS]) {
            playerHP.setTextColor(Color.RED);
        }
            playerHP.setText(String.valueOf(myMonster.getHitPoints()));

        // Players MyTurn indicator is set to invisible.
        // It will be visible only in the fight, during players turn.
        TextView playerTurn = (TextView) findViewById(R.id.playerTurn_TV);
            playerTurn.setVisibility(View.INVISIBLE);


        // Fill enemy monster data
        // Name
        TextView enemyName = (TextView) findViewById(R.id.enemyName_TV);
        enemyName.setText(enemyMonster.getName());

        // Picture
        ImageView enemyImage = (ImageView) findViewById(R.id.enemyPicture_IV);
        enemyImage.setImageResource(enemyMonster.getMonsterPicture());

        // Attack
        TextView enemyAttack = (TextView) findViewById(R.id.enemyAttack_TV);
        enemyAttack.setText(String.valueOf(enemyMonster.getAttack()));

        // Defense
        TextView enemyDefense = (TextView) findViewById(R.id.enemyDefense_TV);
        if (enemyMonster.getDefense() != originalValues[ENEMYDEFENSE]) {
            enemyDefense.setTextColor(Color.RED);
        }
        enemyDefense.setText(String.valueOf(enemyMonster.getDefense()));

        // Hit points
        TextView enemyHP = (TextView) findViewById(R.id.enemyHP_TV);
        if (enemyMonster.getHitPoints() != originalValues[ENEMYHITPOINTS]) {
            enemyHP.setTextColor(Color.RED);
        }
        enemyHP.setText(String.valueOf(enemyMonster.getHitPoints()));

        // Players MyTurn indicator is set to invisible.
        // It will be visible only in the fight, during players turn.
        TextView enemyTurn = (TextView) findViewById(R.id.enemyTurn_TV);
        enemyTurn.setVisibility(View.INVISIBLE);

    }
    // endregion


}
