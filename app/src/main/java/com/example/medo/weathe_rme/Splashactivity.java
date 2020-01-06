package com.example.medo.weathe_rme;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splashactivity extends AppCompatActivity {
    TextView tvtitle;
    Typeface type;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.splashtheme);
        setContentView(R.layout.activity_splashactivity);
        image = (ImageView) findViewById(R.id.image);
        Animation set0 = AnimationUtils.loadAnimation(Splashactivity.this , R.anim.image);
        image.startAnimation(set0);
        tvtitle = (TextView) findViewById(R.id.tvtitle);
        type = Typeface.createFromAsset(getAssets(), "fonts/font2.TTF");
        tvtitle.setTypeface(type);
        Animation set1 = AnimationUtils.loadAnimation(Splashactivity.this , R.anim.splash);
        tvtitle.startAnimation(set1);
        set1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent i = new Intent(Splashactivity.this , MainActivity.class);
                startActivity(i);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
