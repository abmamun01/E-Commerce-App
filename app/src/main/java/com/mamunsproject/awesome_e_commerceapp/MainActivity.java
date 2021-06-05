package com.mamunsproject.awesome_e_commerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mamunsproject.awesome_e_commerceapp.Activity.Register_Activity;
import com.mamunsproject.awesome_e_commerceapp.Fragment.Home_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Fragment.MY_Order_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Fragment.My_Account_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Fragment.My_Rewards_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Fragment.My_WishList_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Fragment.My_cart_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Fragment.Sign_In_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Fragment.Sign_Up_Fragment;

import org.jetbrains.annotations.NotNull;

import static com.mamunsproject.awesome_e_commerceapp.Activity.Register_Activity.setSignUpFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;
    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int ORDERS_FRAGMENT = 2;
    private static final int MY_WISHLIST_FRAGMENT = 3;
    private static final int MY_REWARDS_FRAGMENT = 4;
    private static final int ACCOUNT_FRAGMENT = 5;
    public static Boolean showCart = false;

    private Window window;
    private Toolbar toolbar;

    private static int current_Fragment = -1;
    private NavigationView navigationView;
    private ImageView actionBar_Logo;
    public static DrawerLayout drawerLayout;
    private Dialog signInDialog;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar_Logo = findViewById(R.id.actionbar_logo);




        //=========== For Changing StatusBar Color in Different Layout ==============================

        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        //=========== For Changing StatusBar Color in Different Layout ==============================

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();


        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        frameLayout = findViewById(R.id.frameLayoutId);
        if (showCart) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            gotoFragment("My Cart", new My_cart_Fragment(), -2);

        } else {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            setFragment(new Home_Fragment(), HOME_FRAGMENT);

        }



        signInDialog = new Dialog(MainActivity.this);
        signInDialog.setContentView(R.layout.sign_in_dialauge);
        signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button dialogSignInButton = signInDialog.findViewById(R.id.signInButton);
        Button dialogSignUpButton = signInDialog.findViewById(R.id.signUpButton);

        signInDialog.setCancelable(true);

        Intent registerIntent = new Intent(MainActivity.this, Register_Activity.class);
        dialogSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sign_In_Fragment.disableCloseBtn = true;
                Sign_Up_Fragment.disableCloseButton=true;

                signInDialog.dismiss();
                setSignUpFragment = false;
                startActivity(registerIntent);

            }
        });

        dialogSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sign_Up_Fragment.disableCloseButton = true;
                Sign_In_Fragment.disableCloseBtn=true;


                signInDialog.dismiss();
                setSignUpFragment = true;
                startActivity(registerIntent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        currentUser= FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(false);
        } else {
            navigationView.getMenu().getItem(navigationView.getMenu().size() - 1).setEnabled(false);
        }

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            if (current_Fragment == HOME_FRAGMENT) {
                current_Fragment = -1;
                super.onBackPressed();
            } else {
                if (showCart) {

                    showCart = false;
                    finish();
                } else {
                    actionBar_Logo.setVisibility(View.VISIBLE);
                    // MAIN ACTIVITY TE BACK KORLE JATE ABAR ACTION BAR R ICON GULA SHOW KORE
                    invalidateOptionsMenu();
                    setFragment(new Home_Fragment(), HOME_FRAGMENT);
                    navigationView.getMenu().getItem(0).setChecked(true);

                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        if (current_Fragment == HOME_FRAGMENT) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.main_searchIcon) {
            //todo:search


            return true;
        } else if (id == R.id.mainNotificationIcon) {
            //todo:notification

            return true;

        } else if (id == R.id.mainCartIcon) {

            if (currentUser == null ){
                signInDialog.show();
            }else {
                //todo:cart
                gotoFragment("My Cart", new My_cart_Fragment(), CART_FRAGMENT);
            }



            return true;
        } else if (id == android.R.id.home) {
            if (showCart) {
                showCart = false;
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StateWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

        if (currentUser != null){


        int id = item.getItemId();

        drawerLayout.closeDrawer(GravityCompat.START);


        if (id == R.id.nav_my_mall) {
            actionBar_Logo.setVisibility(View.VISIBLE);

            // MAIN ACTIVITY TE BACK KORLE JATE ABAR ACTION BAR R ICON GULA SHOW KORE
            invalidateOptionsMenu();
            setFragment(new Home_Fragment(), HOME_FRAGMENT);


        } else if (id == R.id.nav_my_order) {

            gotoFragment("My Orders", new MY_Order_Fragment(), ORDERS_FRAGMENT);


        } else if (id == R.id.nav_rewards) {

            gotoFragment("My Rewards", new My_Rewards_Fragment(), MY_REWARDS_FRAGMENT);


        } else if (id == R.id.nav_my_cart) {
            Toast.makeText(getApplicationContext(), "item" + item, Toast.LENGTH_SHORT).show();
            gotoFragment("My Cart", new My_cart_Fragment(), CART_FRAGMENT);


        } else if (id == R.id.nav_my_whish_list) {

            gotoFragment("My WishList", new My_WishList_Fragment(), MY_WISHLIST_FRAGMENT);

        } else if (id == R.id.nav_signOUt) {
            Toast.makeText(getApplicationContext(), "item" + item, Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this,Register_Activity.class));
            finish();
            

        } else if (id == R.id.nav_my_account) {

            gotoFragment("My Account", new My_Account_Fragment(), ACCOUNT_FRAGMENT);
        }
        }else {

            drawerLayout.closeDrawer(GravityCompat.START);
            signInDialog.show();
            return false;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }



    private void setFragment(Fragment fragment, int fragmentNo) {
        //User HOME FRAGMENT THAKA OBOSTHAY JODI HOME FRAGMENT CLICK KORE TAHOLE KAJ HOBENA
        if (fragmentNo != current_Fragment) {

            if (fragmentNo == MY_REWARDS_FRAGMENT) {
                window.setStatusBarColor(Color.parseColor("#5b04b1"));
                toolbar.setBackgroundColor(Color.parseColor("#5b04b1"));
            } else {
                window.setStatusBarColor(getResources().getColor(R.color.red));
                toolbar.setBackgroundColor(getResources().getColor(R.color.red));

            }
            current_Fragment = fragmentNo;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            transaction.replace(R.id.frameLayoutId, fragment);
            transaction.commit();
        }


    }

    private void gotoFragment(String title, Fragment fragment, int fragmentNo) {
        //Action bar r sob icon remove kore dibe
        actionBar_Logo.setVisibility(View.INVISIBLE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNo);

        if (fragmentNo == CART_FRAGMENT) {
            navigationView.getMenu().getItem(3).setChecked(true);

        }

    }

}