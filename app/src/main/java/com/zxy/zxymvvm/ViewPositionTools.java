package com.zxy.zxymvvm;

import android.animation.Animator;
import android.view.View;

class ViewPositionTools {
    public static void to(View view,int x,int y){
        view.animate().translationX(x).translationY(y).setDuration(100).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
