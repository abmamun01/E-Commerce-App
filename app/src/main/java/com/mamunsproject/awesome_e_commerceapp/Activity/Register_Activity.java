package com.mamunsproject.awesome_e_commerceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.mamunsproject.awesome_e_commerceapp.Fragment.Sign_In_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Fragment.Sign_Up_Fragment;
import com.mamunsproject.awesome_e_commerceapp.R;

public class Register_Activity extends AppCompatActivity {

    private FrameLayout frameLayout;
    public static boolean onResetPasswordFragment=false;
    public static boolean setSignUpFragment=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.register_framELayout, new Sign_In_Fragment());
//        transaction.commit();

//==================================Default Fragment in activity======================================
        if (setSignUpFragment){
            setSignUpFragment=false;
            setFragment(new Sign_Up_Fragment());
        }else {
            setFragment(new Sign_In_Fragment());

        }

 //==================================Default Fragment in activity======================================



    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==event.KEYCODE_BACK){
            if (onResetPasswordFragment) {
                onResetPasswordFragment=false;
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_left);
                transaction.replace(R.id.register_framELayout, new Sign_In_Fragment());
                transaction.commit();

                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    private void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.register_framELayout,fragment);
        transaction.commit();


    }
}