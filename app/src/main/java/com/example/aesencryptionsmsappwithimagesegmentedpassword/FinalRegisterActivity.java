package com.example.aesencryptionsmsappwithimagesegmentedpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class FinalRegisterActivity extends AppCompatActivity {

    Button register;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;

    String imnumber;
    String curSequence="ABC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_register);

        init();
        setImages();

        receiveClicks();
    }

    private void saveLoginPin(String s) {
        // shared preferences
        SharedPreferences sharedPreferences=getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("login_pin", s);
        editor.commit();
    }

    private void receiveClicks() {

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLoginPin(curSequence);
                Toast.makeText(FinalRegisterActivity.this, "Successfully Registered !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FinalRegisterActivity.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(1);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(2);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(3);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(4);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(5);
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(6);
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(7);
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(8);
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(9);
            }
        });
    }

    private void addImage(int i) {
        if (curSequence.equals("ABC"))
        {
            curSequence="";
        }
        curSequence+=(""+i);
    }

    private void setImages() {
        if (getIntent().getStringExtra("image") != null){
            imnumber = getIntent().getStringExtra("image");

            if (imnumber.equals("1"))
            {
                img1.setImageDrawable(getDrawable(R.drawable.oneone));
                img2.setImageDrawable(getDrawable(R.drawable.onetwo));
                img3.setImageDrawable(getDrawable(R.drawable.onethree));
                img4.setImageDrawable(getDrawable(R.drawable.onefour));
                img5.setImageDrawable(getDrawable(R.drawable.onefive));
                img6.setImageDrawable(getDrawable(R.drawable.onesix));
                img7.setImageDrawable(getDrawable(R.drawable.oneseven));
                img8.setImageDrawable(getDrawable(R.drawable.oneeight));
                img9.setImageDrawable(getDrawable(R.drawable.onenine));

            }
            else if(imnumber.equals("2"))
            {
                img1.setImageDrawable(getDrawable(R.drawable.twoone));
                img2.setImageDrawable(getDrawable(R.drawable.twotwo));
                img3.setImageDrawable(getDrawable(R.drawable.twothree));
                img4.setImageDrawable(getDrawable(R.drawable.twofour));
                img5.setImageDrawable(getDrawable(R.drawable.twofive));
                img6.setImageDrawable(getDrawable(R.drawable.twosix));
                img7.setImageDrawable(getDrawable(R.drawable.twoseven));
                img8.setImageDrawable(getDrawable(R.drawable.twoeight));
                img9.setImageDrawable(getDrawable(R.drawable.twonine));
            }
            else
            {
                img1.setImageDrawable(getDrawable(R.drawable.threeone));
                img2.setImageDrawable(getDrawable(R.drawable.threetwo));
                img3.setImageDrawable(getDrawable(R.drawable.threethree));
                img4.setImageDrawable(getDrawable(R.drawable.threefour));
                img5.setImageDrawable(getDrawable(R.drawable.threefive));
                img6.setImageDrawable(getDrawable(R.drawable.threesix));
                img7.setImageDrawable(getDrawable(R.drawable.threeseven));
                img8.setImageDrawable(getDrawable(R.drawable.threeeight));
                img9.setImageDrawable(getDrawable(R.drawable.threenine));
            }
        }
    }

    private void init() {
        register = (Button)findViewById(R.id.fra_register) ;
        img1=(ImageView)findViewById(R.id.imageView1);
        img2=(ImageView)findViewById(R.id.imageView2);
        img3=(ImageView)findViewById(R.id.imageView3);
        img4=(ImageView)findViewById(R.id.imageView4);
        img5=(ImageView)findViewById(R.id.imageView5);
        img6=(ImageView)findViewById(R.id.imageView6);
        img7=(ImageView)findViewById(R.id.imageView7);
        img8=(ImageView)findViewById(R.id.imageView8);
        img9=(ImageView)findViewById(R.id.imageView9);
    }
}