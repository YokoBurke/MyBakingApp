package com.example.android.mybakingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class EachStepActivity extends AppCompatActivity {

    final static String ClASS_Name = EachStepActivity.class.getSimpleName();
    private boolean mTwoPain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_step);

        MovieFragment movieFragment = new MovieFragment();
        NavigationFragment navigationFragment = new NavigationFragment();
        StepFragment stepFragment = new StepFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.video_container, movieFragment)
                .add(R.id.step_container, stepFragment)
                .add(R.id.button_container, navigationFragment)
                .commit();
    }
}
