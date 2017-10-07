package com.example.android.elmo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttributePresetsFragment extends Fragment {


    public AttributePresetsFragment() {
        // Required empty public constructor
    }

    MainActivity act = null;
    //Attribute changer variables
    int a = 0;
    int d = 0;
    int h = 0;
    // Create one global toast to use it for all the toasts in the fragment,
    // to avoid the OutOfMemory caused by too many toasts.
    Toast toast = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        act = (MainActivity) this.getActivity();


        // Inflate the layout for this fragment
        final View apfView = inflater.inflate(R.layout.fragment_attribute_presets, container, false);

        toast = Toast.makeText(apfView.getContext(), "", Toast.LENGTH_SHORT);
        ImageView eiv = (ImageView) apfView.findViewById(R.id.element_IV);
        final EditText mnet = (EditText) apfView.findViewById(R.id.name_ET);
        final TextView remPoints = (TextView) apfView.findViewById(R.id.remaining_points);
        final TextView atv = (TextView) apfView.findViewById(R.id.attack_TV);
        final TextView dtv = (TextView) apfView.findViewById(R.id.defense_TV);
        final TextView htv = (TextView) apfView.findViewById(R.id.hitPoints_TV);
        SetViews(eiv, mnet, remPoints, atv, dtv, htv);



        /*
        // region Attack buttons

        // Find the View that shows the ATTACK +
        final Button aplus = (Button) apfView.findViewById(R.id.attackPlus_B);
        // Find the View that shows the ATTACK -
        final Button aminus = (Button) apfView.findViewById(R.id.attackMinus_B);
        SetButtonVisibility(aminus, View.INVISIBLE);


        // Set a click listener on ATTACK + View
        aplus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the attack plus button is clicked on.
            @Override
            public void onClick(View view) {
                if (act.monc.getRemainingPoints() <= 0){
                    Toast toast = Toast.makeText(apfView.getContext(), "You cannot spend more than " + act.monc.getRemainingPoints() + " points.", Toast.LENGTH_SHORT);
                    toast.show();
                    SetButtonVisibility(aplus, View.INVISIBLE);
                } else {
                    if (!aminus.isEnabled()) {
                        SetButtonVisibility(aminus, View.VISIBLE);
                    }
                    a ++;
                    act.monc.setRemainingPoints(act.monc.getRemainingPoints() - 1);
                    atv.setText(String.valueOf(act.monc.getAttack() + a));
                    remPoints.setText("You have got " + act.monc.getRemainingPoints() + " left.");
                }
            }
        });


        // Set a click listener on ATTACK - View
        aminus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the attack plus button is clicked on.
            @Override
            public void onClick(View view) {

                if (a <= 0) {
                    Toast toast = Toast.makeText(apfView.getContext(), "The added value cannot be less than 0.", Toast.LENGTH_SHORT);
                    toast.show();
                    SetButtonVisibility(aminus, View.INVISIBLE);
                } else {
                if (!aplus.isEnabled()){
                    SetButtonVisibility(aplus, View.VISIBLE);
                }
                    a --;
                    act.monc.setRemainingPoints(act.monc.getRemainingPoints() + 1);
                    atv.setText(String.valueOf(act.monc.getAttack() + a));
                    remPoints.setText("You have got " + act.monc.getRemainingPoints() + " left.");
                }
            }
        });

        // endregion

        // region Defense buttons

        // Find the View that shows the DEFENSE +
        final Button dplus = (Button) apfView.findViewById(R.id.defensePlus_B);
        // Find the View that shows the DEFENSE -
        final Button dminus = (Button) apfView.findViewById(R.id.defenseMinus_B);
        SetButtonVisibility(dminus, View.INVISIBLE);

        // Set a click listener on DEFENSE + View
        dplus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the attack plus button is clicked on.
            @Override
            public void onClick(View view) {
                if (SumIsMore()){
                    Toast toast = Toast.makeText(apfView.getContext(), "You cannot spend more than " + act.monc.getRemainingPoints() + " points.", Toast.LENGTH_SHORT);
                    toast.show();
                    SetButtonVisibility(dplus, View.INVISIBLE);
                } else {
                    if (!dminus.isEnabled()) {
                        SetButtonVisibility(dminus, View.VISIBLE);
                    }
                    d ++;
                    act.monc.setRemainingPoints(act.monc.getRemainingPoints() - 1);
                    dtv.setText(String.valueOf(act.monc.getDefense() + d ));
                    remPoints.setText("You have got " + act.monc.getRemainingPoints() + " left.");
                }
            }
        });


        // Set a click listener on DEFENSE - View
        dminus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the attack plus button is clicked on.
            @Override
            public void onClick(View view) {

                if (d <= 0) {
                    Toast toast = Toast.makeText(apfView.getContext(), "The added value cannot be less than 0.", Toast.LENGTH_SHORT);
                    toast.show();
                    SetButtonVisibility(dminus, View.INVISIBLE);
                } else {
                    if (!dplus.isEnabled()){
                        SetButtonVisibility(dplus, View.VISIBLE);
                    }
                    d --;
                    act.monc.setRemainingPoints(act.monc.getRemainingPoints() + 1);
                    dtv.setText(String.valueOf(act.monc.getDefense() + d ));
                    remPoints.setText("You have got " + act.monc.getRemainingPoints() + " left.");
                }
            }
        });

        // endregion

        // region HP buttons

        // Find the View that shows the HP +
        final Button hplus = (Button) apfView.findViewById(R.id.hitPointsPlus_B);
        // Find the View that shows the HP -
        final Button hminus = (Button) apfView.findViewById(R.id.hitPointsMinus_B);
        SetButtonVisibility(hminus, View.INVISIBLE);

        // Set a click listener on HP + View
        hplus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the attack plus button is clicked on.
            @Override
            public void onClick(View view) {
                if (SumIsMore()){
                    Toast toast = Toast.makeText(apfView.getContext(), "You cannot spend more than " + act.monc.getRemainingPoints() + " points.", Toast.LENGTH_SHORT);
                    toast.show();
                    SetButtonVisibility(hplus, View.INVISIBLE);
                } else {
                    if (!hminus.isEnabled()) {
                        SetButtonVisibility(hminus, View.VISIBLE);
                    }
                    h ++;
                    act.monc.setRemainingPoints(act.monc.getRemainingPoints() - 1);
                    htv.setText(String.valueOf(act.monc.getHitPoints() + h ));
                    remPoints.setText("You have got " + act.monc.getRemainingPoints() + " left.");
                }
            }
        });


        // Set a click listener on HP - View
        hminus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the attack plus button is clicked on.
            @Override
            public void onClick(View view) {
                if (h <= 0) {
                    Toast toast = Toast.makeText(apfView.getContext(), "The added value cannot be less than 0.", Toast.LENGTH_SHORT);
                    toast.show();
                    SetButtonVisibility(hminus, View.INVISIBLE);
                } else {
                    if (!hplus.isEnabled()){
                        SetButtonVisibility(hplus, View.VISIBLE);
                    }
                    h --;
                    act.monc.setRemainingPoints(act.monc.getRemainingPoints() + 1);
                    htv.setText(String.valueOf(act.monc.getHitPoints() + h ));
                    remPoints.setText("You have got " + act.monc.getRemainingPoints() + " left.");
                }
            }
        });

        // endregion
*/
        // region Random name - not working yet
        /*
        // Find the Random name button
        ImageButton rndNameBtn = (ImageButton) apfView.findViewById(R.id.randomName_IB);
        // What does the random name button
        rndNameBtn.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view) {

                Monster.MonsterNaming();
            }

        });
        */

        // endregion

        // region Done button
        // Find the DONE button
        Button doneButton = (Button) apfView.findViewById(R.id.done_B);
        // The DONE button
        doneButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Done button View is clicked on.
            @Override
            public void onClick(View view) {
                act.monc.setName(String.valueOf(mnet.getText()));
                act.monc.setAttack(Integer.parseInt(String.valueOf(atv.getText())));
                act.monc.setDefense(Integer.parseInt(String.valueOf(dtv.getText())));
                act.monc.setMaxDefense(Integer.parseInt(String.valueOf(dtv.getText())));
                act.monc.setHitPoints(Integer.parseInt(String.valueOf(htv.getText())));
                act.monc.setMaxHitPoints(Integer.parseInt(String.valueOf(htv.getText())));
                Intent mainIntent = new Intent(act.getApplicationContext(), MoncActivity.class);
                mainIntent.putExtra("Monster", act.monc);
                startActivity(mainIntent);
            }
        });
        // endregion

        return apfView;
    }

    private void SetViews(ImageView eiv, EditText mnet, TextView remPoints, TextView atv, TextView dtv, TextView htv) {
        // Show the picture of the element
        eiv.setImageResource(act.monc.getElementPicture());

        // Show the monster's name
        mnet.setText(act.monc.getName());

        // Show the remaining points
        remPoints.setText("You have got " + act.monc.getRemainingPoints() + " left.");

        // Show the attributes
        atv.setText(String.valueOf(act.monc.getAttack() + a));     // Attack
        dtv.setText(String.valueOf(act.monc.getDefense() + d));    // Defense
        htv.setText(String.valueOf(act.monc.getHitPoints() + h));  // HitPoints
    }

    public boolean SumIsMore () {
        if (a + d + h >= act.monc.getRemainingPoints()) {
            return true;
        } else {
            return false;
        }
    }


    // Get the monster's remaining points. For now there is only one monster (pos 0).
    // ToDo Parameterize the fragment with the position of the monster, or the ID.
    AttributeSpender spender = new AttributeSpender(StableSingleton.getInstance().getArray().get(0).getRemainingPoints());

    // region Helper methods

    // Button binder

    private void ButtonBinder () {}



    // endregion
}
