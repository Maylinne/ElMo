package com.example.android.elmo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class FightActivity extends AppCompatActivity {

    Monster myMonster = null;
    Monster enemyMonster = null;
    int originalValues[] = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        // ToDo Get the pos of the actual monc from an extra. For now 0, since we get only one monster in stable.
        myMonster = StableSingleton.getInstance().getMonsterArray().get(0);
        enemyMonster = FightHelper.GetRandomEnemy(myMonster.getLevel());
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
                        FoodAlert(FightHelper.Drop(enemyMonster.getLevel()));
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


    // An Alert Dialog to inform the user about the type, and amount of the food dropped by the monster
    private void FoodAlert(ArrayList<Food> foodList) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FightActivity.this);

        int size = foodList.size();
        StringBuilder message = new StringBuilder();
        message.append("The monster dropped ");
        for (int i = 0; i < size; i++)
        {
            message.append(foodList.get(i).getAmount());
            message.append(" ");
            message.append(foodList.get(i).getName());
            message.append("s, ");

        }

        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface arg0, int arg1) {
               Toast.makeText(FightActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
               myMonster.setDefense(myMonster.getMaxDefense());
               finish();
           }
         });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void SetOriginalValues() {
        originalValues = new int[4];
        originalValues [Constants.MYDEFENSE] = myMonster.getDefense();
        originalValues [Constants.MYHITPOINTS] = myMonster.getHitPoints();
        originalValues [Constants.ENEMYDEFENSE] = enemyMonster.getDefense();
        originalValues [Constants.ENEMYHITPOINTS] = enemyMonster.getHitPoints();
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
        if (myMonster.getDefense() != originalValues[Constants.MYDEFENSE]) {
            playerDefense.setTextColor(Color.RED);
        }
            playerDefense.setText(String.valueOf(myMonster.getDefense()));

        // Hit points
        TextView playerHP = (TextView) findViewById(R.id.hp_TV);
        if (myMonster.getHitPoints() != originalValues[Constants.MYHITPOINTS]) {
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
        if (enemyMonster.getDefense() != originalValues[Constants.ENEMYDEFENSE]) {
            enemyDefense.setTextColor(Color.RED);
        }
        enemyDefense.setText(String.valueOf(enemyMonster.getDefense()));

        // Hit points
        TextView enemyHP = (TextView) findViewById(R.id.enemyHP_TV);
        if (enemyMonster.getHitPoints() != originalValues[Constants.ENEMYHITPOINTS]) {
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
