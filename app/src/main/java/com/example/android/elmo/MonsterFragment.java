package com.example.android.elmo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anasztázia on 2017.10.10..
 */

public class MonsterFragment extends Fragment implements IRefreshView {

    public MonsterFragment() {
        // Required empty public constructor
    }

    MoncActivity act = null;
    FragmentManager manager = null;

    // Attribute TextViews
    TextView monsterName = null;
    TextView attack = null;
    TextView defense = null;
    TextView hitPoints = null;
    TextView xp = null;
    TextView lvl = null;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View moncView = inflater.inflate(R.layout.fragment_monster, container, false);

            act = (MoncActivity) this.getActivity();
        manager = getActivity().getSupportFragmentManager();

        // region Monster image


        // Find the View that shows the PICTURE OF THE MONSTER
        ImageView monc_picture = (ImageView) moncView.findViewById(R.id.monc_picture);
        //Set the view with the corresponding picture
        monc_picture.setImageResource(act.monc.mMonsterPicture);
        // endregion

        // region Attribute TextViews get values

        // Monster name
        monsterName = (TextView) moncView.findViewById(R.id.monsterName_TV);
        // ATTACK
        attack = (TextView) moncView.findViewById(R.id.attack_TV);
        // DEFENSE
        defense = (TextView) moncView.findViewById(R.id.defense_TV);
        // HIT POINTS
        hitPoints = (TextView) moncView.findViewById(R.id.hitPoints_TV);
        // XP
        xp = (TextView) moncView.findViewById(R.id.xp_TV);
        // LEVEL
        lvl = (TextView) moncView.findViewById(R.id.level_TV);

        // endregion

        SetAttributes();

        return moncView;



    }

    @Override
    public void onResume(){
        super.onResume();
        SetAttributes();
    }

    //
    // Helper methods
    //

    public void SetAttributes() {

        // Fill the previously created TextViews with the corresponding data
        monsterName.setText(act.monc.mName);
        attack.setText(String.valueOf(act.monc.getAttack()));
        defense.setText(String.valueOf(act.monc.getDefense()));
        hitPoints.setText(String.valueOf(act.monc.getHitPoints()));
        xp.setText(String.valueOf(act.monc.LevelMaxXp()) + " / " + String.valueOf(act.monc.getXP()));
        lvl.setText(String.valueOf(act.monc.getLevel()));

    }

}
