package com.moringaschool.booksapp.ui;


import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.booksapp.R;



import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimActivity extends AppCompatActivity {
    Animation anim;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        overridePendingTransition(
                R.anim.zoom_in, R.anim.zoom_out);

        imageView=(ImageView)findViewById(R.id.imageView2);
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(AnimActivity.this, LoginActivity.class));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        imageView.startAnimation(anim);
    }
}