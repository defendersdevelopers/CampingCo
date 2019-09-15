package com.defenders.campingco;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.defenders.campingco.registrationscreen.walkthrough;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ImageView splashImage = findViewById(R.id.loading_image);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in);
        splashImage.startAnimation(animation);


        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null){

            new Handler().postDelayed(new Runnable() {
                public void run()
                {
                    startActivity(new Intent(splashScreen.this, walkthrough.class));
                    finish();
                }
            }, 2000);

        }
        else{

            new Handler().postDelayed(new Runnable() {
                public void run()
                {
                    startActivity(new Intent(splashScreen.this, tabsActivity.class));
                    finish();
                }
            }, 2000);

        }

    }
}
