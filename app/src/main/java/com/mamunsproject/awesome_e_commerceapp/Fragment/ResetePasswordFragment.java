package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.mamunsproject.awesome_e_commerceapp.Activity.Register_Activity;
import com.mamunsproject.awesome_e_commerceapp.R;


public class ResetePasswordFragment extends Fragment {


    private EditText registerEmail;
    private Button resetPasswordButton;
    private TextView goBack;
    private FirebaseAuth firebaseAuth;

    private ViewGroup emailIconContainer;
    private ImageView emailIcon;
    private TextView emailIconText;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resete_password, container, false);


        registerEmail = view.findViewById(R.id.forgotPasswordEmail);
        resetPasswordButton = view.findViewById(R.id.resetPasswordButton);
        goBack = view.findViewById(R.id.forgotPassword_go_back);
        firebaseAuth = FirebaseAuth.getInstance();
        emailIconContainer = view.findViewById(R.id.forgotPassWordEmailIcon_Container);
        emailIcon = view.findViewById(R.id.forgot_password_email_icon);
        emailIconText = view.findViewById(R.id.forgot_password_email_icon_text);
        progressBar = view.findViewById(R.id.forgot_password_progressbar);


        resetPasswordButton.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(emailIconContainer);
                emailIconText.setVisibility(View.GONE);




                TransitionManager.beginDelayedTransition(emailIconContainer);
                emailIcon.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);


                resetPasswordButton.setEnabled(false);
                resetPasswordButton.setTextColor(R.color.black);
                resetPasswordButton.setBackgroundColor(R.color.red);



                firebaseAuth.sendPasswordResetEmail(registerEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {


                                    Toast.makeText(getContext(), "Email Sent Successfully!", Toast.LENGTH_SHORT).show();

                                    //Animation r sathe text show korbo tai viewGroup Use Korechi
                                    TransitionManager.beginDelayedTransition(emailIconContainer);
                                    emailIconText.setVisibility(View.VISIBLE);
                                    emailIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.green_700), android.graphics.PorterDuff.Mode.SRC_IN);




                                } else {
                                    emailIconText.setText(task.getException().getMessage());
                                    emailIconText.setTextColor(R.color.red_200);

                                    //Animation r sathe text show korbo tai viewGroup Use Korechi
                                    TransitionManager.beginDelayedTransition(emailIconContainer);
                                    emailIconText.setVisibility(View.VISIBLE);
                                    resetPasswordButton.setEnabled(true);
                                    resetPasswordButton.setTextColor(R.color.black);
                                    resetPasswordButton.setBackgroundColor(R.color.grey_80);

                                }
                                progressBar.setVisibility(View.GONE);

                            }
                        });
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.register_framELayout, new Sign_In_Fragment());
                transaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_left);
                transaction.commit();

            }
        });
        registerEmail.addTextChangedListener(new TextWatcher() {
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


        return view;
    }

    @SuppressLint("ResourceAsColor")
    private void checkInput() {

        if (registerEmail.getText().toString().isEmpty()) {

            resetPasswordButton.setEnabled(false);
            resetPasswordButton.setTextColor(R.color.red);
        } else {
            resetPasswordButton.setEnabled(true);
            resetPasswordButton.setTextColor(R.color.grey_80);
            resetPasswordButton.setTextColor(R.color.black);
        }
    }
}