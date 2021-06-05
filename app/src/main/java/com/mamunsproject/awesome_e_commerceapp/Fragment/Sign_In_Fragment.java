package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mamunsproject.awesome_e_commerceapp.MainActivity;
import com.mamunsproject.awesome_e_commerceapp.R;

import static com.mamunsproject.awesome_e_commerceapp.Activity.Register_Activity.onResetPasswordFragment;

public class Sign_In_Fragment extends Fragment {

    private TextView dontHaveanAccount;
    private EditText email, password;
    private ImageButton closeButton;
    private Button signInButton;
    private FirebaseAuth firebaseAuth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private ProgressBar progressBar;
    private TextView forgotPassword;
    public static boolean disableCloseBtn = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign__in_, container, false);


//=======================================Initialization=========================================

        dontHaveanAccount = view.findViewById(R.id.tv_dont_have_an_account);
        signInButton = view.findViewById(R.id.sign_in_btn);
        closeButton = view.findViewById(R.id.sign_in_close_btn);
        email = view.findViewById(R.id.sign_in_email);
        password = view.findViewById(R.id.sign_in_password);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = view.findViewById(R.id.sign_in_progressbar);
        forgotPassword = view.findViewById(R.id.sign_in_forgot_password);


//=======================================Initialization=========================================


        dontHaveanAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_out_from_right);
                transaction.replace(R.id.register_framELayout, new Sign_Up_Fragment()).commit();

            }
        });


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

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIntent();
            }
        });


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onResetPasswordFragment = true;

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_out_from_right);
                transaction.replace(R.id.register_framELayout, new ResetePasswordFragment()).commit();
            }
        });


        if (disableCloseBtn) {
            closeButton.setVisibility(View.GONE);
        } else {
            closeButton.setVisibility(View.VISIBLE);
        }


        return view;


    }

    @SuppressLint("ResourceAsColor")
    private void checkEmailAndPassword() {

        if (email.getText().toString().matches(emailPattern)) {
            if (password.length() >= 8) {

                progressBar.setVisibility(View.VISIBLE);
                signInButton.setEnabled(false);
                signInButton.setBackgroundColor(R.color.grey_80);


                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    mainIntent();
                                } else {
                                    Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(getContext(), "Incorrect Password!", Toast.LENGTH_SHORT).show();
                signInButton.setEnabled(true);
                signInButton.setBackgroundColor(R.color.red_50);
                progressBar.setVisibility(View.INVISIBLE);
            }
        } else {
            Toast.makeText(getContext(), "Incorrect Email!", Toast.LENGTH_SHORT).show();
            signInButton.setEnabled(true);
            signInButton.setBackgroundColor(R.color.red_50);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

    @SuppressLint("ResourceAsColor")
    private void checkInput() {
        if (!email.getText().toString().isEmpty()) {
            if (!password.getText().toString().isEmpty()) {
                signInButton.setEnabled(true);
                signInButton.setBackgroundColor(R.color.red_50);
            } else {
                signInButton.setEnabled(false);
                signInButton.setBackgroundColor(R.color.grey_80);

            }
        } else {
            signInButton.setEnabled(false);
            signInButton.setBackgroundColor(R.color.grey_80);

        }
    }


    private void mainIntent() {
        if (disableCloseBtn){
            disableCloseBtn=false;

        }else {
            startActivity(new Intent(getContext(), MainActivity.class));
            disableCloseBtn = false;
        }
        getActivity().finish();

    }
}