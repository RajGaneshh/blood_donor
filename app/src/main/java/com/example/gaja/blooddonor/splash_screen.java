package com.example.gaja.blooddonor;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash_screen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView griveLogo = (ImageView) findViewById(R.id.splash);
        Animation logoFadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        griveLogo.startAnimation(logoFadeIn);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                Intent i = new Intent(splash_screen.this, login.class);
                startActivity(i);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
