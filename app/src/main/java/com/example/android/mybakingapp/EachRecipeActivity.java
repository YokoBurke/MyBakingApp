package com.example.android.mybakingapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class EachRecipeActivity extends AppCompatActivity implements EachRecipeFragment.OnStepClickListener {

    final static String ClASS_Name = EachRecipeActivity.class.getSimpleName();
    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_recipe);

        isTablet = getResources().getBoolean(R.bool.is_tablet);
        Log.i(ClASS_Name, "IsTablet Status " + Boolean.toString(isTablet));

        EachRecipeFragment eachRecipeFragment = new EachRecipeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, eachRecipeFragment)
                .commit();

    }

    @Override
    public void onStepSelected(int position) {
        Toast.makeText(this, "Position Clicked = " + position, Toast.LENGTH_SHORT).show();




    }
}
