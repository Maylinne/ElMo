package com.example.android.elmo;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedingFragment extends Fragment implements IRefreshView {

    public FeedingFragment() {
        // Required empty public constructor
    }

    MoncActivity act = null;
    ProgressBar hungerMeter = null;
    TextView hungerText = null;
    static int foodElement = 0;
    RadioGroup rg = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View feedingView = inflater.inflate(R.layout.fragment_feeding, container, false);

        act = (MoncActivity) this.getActivity();

        // Toggle button instantiate
        rg = ((RadioGroup) feedingView.findViewById(R.id.toggleGroup));
        rg.setOnCheckedChangeListener(ToggleListener);

        BindToggleButtonsClick(rg);

        //  region Instantiate the hungerMeter, and the hungerText
        //

        //Initiate the progress bar, and the hunger text
        hungerMeter = (ProgressBar) feedingView.findViewById(R.id.hungerMeter_PB);
        hungerText = (TextView) feedingView.findViewById(R.id.hungerText_TV);

        // Setting the progress bar, and the hunger text
        hungerMeter.setMax(100);
        hungerMeter.getProgressDrawable().setColorFilter(act.monc.getElementColor(), PorterDuff.Mode.SRC_IN);


        // endregion

        // region Hungry button

        // Find the View that shows the HUNGRY BUTTON
        Button hungry_button = (Button) feedingView.findViewById(R.id.hungry_button);

        hungry_button.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the FEEDING BUTTON is clicked on.
            @Override
            public void onClick(View view) {
                act.toast.cancel();
                ArrayList<String> speach = null;

                if (foodElement == 0) {
                    act.toast = Toast.makeText(feedingView.getContext(), "Please, select a food first!", Toast.LENGTH_SHORT);
                } else {
                    Random r = new Random();
                    int pos = 0;

                    if (act.monc.canEat(foodElement)) {
                        int prevHunger = act.monc.mHunger;
                        speach = act.monc.GetAcceptedSpeach();
                        pos = r.nextInt(speach.size());
                        act.toast = Toast.makeText(feedingView.getContext(), speach.get(pos), Toast.LENGTH_SHORT);
                        act.monc.mHunger += r.nextInt(30 - 20) + 20;
                        StableSingleton.getInstance().DecreaseFoodAmount(new Food(foodElement, 1));
                        if (act.monc.mHunger >= 100) {
                            act.monc.Heal();
                            act.monc.mHunger = 0;
                            act.RefreshView();
                        }
                        hungerMeter.setProgress(act.monc.mHunger);
                        hungerText.setText(act.monc.HungerTextSelect());
                        UiHelper.AnimateProgressBar(hungerMeter, prevHunger, act.monc.mHunger, 1000);
                    } else {
                        speach = act.monc.GetDeniedSpeach();
                        pos = r.nextInt(speach.size());
                        act.toast = Toast.makeText(feedingView.getContext(), speach.get(pos), Toast.LENGTH_SHORT);
                    }
                }

                act.toast.show();
            }
        });

        // endregion

        SetAttributes();

        return feedingView;
    }

    private void BindToggleButtonsClick(RadioGroup radioGroup) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ToggleButton rb = (ToggleButton) radioGroup.getChildAt(i);
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((RadioGroup)view.getParent()).check(view.getId());
                    foodElement = Integer.parseInt(view.getTag().toString());
                }
            });
        }
    }

    //
    // Helper methods
    //

    // region ToggleButton toggling

    static final RadioGroup.OnCheckedChangeListener ToggleListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
            for (int j = 0; j < radioGroup.getChildCount(); j++) {
                final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                if ( view.getId() == i) {
                    view.setChecked(true);
                } else {
                    view.setChecked(false);
                }

            }
        }
    };

    // endregion

    public void SetAttributes () {
        hungerMeter.setProgress(act.monc.mHunger);
        hungerText.setText(act.monc.HungerTextSelect());
    }


}
