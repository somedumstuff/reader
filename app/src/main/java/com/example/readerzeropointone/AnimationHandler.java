package com.example.readerzeropointone;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

public class AnimationHandler {

    //local vars
    Context context;
    ImageButton mainMenuButton;
    Boolean menuOpen;

    public void clickAnim(ImageButton mainMenuButton){
        this.mainMenuButton = mainMenuButton;
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mainMenuButton, "scaleX", 0.9f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mainMenuButton, "scaleY", 0.9f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    public void clickAnim(Button button){
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(button, "scaleX", 0.9f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(button, "scaleY", 0.9f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.setDuration(500);
        animatorSet.start();
    }


    public boolean openMenu(ImageButton mainMenuButton){
        ObjectAnimator rotateIn = ObjectAnimator.ofFloat(mainMenuButton, "rotation", 360f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotateIn);
        animatorSet.setTarget(mainMenuButton);
        animatorSet.start();
        menuOpen = true;
        return menuOpen;
    }

    public boolean closMenu(ImageButton mainMenuButton){
        ObjectAnimator rotateBack = ObjectAnimator.ofFloat(mainMenuButton, "rotation",  90f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotateBack);
        animatorSet.setTarget(mainMenuButton);
        animatorSet.start();
        menuOpen = false;
        return menuOpen;
    }

    void showMenuItems(ImageView yepBlow, Button todayButton, Button savedButton, Button subsButton, Button addNewSubButton) {
        Button[] buttonArray = {
                todayButton, savedButton, subsButton, addNewSubButton
        };
        yepBlow.setVisibility(View.VISIBLE);
        FastOutSlowInInterpolator bounceInterpolator = new FastOutSlowInInterpolator();
        ObjectAnimator blowUpX = ObjectAnimator.ofFloat(yepBlow, "scaleX", 0, 40);
        ObjectAnimator blowUpY = ObjectAnimator.ofFloat(yepBlow, "scaleY", 0, 40);
        ObjectAnimator opacity = ObjectAnimator.ofFloat(yepBlow, "alpha", 0.9f);
        AnimatorSet blowItUp = new AnimatorSet();
        blowItUp.playTogether(blowUpX, blowUpY, opacity);
        blowItUp.setInterpolator(bounceInterpolator);
        blowItUp.setDuration(800);
        blowItUp.start();

        todayButton.setAlpha(0);
        savedButton.setAlpha(0);
        subsButton.setAlpha(0);
        addNewSubButton.setAlpha(0);

        for(int i = 0; i<buttonArray.length; i++){
            buttonArray[i].setVisibility(View.VISIBLE);
            ObjectAnimator rotateIn = ObjectAnimator.ofFloat(buttonArray[i], "rotation",  0, 360f);
            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(buttonArray[i], "alpha",  0, 1f);
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(buttonArray[i], "scaleX", 0, 1);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(buttonArray[i], "scaleY", 0, 1);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setInterpolator(bounceInterpolator);
            animatorSet.setTarget(buttonArray[i]);
            animatorSet.setDuration(800);
            animatorSet.setStartDelay(100+(100*i));
            animatorSet.playTogether(rotateIn, fadeIn, scaleX, scaleY);
            animatorSet.start();
        }
    }

    protected void closeMenuItems(ImageView yepBlow, Button todayButton, Button savedButton, Button subsButton, Button addNewSubButton) {
        Button[] buttonArray = {
                todayButton, savedButton, subsButton, addNewSubButton
        };

        FastOutSlowInInterpolator bounceInterpolator = new FastOutSlowInInterpolator();
        ObjectAnimator blowInX = ObjectAnimator.ofFloat(yepBlow, "scaleX", 40, 0);
        ObjectAnimator blowInY = ObjectAnimator.ofFloat(yepBlow, "scaleY", 40, 0);
        AnimatorSet blowItUp = new AnimatorSet();
        blowItUp.playTogether(blowInX, blowInY);
        blowItUp.setInterpolator(bounceInterpolator);
        blowItUp.setDuration(1000);
        blowItUp.start();

        todayButton.setAlpha(1);
        savedButton.setAlpha(1);
        subsButton.setAlpha(1);
        addNewSubButton.setAlpha(1);
        int temp = buttonArray.length;
        for(int i = 0; i<buttonArray.length; i++){
            ObjectAnimator rotateOut = ObjectAnimator.ofFloat(buttonArray[temp-1], "rotation",  360f, 1f);
            ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(buttonArray[temp-1], "scaleX", 1, 0);
            ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(buttonArray[temp-1], "scaleY", 1, 0);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.setInterpolator(bounceInterpolator);
            animatorSet2.setTarget(buttonArray[temp-1]);
            animatorSet2.setDuration(300);
            animatorSet2.setStartDelay(100+(25*i));
            animatorSet2.playTogether(rotateOut, scaleOutX, scaleOutY);
            animatorSet2.start();
            temp = temp - 1;
        }

    }


}
