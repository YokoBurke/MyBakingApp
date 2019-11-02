package com.example.android.mybakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android.mybakingapp.data.BakingRecipe;
import com.example.android.mybakingapp.data.Ingredients;
import com.example.android.mybakingapp.data.Steps;

import java.util.ArrayList;

public class EachStepActivity extends AppCompatActivity {

    final static String ClASS_NAME = EachStepActivity.class.getSimpleName();


    Steps mySteps;
    private ArrayList<Steps> myStepsData;
    String myRecipeName;
    int currentStep;

    String myVideoURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_step);

        Intent childIntent = this.getIntent();
        if (childIntent.hasExtra(Intent.EXTRA_TEXT)) {

            myRecipeName = childIntent.getStringExtra("recipe_name");
            mySteps = (Steps) childIntent.getParcelableExtra(Intent.EXTRA_TEXT);
            currentStep = mySteps.getId();
            myStepsData = childIntent.getParcelableArrayListExtra("vlist");

            myVideoURL = mySteps.getVideoURL();
            Log.i(ClASS_NAME, "Video URL: " + myVideoURL);


            getSupportActionBar().setTitle(myRecipeName);


        }

        MovieFragment movieFragment = new MovieFragment();
        StepFragment stepFragment = new StepFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.video_container, movieFragment)
                .add(R.id.step_container, stepFragment)
                .commit();
    }
}
