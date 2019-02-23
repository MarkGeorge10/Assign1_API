package com.project.android.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class MainActivity extends AppCompatActivity {

    Button servicebtn,postbtn,Jsonservicesbtn;

    FlipperLayout flipperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         flipperLayout=(FlipperLayout) findViewById(R.id.flipper);

        int [] images = {R.drawable.back};

        for(int i=0; i<images.length; i++){

            FlipperView view = new FlipperView(getBaseContext());

            view.setImageDrawable(images[i]).setDescription("Images"+(i+1));

            flipperLayout.addFlipperView(view);

            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {

                }
            });

        }







        servicebtn =(Button) findViewById(R.id.btn2);
        Jsonservicesbtn = (Button) findViewById(R.id.btn1);

        postbtn = (Button) findViewById(R.id.btn);

         servicebtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent myIntent = new Intent(MainActivity.this, ServiceActivity.class);
                 startActivity(myIntent);

             }
         });

        Jsonservicesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivity(myIntent);

            }
        });

        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity.this, Retrofit_postActivity.class);
                startActivity(myIntent);

            }
        });
    }
}
