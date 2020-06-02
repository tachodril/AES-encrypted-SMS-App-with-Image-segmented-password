package com.example.aesencryptionsmsappwithimagesegmentedpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView openRegister;
    ImageView img1, img2, img3;
    int imageNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        receiveClicks();

        SharedPreferences sharedPreferences=getSharedPreferences("pref", MODE_PRIVATE);
        imageNo=sharedPreferences.getInt("login_image",-1);

        //startActivity(new Intent(this,AesActivity.class));
    }

    private void receiveClicks() {
        openRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                //finish();
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageNo==1){
                    Intent intent = new Intent(MainActivity.this,FinalLoginActivity.class);
                    intent.putExtra("image", "1");
                    finish();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Incorrect image !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageNo==2){
                    Intent intent = new Intent(MainActivity.this,FinalLoginActivity.class);
                    intent.putExtra("image", "2");
                    finish();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Incorrect image !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageNo==3){
                    Intent intent = new Intent(MainActivity.this,FinalLoginActivity.class);
                    intent.putExtra("image", "3");
                    finish();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Incorrect image !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        openRegister=findViewById(R.id.open_register);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
    }
}