package com.project.android.map;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class Onboardslider extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


// slide1
        addFragment(new Step.Builder().setTitle("APIs Application")
                .setContent("METHODS : GET - POST - DELETE")
                .setBackgroundColor(Color.parseColor("#9C9C9A")) // int background color
                .setDrawable(R.drawable.api) // int top drawable

                .build());

        // slide2

        addFragment(new Step.Builder().setTitle("Data API")
                .setContent("Using JSON parsing")
                .setBackgroundColor(Color.parseColor("#9C9C9A")) // int background color
                .setDrawable(R.drawable.ic_dns_black_24dp) // int top drawable

                .build());


        // slide3

        addFragment(new Step.Builder().setTitle("Map API")
                .setContent("Place Holder")
                .setBackgroundColor(Color.parseColor("#9C9C9A")) // int background color
                .setDrawable(R.drawable.ic_place_black_24dp) // int top drawable

                .build());









    }


    @Override
    public void currentFragmentPosition(int i) {
        //Toast.makeText(this, "Position : " + i, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishTutorial() {
        Intent tomain = new Intent(Onboardslider.this,MainActivity.class);
        startActivity(tomain);
        finish();
        Toast.makeText(this, "Tutorial finished", Toast.LENGTH_SHORT).show();
        finish();
    }
}
