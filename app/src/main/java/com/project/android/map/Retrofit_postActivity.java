package com.project.android.map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_postActivity extends AppCompatActivity {
    private TextView textViewResult,textViewResultupdate;
    Button Updatebtn,deletebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_post);



        textViewResult = findViewById(R.id.text_view_result);
        Updatebtn = findViewById(R.id.update_post);
        deletebtn = findViewById(R.id.delete_post);

        //textViewResultupdate = findViewById(R.id.update_post);









        Gson gson = new GsonBuilder().serializeNulls().create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final JsonPlaceHolderApi  jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);




        Post post = new Post(23, "New Title", "New Text");



        Call<Post> call = jsonPlaceHolderApi.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";

                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        Updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textViewResult.setText("");




                Post post = new Post(12, null, "New Text");

                Call<Post> call = jsonPlaceHolderApi.patchPost(5, post);

                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {

                        if (!response.isSuccessful()) {
                            textViewResult.setText("Code: " + response.code());
                            return;
                        }

                        Post postResponse = response.body();

                        String content = "";
                        content += "Code: " + response.code() + "\n";
                        content += "ID: " + postResponse.getId() + "\n";
                        content += "User ID: " + postResponse.getUserId() + "\n";
                        content += "Title: " + postResponse.getTitle() + "\n";
                        content += "Text: " + postResponse.getText() + "\n\n";

                        textViewResult.setText(content);
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        textViewResult.setText(t.getMessage());
                    }
                });

            }
        });


        deletebtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                textViewResult.setText("");

                Call<Void> call = jsonPlaceHolderApi.deletePost(0);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        textViewResult.setText("Code: " + response.code());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        textViewResult.setText(t.getMessage());
                    }
                });

            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View view) {


                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("test@gmail.com"));
                emailIntent.setType("text/plain");

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, textViewResult.getText().toString());

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Finished sending email...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Retrofit_postActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }



            }
        });


    }
}
