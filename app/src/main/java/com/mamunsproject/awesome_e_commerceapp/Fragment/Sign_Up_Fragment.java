  package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mamunsproject.awesome_e_commerceapp.MainActivity;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.HashMap;
import java.util.Map;

public class Sign_Up_Fragment extends Fragment {

    TextView alreadyHaveanAccount;
    FrameLayout parentFrameLayout;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    private EditText email, fullName, password, confirmPassword;
    private ImageButton closeButton;
    private Button signUpButton;
    private ProgressBar progressBar;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign__up_, container, false);


//=======================================Initialization=========================================
        parentFrameLayout = view.findViewById(R.id.register_framELayout);
        alreadyHaveanAccount = view.findViewById(R.id.tv_already_have_an_account);
        email = view.findViewById(R.id.sign_up_Email);
        password = view.findViewById(R.id.signUpPassword);
        confirmPassword = view.findViewById(R.id.signup_confirm_password);
        fullName = view.findViewById(R.id.signUp_full_name);
        closeButton = view.findViewById(R.id.signup_close_btn);
        progressBar = view.findViewById(R.id.sign_up_progressBar);
        signUpButton = view.findViewById(R.id.signup_btn);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
//=======================================Initialization=========================================        clickListener();


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainIntent();
            }
        });
//=======================================OnClick Listener=========================================        clickListener();

        alreadyHaveanAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.register_framELayout, new Sign_In_Fragment()).commit();

            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();

            }
        });


//=======================================OnClick Listener=========================================        clickListener();


        return view;

    }



    @SuppressLint("ResourceAsColor")
    private void checkEmailAndPassword() {

        //===========For Changing Error Icon=========
        Drawable customErrorIcon=getResources().getDrawable(R.drawable.ic_attention);
        customErrorIcon.setBounds
                (0,0,customErrorIcon.getIntrinsicWidth()
                        ,customErrorIcon.getIntrinsicHeight());


        if (email.getText().toString().matches(emailPattern)) {
            if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                progressBar.setVisibility(View.VISIBLE);
                //For Multiple Click Handler
                signUpButton.setEnabled(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    signUpButton.setTextColor(R.color.grey_80);
                    signUpButton.setBackgroundColor(R.color.blue_grey_50);
                }


                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Map<Object,String> userData=new HashMap<>();
                                userData.put("FullName",fullName.getText().toString());

                                firebaseFirestore.collection("User")
                                        .add(userData)
                                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {

                                                if (task.isSuccessful()){

                                                    mainIntent();
                                                }else {

                                                    signUpButton.setEnabled(true);
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                        signUpButton.setTextColor(R.color.yellow_50);
                                                        signUpButton.setBackgroundColor(R.color.blue_grey_50);                                    }
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                    String error = task.getException().getMessage();
                                                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });


                            }
                        });
            } else {
                confirmPassword.setError("Password Doesn't Match!",customErrorIcon);


            }
        } else {
            email.setError("Invalid Email!",customErrorIcon);

        }


    }

    @SuppressLint("ResourceAsColor")
    private void checkInput() {
        if (!email.getText().toString().isEmpty()){
            if (!fullName.getText().toString().isEmpty()){
                if (!password.getText().toString().isEmpty()&&password.length()>=8){
                    signUpButton.setEnabled(true);
                    signUpButton.setTextColor(R.color.green_600);
                    signUpButton.setBackgroundColor(R.color.grey_3);


                }
            }
        }else {
            signUpButton.setEnabled(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                signUpButton.setTextColor(Color.argb(50f, 15, 245, 255));
            }
        }



        }


        private void mainIntent(){
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        }
    }

































