package com.example.android.mybakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mybakingapp.data.BakingRecipe;
import com.example.android.mybakingapp.data.Ingredients;
import com.example.android.mybakingapp.data.Steps;
import com.example.android.mybakingapp.utilities.IngredientsAdapter;
import com.example.android.mybakingapp.utilities.StepsAdapter;

import java.util.ArrayList;

public class EachRecipeActivity extends AppCompatActivity {

    final static String ClASS_Name = EachRecipeActivity.class.getSimpleName();

    private ArrayList<Ingredients> myIngredients;
    private ArrayList<Steps> mySteps;

    private RecyclerView stepsRecyclerView;
    private RecyclerView ingredientsRecyclerView;

    private BakingRecipe myBakingRecipe;
    private IngredientsAdapter mIngredientsAdapter;
    private StepsAdapter mStepsAdapter;

    private RecyclerView.LayoutManager stepsLayoutManager;
    private RecyclerView.LayoutManager ingredientsLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_recipe);

        Intent childIntent = getIntent();
        if (childIntent.hasExtra(Intent.EXTRA_TEXT)) {
            myBakingRecipe = (BakingRecipe) childIntent.getParcelableExtra(Intent.EXTRA_TEXT);

        }

        Log.i(ClASS_Name, "recipe is " + myBakingRecipe.getName());

        getSupportActionBar().setTitle(myBakingRecipe.getName());

        myIngredients = myBakingRecipe.getIngredients();
        mySteps = myBakingRecipe.getSteps();
        Log.i(ClASS_Name, "Size of my steps is " + Integer.toString(mySteps.size()));

        ingredientsRecyclerView = (RecyclerView) findViewById(R.id.recycle_ingredient);
        ingredientsRecyclerView.setHasFixedSize(true);

        stepsRecyclerView = (RecyclerView) findViewById(R.id.recycle_steps);
        stepsRecyclerView.setHasFixedSize(true);

        ingredientsLayoutManager = new LinearLayoutManager(this);
        stepsLayoutManager = new LinearLayoutManager(this);
        mIngredientsAdapter = new IngredientsAdapter(this, myIngredients);
        mStepsAdapter = new StepsAdapter(this, mySteps, new StepsAdapter.ListItemClickListner() {
            @Override
            public void onListItemClick(int clickedItemIndex) {

            }
        }, myBakingRecipe.getName());

        ingredientsRecyclerView.setLayoutManager(ingredientsLayoutManager);
        ingredientsRecyclerView.setAdapter(mIngredientsAdapter);

        stepsRecyclerView.setLayoutManager(stepsLayoutManager);
        stepsRecyclerView.setAdapter(mStepsAdapter);

        Log.i(ClASS_Name, "Adapter Set");

    }
}
