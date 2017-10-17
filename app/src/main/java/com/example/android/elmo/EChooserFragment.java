package com.example.android.elmo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class EChooserFragment extends Fragment {

       public EChooserFragment() {
        // Required empty public constructor
    }

    MainActivity act = null;
    FragmentManager manager = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ecView = inflater.inflate(R.layout.fragment_echooser, container, false);


        act = (MainActivity) this.getActivity();

        //region Element buttons

        manager = getActivity().getSupportFragmentManager();

        // Find the View that shows the fire element
        ImageView fire = (ImageView) ecView.findViewById(R.id.fire);
        // Set a click listener on that View
        fire.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the fire is clicked on.
            @Override
            public void onClick(View view) {
                CreateMonster(new FireMonster());
            }
        });

        // Find the View that shows the earth element
        ImageView earth = (ImageView) ecView.findViewById(R.id.earth);
        // Set a click listener on that View
        earth.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the earth is clicked on.
            @Override
            public void onClick(View view) {
                CreateMonster(new EarthMonster());
            }
        });

        // Find the View that shows the air element
        ImageView air = (ImageView) ecView.findViewById(R.id.air);
        // Set a click listener on that View
        air.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the MoncActivity View is clicked on.
            @Override
            public void onClick(View view) {
                CreateMonster(new AirMonster());
            }
        });

        // Find the View that shows the water element
        ImageView water = (ImageView) ecView.findViewById(R.id.water);
        // Set a click listener on that View
        water.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the water is clicked on.
            @Override
            public void onClick(View view) {
                CreateMonster(new WaterMonster());
            }
        });

        // endregion

        return ecView;
    }

    private void CreateMonster(Monster monster) {
        StableSingleton.getInstance().addToMonsterArray(monster);
        UiHelper.FragmentShower(manager, R.id.fragment_container);
    }


}
