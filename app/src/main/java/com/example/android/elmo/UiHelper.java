package com.example.android.elmo;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by Anasztázia on 2017.09.18..
 */

public class UiHelper {

    public UiHelper() {

    }

    public static int GetColor(int p_red, int p_green, int p_blue) {
        String red = Integer.toHexString(p_red);
        String green = Integer.toHexString(p_green);
        String blue = Integer.toHexString(p_blue);

        if (red.length() == 1)
        {
            red = "0" + red;
        }
        if (green.length() == 1)
        {
            green = "0" + green;
        }
        if (blue.length() == 1)
        {
            blue = "0" + blue;
        }

        String colorHex = "#" + red + green + blue;
        return Color.parseColor(colorHex);
    }

    // region MoncActivity
    public static void AnimateProgressBar (ProgressBar progressBar, int start, int end, int duration) {

        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", start, end);
        progressAnimator.setDuration(duration);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
    }

    //endregion

    // region Fragments overall

    public static void FragmentShower (FragmentManager manager, int containerId) {

        AttributePresetsFragment nextFrag = new AttributePresetsFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(containerId, nextFrag ); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();

        // Required in the fragment to define a fragmentManager:
        // manager = getActivity().getSupportFragmentManager();
    }

    // endregion

    public static void SetButtonVisibility (Button btn, int visibility) {
        btn.setVisibility(visibility);
        btn.setEnabled(visibility == View.VISIBLE);
    }

    public void SetButtonExistance (Button btn, int visibility) {
        btn.setVisibility(View.VISIBLE);
        btn.setEnabled(true);
    }

}
