package com.mamunsproject.awesome_e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mamunsproject.awesome_e_commerceapp.MainActivity;
import com.mamunsproject.awesome_e_commerceapp.R;

public class Splash_Activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);

        firebaseAuth=FirebaseAuth.getInstance();


        SystemClock.sleep(1000);



    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser=firebaseAuth.getCurrentUser();

        if (currentUser==null){
            Intent registerIntent = new Intent(getApplicationContext(), Register_Activity.class);
            startActivity(registerIntent);
            finish();
        }else {
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainIntent);
            finish();

        }
    }
}