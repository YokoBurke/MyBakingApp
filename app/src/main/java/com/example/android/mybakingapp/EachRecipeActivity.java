package com.example.android.mybakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android.mybakingapp.data.BakingRecipe;
import com.example.android.mybakingapp.data.Steps;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class EachRecipeActivity extends AppCompatActivity {

    final static String ClASS_Name = EachRecipeActivity.class.getSimpleName();
    private boolean isTablet;
    private ArrayList<Steps> mySteps;
    private BakingRecipe myBakingRecipe;
    private String myRecipeName;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_recipe);

        int i = 0;

        //Maybe this is not necessary.
        Intent childIntent = getIntent();
        if (childIntent.hasExtra(Intent.EXTRA_TEXT)) {
            myBakingRecipe = (BakingRecipe) childIntent.getParcelableExtra(Intent.EXTRA_TEXT);
            myRecipeName = myBakingRecipe.getName();
            mySteps = myBakingRecipe.getSteps();
            getSupportActionBar().setTitle(myRecipeName);
        }

        isTablet = getResources().getBoolean(R.bool.is_tablet);
        Log.i(ClASS_Name, "IsTablet Status " + Boolean.toString(isTablet));


        EachRecipeFragment eachRecipeFragment = new EachRecipeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, eachRecipeFragment)
                .commit();


        if (isTablet){
            MovieFragment movieFragment =  new MovieFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.video_container, movieFragment)
                    .commit();

            movieFragment.setMovieUrl(mySteps.get(i).getVideoURL());
            Log.i(ClASS_Name, mySteps.get(0).getVideoURL());

        }
    }


    public void onStepSelected(int position) {

        if (isTablet){

            MovieFragment newMovieFragment = new MovieFragment();
            newMovieFragment.setMovieUrl(mySteps.get(position).getVideoURL());

        }

    }

}
