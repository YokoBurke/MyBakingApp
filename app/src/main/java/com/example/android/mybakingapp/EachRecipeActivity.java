package com.example.android.mybakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mybakingapp.data.BakingRecipe;
import com.example.android.mybakingapp.data.Ingredients;
import com.example.android.mybakingapp.data.Steps;
import com.example.android.mybakingapp.utilities.IngredientsAdapter;
import com.example.android.mybakingapp.utilities.StepsAdapter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class EachRecipeActivity extends AppCompatActivity implements StepsAdapter.StepsClickListener {

    final static String CLASS_NAME = EachRecipeActivity.class.getSimpleName();
    private boolean isTablet;
    private ArrayList<Steps> mySteps;
    private ArrayList<Ingredients> myIngredients;
    private BakingRecipe myBakingRecipe;
    private String myRecipeName;
    private MovieFragment movieFragment;

    private RecyclerView stepsRecyclerView;
    private RecyclerView ingredientsRecyclerView;

    private IngredientsAdapter mIngredientsAdapter;
    private StepsAdapter mStepsAdapter;

    private RecyclerView.LayoutManager stepsLayoutManager;
    private RecyclerView.LayoutManager ingredientsLayoutManager;
    int i;
    private FragmentManager fragmentManager;

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
            myIngredients = myBakingRecipe.getIngredients();

        }

        getSupportActionBar().setTitle(myRecipeName);

        ingredientsRecyclerView = (RecyclerView) findViewById(R.id.recycle_ingredient);
        ingredientsRecyclerView.setHasFixedSize(false);
        ingredientsRecyclerView.setNestedScrollingEnabled(false);

        stepsRecyclerView = (RecyclerView) findViewById(R.id.recycle_steps);
        stepsRecyclerView.setHasFixedSize(false);
        stepsRecyclerView.setNestedScrollingEnabled(false);

        ingredientsLayoutManager = new LinearLayoutManager(this);
        stepsLayoutManager = new LinearLayoutManager(this);

        mIngredientsAdapter = new IngredientsAdapter(this, myIngredients);
        Log.i(CLASS_NAME, "Size of my steps is " + Integer.toString(myIngredients.size()));

        mStepsAdapter = new StepsAdapter(this, mySteps, myRecipeName, this);

        Log.i(CLASS_NAME, "Size of my steps is " + Integer.toString(mySteps.size()));

        ingredientsRecyclerView.setLayoutManager(ingredientsLayoutManager);
        ingredientsRecyclerView.setAdapter(mIngredientsAdapter);

        stepsRecyclerView.setLayoutManager(stepsLayoutManager);
        stepsRecyclerView.setAdapter(mStepsAdapter);






        isTablet = getResources().getBoolean(R.bool.is_tablet);
        Log.i(CLASS_NAME, "IsTablet Status " + Boolean.toString(isTablet));

        if (isTablet){
            fragmentManager = getSupportFragmentManager();
            movieFragment =  new MovieFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.video_container, movieFragment)
                    .commit();

            movieFragment.setMovieUrl(mySteps.get(i).getVideoURL());
            Log.i(CLASS_NAME, mySteps.get(0).getVideoURL());

        }
    }

    @Override
    public void StepOnClick(int clickedItemIndex) {
        if (isTablet) {
            Bundle urlBundle = new Bundle();
            urlBundle.putString("myUrl", mySteps.get(clickedItemIndex).getVideoURL());
            MovieFragment myMovieFragment = new MovieFragment();
            myMovieFragment.setArguments(urlBundle);


            fragmentManager.beginTransaction()
                    .replace(R.id.video_container, myMovieFragment)
                    .commit();

        } else {
            Intent intentEachStep;
            intentEachStep = new Intent(getApplicationContext(), EachStepActivity.class);
            intentEachStep.putExtra(Intent.EXTRA_TEXT, mySteps.get(clickedItemIndex));
            intentEachStep.putExtra("recipe_name", myRecipeName);
            intentEachStep.putParcelableArrayListExtra("vlist", mySteps);
            startActivity(intentEachStep);
        }

    }

}
