package com.example.android.mybakingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class EachRecipeActivity extends AppCompatActivity {

    final static String ClASS_Name = EachRecipeActivity.class.getSimpleName();
    private boolean mTwoPain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_recipe);

        EachRecipeFragment eachRecipeFragment = new EachRecipeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, eachRecipeFragment)
                .commit();

    }
}
