package com.example.aesencryptionsmsappwithimagesegmentedpassword;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionManager;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesActivity extends AppCompatActivity {

    EditText msgText, msgPhone, msgKey;
    TextView cipherOutput;
    Button encryptBtn, sendBtn;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    String outputString = "-1";
    String msg, phoneNo, themsgstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes);

        init();
        receiveClicks();
    }

    private void receiveClicks() {
        encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (msgText.getText().toString().trim().length() == 0) {
                    Toast.makeText(AesActivity.this, "Message not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    outputString = encrytText(msgText.getText().toString(), msgKey.getText().toString());
                    themsgstring = outputString;
                    cipherOutput.setVisibility(View.VISIBLE);
                    cipherOutput.setText(outputString);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!checkPermissions()) return;

                if (outputString.equals("-1")) {
                    Toast.makeText(AesActivity.this, "Message not found", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (msgPhone.length() != 10) {
                    Toast.makeText(AesActivity.this, "Enter a 10 digit phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendSMS(msgPhone.getText().toString(), outputString);
            }
        });
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            //if permission is not granted then check if the user has denied the permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                return true;
            } else {
                //a pop up will appear asking for required permission i.e. allow or deny
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
                return false;
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendSMS(String phoneNo, String msg) {
        try {
            int i = SubscriptionManager.getDefaultSubscriptionId();
            SmsManager smsManager = SmsManager.getSmsManagerForSubscriptionId(i);
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        //will check the requestCode
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                //check whether the length of grantResults is greater than 0 and is equal to PERMISSION_GRANTED
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Thanks for permitting!", Toast.LENGTH_LONG).show();
                    if (outputString.equals("-1")) {
                        Toast.makeText(AesActivity.this, "Message not found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (msgPhone.length() != 10) {
                        Toast.makeText(AesActivity.this, "Enter a 10 digit phone number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    sendSMS(msgPhone.getText().toString(), outputString);
                } else {
                    Toast.makeText(this, "Why didn't you permit me idiot!", Toast.LENGTH_LONG).show();
                }
            }
        }//switch
    }//method

    private String encrytText(String texttoencryt, String passwordofencrytText) throws Exception {

        SecretKeySpec key = generateKey(passwordofencrytText);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(texttoencryt.getBytes());
        String encrytvalue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encrytvalue;
    }

    private SecretKeySpec generateKey(String passwordofencrytText0) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = passwordofencrytText0.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }

    private void init() {
        msgKey = findViewById(R.id.msg_key);
        msgPhone = findViewById(R.id.msg_phone);
        msgText = findViewById(R.id.msg_text);
        encryptBtn = findViewById(R.id.encrypt_btn);
        sendBtn = findViewById(R.id.send_btn);
        cipherOutput = findViewById(R.id.cipher_op);
    }
}