package com.example.android.elmo;


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


    public AttributePresetsFragment(boolean pHideEI) {
        this.hideEI = pHideEI;
    }

    public AttributePresetsFragment() {
        // Required empty public constructor
    }


    Monster monc = null;
    AttributeSpender spender = null;
    boolean hideEI = false;
    // Attribute setter buttons
    Button aplus = null;
    Button aminus = null;
    Button dplus = null;
    Button dminus = null;
    Button hplus = null;
    Button hminus = null;

    // Attribute amounts
    TextView atv = null;
    TextView dtv = null;
    TextView htv = null;
    TextView remPoints = null;

    // Create one global toast to use it for all the toasts in the fragment,
    // to avoid the OutOfMemory caused by too many toasts.
    Toast toast = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        monc = StableSingleton.getInstance().getArray().get(0);

        // Get the monster's remaining points. For now there is only one monster (pos 0).
        // ToDo Parameterize the fragment with the position of the monster, or the ID.
        spender = new AttributeSpender(monc.getRemainingPoints());


        // Inflate the layout for this fragment
        final View apfView = inflater.inflate(R.layout.fragment_attribute_presets, container, false);

        toast = Toast.makeText(apfView.getContext(), "", Toast.LENGTH_SHORT);
        ImageView eiv = (ImageView) apfView.findViewById(R.id.element_IV);
        final EditText mnet = (EditText) apfView.findViewById(R.id.name_ET);
        remPoints = (TextView) apfView.findViewById(R.id.remaining_points);
        atv = (TextView) apfView.findViewById(R.id.attack_TV);
        dtv = (TextView) apfView.findViewById(R.id.defense_TV);
        htv = (TextView) apfView.findViewById(R.id.hitPoints_TV);
        SetImages(eiv, mnet, hideEI);
        SetAttributes();

        // Create buttons
        // Find the View that shows the ATTACK +
        aplus = (Button) apfView.findViewById(R.id.attackPlus_B);
        // Find the View that shows the ATTACK -
        aminus = (Button) apfView.findViewById(R.id.attackMinus_B);
        // Find the View that shows the DEFENSE +
        dplus = (Button) apfView.findViewById(R.id.defensePlus_B);
        // Find the View that shows the DEFENSE -
        dminus = (Button) apfView.findViewById(R.id.defenseMinus_B);
        // Find the View that shows the HP +
        hplus = (Button) apfView.findViewById(R.id.hitPointsPlus_B);
        // Find the View that shows the HP -
        hminus = (Button) apfView.findViewById(R.id.hitPointsMinus_B);
        RefreshVisibility();

        // region Bind buttons click

        // Attack buttons
        // Attack plus
        aplus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the attack plus button is clicked on.
            @Override
            public void onClick(View view) {
                AttributeIncreaser(1, Constants.ATTACK);

            }
        });

        // Attack minus
        aminus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the attack plus button is clicked on.
            @Override
            public void onClick(View view) {
                AttributeIncreaser(-1, Constants.ATTACK);

            }
        });

        //Defense buttons
        // Defense plus
        dplus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the attack plus button is clicked on.
            @Override
            public void onClick(View view) {
                AttributeIncreaser(1, Constants.DEFENSE);

            }
        });

        // Defense minus
        dminus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the attack plus button is clicked on.
            @Override
            public void onClick(View view) {
                AttributeIncreaser(-1, Constants.DEFENSE);

            }
        });

        // HP buttons
        // HP plus
        hplus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the attack plus button is clicked on.
            @Override
            public void onClick(View view) {
                AttributeIncreaser(1, Constants.HITPOINTS);

            }
        });

        // HP minus
        hminus.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the attack plus button is clicked on.
            @Override
            public void onClick(View view) {
                AttributeIncreaser(-1, Constants.HITPOINTS);

            }
        });

        // endregion

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
                monc.setName(String.valueOf(mnet.getText()));
                monc.setAttack(Integer.parseInt(String.valueOf(atv.getText())));
                monc.setDefense(Integer.parseInt(String.valueOf(dtv.getText())));
                monc.setMaxDefense(Integer.parseInt(String.valueOf(dtv.getText())));
                monc.setMaxHitPoints(Integer.parseInt(String.valueOf(htv.getText())));
                CloseFragment();

            }
        });
        // endregion

        return apfView;
    }

    private void CloseFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onResume(){
        super.onResume();
        //OnResume Fragment
        // To prevent the attributes fully respend. The user can spend only once the available points.
        spender = new AttributeSpender(monc.getRemainingPoints());
        RefreshVisibility();
    }

    // region Helper methods
    private void AttributeIncreaser(int dif, int attr) {

        switch (attr) {
            case Constants.ATTACK:
                if (spender.IncreaseAttribute(Constants.ATTACK, dif) == 0){
                    monc.setAttack(monc.getAttack() + dif);
                    monc.setRemainingPoints(monc.getRemainingPoints() - dif);
                } else {
                    toast.cancel();
                    toast.makeText(getActivity(), "You cannot increase the attack.", Toast.LENGTH_SHORT).show();
                }
                break;
            case Constants.DEFENSE:
                if (spender.IncreaseAttribute(Constants.DEFENSE, dif) == 0){
                    monc.setDefense(monc.getDefense() + dif);
                    monc.setRemainingPoints(monc.getRemainingPoints() - dif);
                } else {
                    toast.cancel();
                    toast.makeText(getActivity(), "You cannot increase the Defense.", Toast.LENGTH_SHORT).show();
                }
                break;
            case Constants.HITPOINTS:
                if (spender.IncreaseAttribute(Constants.HITPOINTS, dif) == 0){
                    monc.setMaxHitPoints(monc.getMaxHitPoints() + dif);
                    monc.setRemainingPoints(monc.getRemainingPoints() - dif);
                } else {
                    toast.cancel();
                    toast.makeText(getActivity(), "You cannot increase the HitPoints.", Toast.LENGTH_SHORT).show();
                }
                break;
            default: // ToDo Notif or log exception
        }

        SetAttributes();
        RefreshVisibility();
    }

    private void SetImages(ImageView eiv, EditText mnet, boolean hideElementImage) {
        // Show the picture of the element

        if (!hideElementImage) {
        eiv.setImageResource(monc.getElementPicture());
        } else {
            eiv.setVisibility(View.GONE);
        }

        // Show the monster's name
        mnet.setText(monc.getName());
    }

    private void SetAttributes () {
        // Show the attributes
        atv.setText(String.valueOf(monc.getAttack()));     // Attack
        dtv.setText(String.valueOf(monc.getDefense()));    // Defense
        htv.setText(String.valueOf(monc.getMaxHitPoints()));  // HitPoints

        // Show the remaining points
        remPoints.setText("You have got " + monc.getRemainingPoints() + " left.");
    }

    // Button binder

    private void RefreshVisibility () {

        if (spender.getmRemainingPoints() == 0) {
            UiHelper.SetButtonVisibility(aplus, View.INVISIBLE);
            UiHelper.SetButtonVisibility(dplus, View.INVISIBLE);
            UiHelper.SetButtonVisibility(hplus, View.INVISIBLE);
        } else {
            UiHelper.SetButtonVisibility(aplus, View.VISIBLE);
            UiHelper.SetButtonVisibility(dplus, View.VISIBLE);
            UiHelper.SetButtonVisibility(hplus, View.VISIBLE);
        }

        if (spender.getmAttack() == 0) {
            UiHelper.SetButtonVisibility(aminus, View.INVISIBLE);
        } else {
            UiHelper.SetButtonVisibility(aminus, View.VISIBLE);
        }

        if (spender.getmDefense() == 0) {
            UiHelper.SetButtonVisibility(dminus, View.INVISIBLE);
        } else {
            UiHelper.SetButtonVisibility(dminus, View.VISIBLE);
        }

        if (spender.getmHitPoints() == 0) {
            UiHelper.SetButtonVisibility(hminus, View.INVISIBLE);
        } else {
            UiHelper.SetButtonVisibility(hminus, View.VISIBLE);
        }

    }



    // endregion
}
