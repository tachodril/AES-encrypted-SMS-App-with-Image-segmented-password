package com.example.aesencryptionsmsappwithimagesegmentedpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    TextView openLogin;
    ImageView img1, img2, img3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        receiveClicks();
    }

    private void receiveClicks() {
        openLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLoginImage(1);
                Intent intent=new Intent(RegisterActivity.this, FinalRegisterActivity.class);
                intent.putExtra("image", "1");
                startActivity(intent);
                finish();
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLoginImage(2);
                Intent intent=new Intent(RegisterActivity.this, FinalRegisterActivity.class);
                intent.putExtra("image", "2");
                startActivity(intent);
                finish();
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLoginImage(3);
                Intent intent=new Intent(RegisterActivity.this, FinalRegisterActivity.class);
                intent.putExtra("image", "3");
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveLoginImage(int i) {
        // shared preferences
        SharedPreferences sharedPreferences=getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("login_image", i);
    }

    private void init() {
        openLogin=findViewById(R.id.open_register);
        img1=findViewById(R.id.ra_img1);
        img2=findViewById(R.id.ra_img2);
        img3=findViewById(R.id.ra_img3);
    }
}