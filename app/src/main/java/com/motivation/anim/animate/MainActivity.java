package com.motivation.anim.animate;

import android.animation.Animator;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 ImageView imageView;
    boolean bb=true;
    private int savedWidth,savedHeight;
    CardView cardView;
    RelativeLayout relativeLayout;
    private boolean sizeChanged;
    Animator anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          imageView=(ImageView)findViewById(R.id.imageView2);
          cardView=(CardView)findViewById(R.id.card);
        relativeLayout=(RelativeLayout)findViewById(R.id.lay) ;
        Toast.makeText(getApplicationContext(),String.valueOf(sizeChanged),Toast.LENGTH_SHORT).show();
         imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               cardView.animate()
                       .setInterpolator(new FastOutSlowInInterpolator())
                       .setListener(new Animator.AnimatorListener() {
                           @Override
                           public void onAnimationStart(Animator animation) {
                               DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
                               float dp = 250f;
                               float fpixels = metrics.density * dp;
                               int pixels = (int) (fpixels + 0.5f);
                               TransitionManager.beginDelayedTransition(relativeLayout);
                               ViewGroup.LayoutParams params = cardView.getLayoutParams();
                               if (sizeChanged) {
                                   params.width=savedWidth;
                                   params.height = savedHeight;

                               } else {
                                   savedWidth=params.width;
                                   savedHeight = params.height;
                                   params.height = pixels;
                                   params.width=pixels;


                               }
                               sizeChanged = !sizeChanged;
                               Toast.makeText(getApplicationContext(),String.valueOf(sizeChanged),Toast.LENGTH_SHORT).show();
                               cardView.setLayoutParams(params);


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
                       })
                       .start();
             }
         });

    }
    void anim() {
        int cx =relativeLayout.getLeft();
        int cy = relativeLayout.getBottom();
        int finalRadius = (int) Math.hypot(relativeLayout.getWidth(), relativeLayout.getHeight());
        if (bb) {



            anim = ViewAnimationUtils.createCircularReveal(cardView, cx, cy, 0, finalRadius);


            anim.setDuration(1000);
            anim.addListener(new Animator.AnimatorListener() {
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
        cardView.setVisibility(View.VISIBLE);


            anim.start();

            bb=false;

        } else {



            Animator anim2 = ViewAnimationUtils.createCircularReveal(relativeLayout, cx, cy, finalRadius, 0);
            anim2.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    cardView.setVisibility(View.GONE);


                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            anim2.setDuration(500);
            anim2.start();
            bb=true;
        }
    }
}
