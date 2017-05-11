package com.example.shaikhbro.moneytracker;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.shaikhbro.moneytracker.fragment.MainMenuFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on start of the activity, show MainMenuFragment.class
        getSupportFragmentManager().beginTransaction().addToBackStack("menu_main").replace(R.id.fragment_placeholder, new MainMenuFragment(),"menu_main").commit();
    }

    @Override
    public void onBackPressed() {
        //on hardware back press, always show MainMenuFragment.
        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new MainMenuFragment(),"menu_main").commit();
    }
}
