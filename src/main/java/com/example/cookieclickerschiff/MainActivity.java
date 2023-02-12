package com.example.cookieclickerschiff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    boolean upgrade1= false;
    boolean upgrade2 = false;
    Timer timer = new Timer();
    ImageView img;
    TextView count;
    ConstraintLayout layout;
    int bagels = 0;
    int revenue=0;
    TextView target;
    Button sell;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.bagel);
        count = findViewById(R.id.count);
        layout = findViewById(R.id.id_layout);
        target = findViewById(R.id.rev);
        sell=findViewById(R.id.button);


        final ScaleAnimation scaleAnimate = new ScaleAnimation(1.0f,2.0f,1.0f,2.0f, Animation.RELATIVE_TO_SELF,.5f,Animation.RELATIVE_TO_SELF,.5f);
        scaleAnimate.setDuration(200);
        img.setOnClickListener(v -> {
            img.startAnimation(scaleAnimate);
            bagels++; //increase +1 to count, then layout


            TextView textInCode = new TextView(MainActivity.this); //plus one
            textInCode.setId(View.generateViewId());
            textInCode.setText("+1");
            ConstraintSet constraintSet = new ConstraintSet();
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            textInCode.setLayoutParams(params);

            layout.addView(textInCode);
            constraintSet.clone(layout);

            constraintSet.connect(textInCode.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
            constraintSet.connect(textInCode.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);
            constraintSet.connect(textInCode.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);
            constraintSet.connect(textInCode.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM);
            Log.d("TAG",layout.getChildCount()+ ""); //removing views, not transparent
            double b =  (Math.random() * .35) + .3;

            AnimationSet set = new AnimationSet(true);
            Animation translate = new TranslateAnimation(0, 0, 500, 0);
            set.addAnimation(translate);

            Animation alphaAnim = new AlphaAnimation(1.0f, 0.0f); // remove text
            alphaAnim.setDuration(1000);
            alphaAnim.setFillAfter(true);

            alphaAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    layout.post(() -> layout.removeView(textInCode));
                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            textInCode.animate().translationY(100f).setDuration(0).start();
            textInCode.animate().translationY(-100f).setDuration(1000).start();
            textInCode.startAnimation(alphaAnim);
            constraintSet.setVerticalBias(textInCode.getId(), .45f);
            constraintSet.setHorizontalBias(textInCode.getId(), (float) b);
            constraintSet.applyTo(layout);


            count.setText("bagels ready to sell: " + bagels);


            sell.setOnClickListener(v13 -> {
                revenue += bagels;
                bagels -= bagels;
                target.setText("revenue:$" + revenue);
                count.setText("bagels ready to sell: " + bagels);
                    if(revenue >100) {
                        Button button_upgrade = new Button(MainActivity.this);
                        button_upgrade.setId(View.generateViewId());
                        button_upgrade.setText("CLICK HERE TO MAKE A SHOP");
                        ConstraintSet constraintSet1 = new ConstraintSet();
                        ConstraintLayout.LayoutParams params1 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                        button_upgrade.setLayoutParams(params1);

                        layout.addView(button_upgrade);
                        constraintSet1.clone(layout);
                        constraintSet1.connect(button_upgrade.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
                        constraintSet1.connect(button_upgrade.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);
                        constraintSet1.connect(button_upgrade.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);
                        constraintSet1.connect(button_upgrade.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM);
                        constraintSet1.setVerticalBias(button_upgrade.getId(),  .7f);
                        constraintSet1.setHorizontalBias(button_upgrade.getId(),  .7f);
                        constraintSet1.applyTo(layout);
                        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
                        aniSlide.setDuration(1500);
                        Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
                        aniRotate.setDuration(1500);
                        button_upgrade.startAnimation(aniSlide);

                        button_upgrade.setOnClickListener(v1 -> {
                            layout.removeView(button_upgrade);
                            upgrade1= true;
                            button_upgrade.setEnabled(false);
                            ImageView upgradeR = new ImageView(MainActivity.this);
                            upgradeR.setId(View.generateViewId());
                            upgradeR.setImageResource(R.drawable.restaruant);
                            upgradeR.setLayoutParams(params1);
                            layout.addView(upgradeR);
                            constraintSet1.clone(layout);
                            constraintSet1.connect(upgradeR.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
                            constraintSet1.connect(upgradeR.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);
                            constraintSet1.connect(upgradeR.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);
                            constraintSet1.connect(upgradeR.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM);
                            double b1 =(Math.random());
                            constraintSet1.setVerticalBias(upgradeR.getId(), (float) .8);
                            constraintSet1.setHorizontalBias(upgradeR.getId(), (float)(b1));
                            constraintSet1.applyTo(layout);

                            upgradeR.startAnimation(aniRotate);
                            revenue -= 100;

                            target.setText("revenue:$" + revenue);

                        });


                    } //upgrade2
                    if(revenue>250){
                        Button button_upgrade2 = new Button(MainActivity.this);
                        button_upgrade2.setId(View.generateViewId());
                        button_upgrade2.setText("CLICK HERE TO GET NEW CREAM CHEESE");
                        ConstraintSet constraintSet1 = new ConstraintSet();
                        ConstraintLayout.LayoutParams params1 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                        button_upgrade2.setLayoutParams(params1);

                        layout.addView(button_upgrade2);
                        constraintSet1.clone(layout);
                        constraintSet1.connect(button_upgrade2.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
                        constraintSet1.connect(button_upgrade2.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);
                        constraintSet1.connect(button_upgrade2.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);
                        constraintSet1.connect(button_upgrade2.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM);
                        constraintSet1.setVerticalBias(button_upgrade2.getId(), .65f);
                        constraintSet1.setHorizontalBias(button_upgrade2.getId(), .65f);
                        constraintSet1.applyTo(layout);
                        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
                        aniSlide.setDuration(1500);
                        Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
                        aniRotate.setDuration(1500);
                        button_upgrade2.startAnimation(aniSlide);
                        button_upgrade2.setOnClickListener(v12 -> {
                            layout.removeView(button_upgrade2);
                            upgrade2= true;
                            button_upgrade2.setEnabled(false);
                            ImageView upgradeC = new ImageView(MainActivity.this);
                            upgradeC.setId(View.generateViewId());
                            upgradeC.setImageResource(R.drawable.cream);
                            upgradeC.setLayoutParams(params1);
                            layout.addView(upgradeC);
                            constraintSet1.clone(layout);
                            constraintSet1.connect(upgradeC.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
                            constraintSet1.connect(upgradeC.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);
                            constraintSet1.connect(upgradeC.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);
                            constraintSet1.connect(upgradeC.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM);
                            double b1 = Math.random();
                            constraintSet1.setVerticalBias(upgradeC.getId(), (float) .8);
                            constraintSet1.setHorizontalBias(upgradeC.getId(), (float)(b1));
                            constraintSet1.applyTo(layout);

                            upgradeC.startAnimation(aniRotate);
                            Toast.makeText(getApplicationContext(), "Yay!", Toast.LENGTH_LONG).show();
                            revenue -= 250;

                            target.setText("revenue:$" + revenue);
                        });

                    }
            });
            timer.scheduleAtFixedRate(new TimerTask() { //passive
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        if (upgrade1) {
                            bagels = bagels + 1;
                            count.setText("bagels ready to sell: " + bagels);
                        }
                        if(upgrade2){
                            bagels = bagels +2;
                            count.setText("bagels ready to sell: " + bagels);
                        }
                    });
                }

            },100,10000);




        });

    }

}