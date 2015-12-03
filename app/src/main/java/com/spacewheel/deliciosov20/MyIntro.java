package com.spacewheel.deliciosov20;

import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by Amrit on 22/11/15.
 */
public class MyIntro extends AppIntro {

    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {

        // Add your slide's fragments here
        // AppIntro will automatically generate the dots indicator and buttons.

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
        //addSlide(AppIntroFragment.newInstance(title, description, image, background_colour));
        addSlide(AppIntroFragment.newInstance(
                getString(R.string.intro_title),
                getString(R.string.intro_desc),
                R.mipmap.delicioso_icon,
                Color.parseColor("#2196F3")));

        setFlowAnimation();
        showDoneButton(true);

        // OPTIONAL METHODS
        // Override bar/separator color
        //setBarColor(Color.parseColor("#3F51B5")); // Dark blue
        //setSeparatorColor(Color.parseColor("#2196F3")); // Blue



        // Hide Skip/Done button
        showSkipButton(false);
        showDoneButton(false);

        // Turn vibration on and set intensity
        // NOTE: you will probably need to ask VIBRATE permesssion in Manifest
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onDonePressed() {

        finish();

    }

    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
    }

}
