package com.example.android.elmo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class FightButtonFragment extends Fragment {

    public FightButtonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fbView = inflater.inflate(R.layout.fragment_fight_button, container, false);

        // Fight button
        ImageButton fight_btn = (ImageButton) fbView.findViewById(R.id.fight);
        fight_btn.setImageResource(R.drawable.fight_btn);

        // What does the fight button
        fight_btn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the fight button View is clicked on.
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(view.getContext(), FightActivity.class);
                startActivityForResult(mainIntent, 1);

            }
        });

        // endregion

        return fbView;
    }

}
